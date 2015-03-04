package com.joss.voodootvdb.api.models.Login;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 5:44 PM
 */
public class UserModel {

    public String login;

    public String password;

    public String token;

    public String message;

    public UserModel(String username, String password) {
        this.login = username;
        this.password = password;
    }
}
