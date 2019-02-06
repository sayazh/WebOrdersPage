package com.utilities;


public class BrowserUtils {

    public static  boolean verifyTextMatches(String str1, String str2){
        return str1.equals(str2);
    }

    public static boolean verifyTextContains(String str1, String str2){
        return str1.contains(str2);
    }
}
