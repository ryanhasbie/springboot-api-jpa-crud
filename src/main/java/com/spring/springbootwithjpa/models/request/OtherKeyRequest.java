package com.spring.springbootwithjpa.models.request;

public class OtherKeyRequest {
    private String searchKeyName;
    private String getSearchKeyEmail;

    public String getSearchKeyName() {
        return searchKeyName;
    }

    public void setSearchKeyName(String searchKeyName) {
        this.searchKeyName = searchKeyName;
    }

    public String getGetSearchKeyEmail() {
        return getSearchKeyEmail;
    }

    public void setGetSearchKeyEmail(String getSearchKeyEmail) {
        this.getSearchKeyEmail = getSearchKeyEmail;
    }
}
