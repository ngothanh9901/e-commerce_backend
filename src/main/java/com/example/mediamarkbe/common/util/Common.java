package com.example.mediamarkbe.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    //function to check if the mobile number is valid or not
    public static boolean isValidMobileNo(String str)
    {

        Pattern ptrn = Pattern.compile("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$");
        Matcher match = ptrn.matcher(str);
        return (match.find() && match.group().equals(str));
    }
}
