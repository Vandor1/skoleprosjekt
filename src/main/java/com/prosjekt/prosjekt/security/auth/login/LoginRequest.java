package com.prosjekt.prosjekt.security.auth.login;

/**
 * LOGIN REQUEST CLASS, DEFINES WHAT A LOGIN RESPONSE IS MEANT TO LOOK LIKE AND MUST CONTAIN.
 */
public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void getJwt(){

    }
}
