package com.example.yanolja.global.springsecurity;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getPhoneNumber() {
        return (String) attributes.get("mobile");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email")+"OAuth2";
    }

    @Override
    public String getProvider() {
        return "naver";
    }
}
