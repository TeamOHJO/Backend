package com.example.yanolja.global.springsecurity;

public class CheckUserLogined {

    public static void checkUserLogined(PrincipalDetails principalDetails) {
        if (principalDetails == null) {
            throw new InvalidPrincipalDetailsException();
        }
    }
}
