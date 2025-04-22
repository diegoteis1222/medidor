package com.cebem.medidor.utils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class Utils {

    public static boolean isPalindrome(String text) {
        String inverse = new StringBuilder(text).reverse().toString();
        return text.equalsIgnoreCase(inverse);
    }
    

}

