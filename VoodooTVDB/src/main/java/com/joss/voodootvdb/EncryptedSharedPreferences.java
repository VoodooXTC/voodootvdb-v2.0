package com.joss.voodootvdb;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import oak.CryptoSharedPreferences;

/**
 * Created by: jossayjacobo
 * Date: 2/27/15
 * Time: 4:15 PM
 */
public final class EncryptedSharedPreferences extends CryptoSharedPreferences {

    public EncryptedSharedPreferences(Context context, SharedPreferences delegate) {
        super(context, delegate);
    }

    @Override
    protected char[] getSpecialCode() {
        return "T3L@M3t!3R0n!!".toCharArray();
    }

    @Override
    public Set<String> getStringSet(String s, Set<String> strings) {
        return null;
    }
}

