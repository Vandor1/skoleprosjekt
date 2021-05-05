package com.prosjekt.prosjekt.security.auth;

import com.prosjekt.prosjekt.appuser.AppUser;
import com.prosjekt.prosjekt.appuser.AppUserService;
import com.prosjekt.prosjekt.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication/blob/master/src/main/java/com/bezkoder/springjwt/controllers/AuthController.java
 * https://bezkoder.com/spring-boot-jwt-authentication/#Define_payloads_for_Spring_RestController
 * https://bezkoder.com/spring-boot-security-postgresql-jwt-authentication/
 */
@CrossOrigin
@RestController
@RequestMapping(path="/api/v1/authenticate")
public class AuthController {
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final AppUser appUser = (AppUser) appUserService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtUtils.generateToken(appUser);

        return ResponseEntity.ok(new AuthenticationResponse(
                jwt,
                appUser.getId(),
                appUser.getEmail(),
                appUser.getName()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser appUser = (AppUser) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(appUser);
        return ResponseEntity.ok(new AuthenticationResponse(
                jwt,
                appUser.getId(),
                appUser.getEmail(),
                appUser.getName()
        ));
    }
}