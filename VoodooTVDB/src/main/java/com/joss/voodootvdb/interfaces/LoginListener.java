package com.joss.voodootvdb.interfaces;

/**
 * Created by: jossayjacobo
 * Date: 3/5/15
 * Time: 11:27 AM
 */
public interface LoginListener {

    public void onOAuthRequest(String username);

    public void onLoginSuccess();

    public void onCancel();

    public void onAnimateOutFinished();

}
