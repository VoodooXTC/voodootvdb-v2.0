package com.joss.voodootvdb.api.models.Login;

/**
 * Created by: jossayjacobo
 * Date: 3/10/15
 * Time: 5:38 PM
 */
public class AccessTokenRequest {

    public static final String CLIENT_ID = "eb78e260fea060a63edc2857fa41ddee8002674c899ae6705dfbedeada8f4a46";
    public static final String REDIRECT_URI = "http://voodootvdb.com";
    public static final String CLIENT_SECRET = "1fda1cef2e4e7d76f563d82a27905526b76925b2d2f0079926be5ad48640d085";
    public static final String GRANT_TYPE = "authorization_code";

    String code;
    String client_id;
    String redirect_uri;
    String client_secret;
    String grant_type;

    public AccessTokenRequest(String code){
        this.code = code;
        this.client_id = CLIENT_ID;
        this.redirect_uri = REDIRECT_URI;
        this.client_secret = CLIENT_SECRET;
        this.grant_type = GRANT_TYPE;
    }

}
