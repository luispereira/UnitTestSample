package com.lpereira.uts.unittestsample.utils;

import android.text.TextUtils;

/**
 * @author lpereira on 16/12/2017.
 */

public class SomethingStatic {

    public static int getSomethingFromSomething(String value){
        return getTwoOrZero(value);
    }

    public static int getTwoOrZero(String value){
        return TextUtils.equals(value, "2") ? 2 : 0;
    }
}
