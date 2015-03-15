package com.joss.voodootvdb.api;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit.client.Request;
import retrofit.client.UrlConnectionClient;

/**
 * Created by Jossay
 * Date: 3/15/2015
 * Time: 4:28 PM
 */
public class ShortTimeOutClient extends UrlConnectionClient {

    private int timeout;

    public ShortTimeOutClient(int timeout){
        this.timeout = timeout;
    }

    @Override
    protected HttpURLConnection openConnection(Request request) throws IOException {
        HttpURLConnection connection = super.openConnection(request);
        connection.setConnectTimeout(timeout);
        return connection;
    }
}
