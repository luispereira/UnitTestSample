package com.lpereira.uts.unittestsample.utils;

/**
 * @author lpereira on 17/12/2017.
 */

public class JavaTextUtils {
    private JavaTextUtils() {
        //empty constructor
    }

    public static boolean isEmpty(String searchText) {
        return searchText == null || searchText.isEmpty();
    }

    public static boolean equals(String searchText, String name) {
        return searchText != null && searchText.equals(name);
    }
}
