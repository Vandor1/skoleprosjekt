package com.prosjekt.prosjekt.security.auth.registration;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/**
 * Registration request with annotation all args constructor and annotation getter, to remove the need to type them out.
 * This class represents what a registration request needs to looks like.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String email;
    private final String password;
    private final String name;
}
