package com.prosjekt.prosjekt.security.auth;

public class AuthenticationResponse {
    private final String jwt;
    private final Long id;
    private final String email;
    private final String name;

    public AuthenticationResponse(String jwt, Long id, String email, String name) {
        this.jwt = jwt;
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getJwt(){
        return jwt;
    }

}
