package com.yrabdelrhmn.userinfodatabase;

public class UserInfo {
    private String userName; //Storing user name
    private String userPhone; //Storing user phone
    private String userAddress; // Storing user address

    public UserInfo() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}