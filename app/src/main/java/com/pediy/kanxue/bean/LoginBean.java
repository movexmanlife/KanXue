package com.pediy.kanxue.bean;

public class LoginBean {
    private int result;
    private String username;
    private int userid;
    private int isavatar;
    private String email;
    private String securitytoken;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getIsavatar() {
        return isavatar;
    }

    public void setIsavatar(int isavatar) {
        this.isavatar = isavatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecuritytoken() {
        return securitytoken;
    }

    public void setSecuritytoken(String securitytoken) {
        this.securitytoken = securitytoken;
    }
}
