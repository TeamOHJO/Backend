package com.example.yanolja.global.springsecurity;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getPhoneNumber() {
        return null;
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email")+"OAuth2";
    }

    @Override
    public String getProvider() {
        return "google";
    }
}
