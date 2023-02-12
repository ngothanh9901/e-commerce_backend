package com.example.mediamarkbe.common.util;

import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class SpecificationUtils {

    public static boolean isMatchRegex(String input, String regex) {
        // validate input
        if (ObjectUtils.isEmpty(input)) {
            return false;
        }

        // return validation result
        return Pattern.compile(regex).matcher(input).matches();
    }

    public static Date convertStringToDateObject(String input, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(input);
    }
}
