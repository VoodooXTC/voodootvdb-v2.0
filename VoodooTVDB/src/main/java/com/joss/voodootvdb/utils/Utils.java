package com.joss.voodootvdb.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by: jossayjacobo
 * Date: 3/5/15
 * Time: 10:29 AM
 */
public class Utils {

    public static void toast(Context context, String message){
        Toast.makeText(context,
                message,
                Toast.LENGTH_SHORT)
                .show();
    }

    public static void hideSoftKeyboard(Context context, View view){
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
