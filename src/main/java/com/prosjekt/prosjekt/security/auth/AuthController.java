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

    /**
     * Login
     * @param loginRequest
     * @return
     */
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

    /**
     * If a user has an existing JWT in localStorage, it will be used to automatically log the user back in
     * to his last session. JWT localStorage handling is done in frontend. JWT will automatically be illegitimate
     * after a given time from JwtUtils(jwtExpirationMs), requiring the user to log in manually again.
     * @param jwt token used to log in.
     * @return the user to log in.
     */
    @PostMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestBody String jwt){
        AppUser appUser = (AppUser) this.appUserService.loadUserByUsername(jwtUtils.getEmailFromJwtToken(jwt));
        return ResponseEntity.ok(new AuthenticationResponse(
                jwt,
                appUser.getId(),
                appUser.getEmail(),
                appUser.getName()
        ));
    }
}