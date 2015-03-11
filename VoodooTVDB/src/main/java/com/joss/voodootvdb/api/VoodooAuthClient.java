package com.joss.voodootvdb.api;

import android.content.Context;
import android.util.Log;

import com.joss.voodootvdb.DataStore;
import com.joss.voodootvdb.api.models.Login.AccessToken;
import com.joss.voodootvdb.api.models.Login.AccessTokenRequest;

import java.io.IOException;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 5:18 PM
 */
public class VoodooAuthClient extends OkClient {

    public static final String TAG = VoodooAuthClient.class.getSimpleName();

    Context context;

    public VoodooAuthClient(Context context){
        this.context = context;
    }

    @Override
    public Response execute(Request request) throws IOException {
        Response response = super.execute(request);

        // 401: Unauthorized - OAuth must be provided
        if (response.getStatus() == 401) {

            // Try to refresh the User Token
            AccessToken accessToken = DataStore.getAccessToken(context);

            if(accessToken != null){
                AccessToken newAccessToken= null;
                try {
                    newAccessToken = ApiService.getApi(context).login(new AccessTokenRequest(accessToken.refresh_token));

                }catch (RetrofitError error){
                    Log.d(TAG, error.toString());
                }

                if (newAccessToken != null && newAccessToken.access_token != null) {

                    DataStore.persistAccessToken(context, newAccessToken);
                    Request newRequest = updateTokenInRequest(request, newAccessToken.access_token);

                    return super.execute(newRequest);

                }else{
                    // TODO Prompt User with Login Dialog to attempt to re-login
//                    Intent reLogin = new Intent(context, DialogActivity.class);
//                    reLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    reLogin.putExtra(DialogActivity.TYPE, DialogActivity.RE_LOGIN);
//                    context.startActivity(reLogin);
                }
            }
        }
        return response;
    }

    private Request updateTokenInRequest(Request request, String token) {
        List<Header> headers = request.getHeaders();
        for(int i = 0; i < headers.size(); i++){
            Header h = headers.get(i);
            if(h.getName().equals(VoodooRequestInterceptor.HEADER_AUTHORIZATION)){
                headers.remove(i);
                headers.add(new Header(VoodooRequestInterceptor.HEADER_AUTHORIZATION, token));
                break;
            }
        }
        return new Request(request.getMethod(), request.getUrl(), headers, request.getBody());
    }
}
