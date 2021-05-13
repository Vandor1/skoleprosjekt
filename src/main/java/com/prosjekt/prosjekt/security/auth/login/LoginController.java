package com.prosjekt.prosjekt.security.auth.login;

import com.prosjekt.prosjekt.appuser.AppUser;
import com.prosjekt.prosjekt.appuser.AppUserService;
import com.prosjekt.prosjekt.order.OrderService;
import com.prosjekt.prosjekt.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Class AuthController, used to login and authorize users who are already logged in and can be identified by their local JWT.
 *
 * A lot of code inspired from:
 * https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication/blob/master/src/main/java/com/bezkoder/springjwt/controllers/AuthController.java
 * https://bezkoder.com/spring-boot-jwt-authentication/#Define_payloads_for_Spring_RestController
 * https://bezkoder.com/spring-boot-security-postgresql-jwt-authentication/
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/authenticate")
public class LoginController {
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private OrderService orderService;

    /**
     * Login method. Collects login request, checks if its valid with a user in the database, if it is, a jwt token is provided for the user.
     *
     * @param loginRequest request with login data.
     * @return if user exists and a valid login request is sent, the logged in user is returned to frontend.
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())); // Is this user who he says he is?

        SecurityContextHolder.getContext().setAuthentication(authentication); //

        AppUser appUser = (AppUser) authentication.getPrincipal();

        String jwt = jwtUtils.generateToken(appUser);

        orderService.createCart(appUser.getId()); // create cart for user if he does not have one on login.
        return ResponseEntity.ok(new LoginResponse(
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
     *
     * @param jwt token used to log in.
     * @return the user to log in.
     */
    @PostMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestBody String jwt) {
        AppUser appUser = (AppUser) this.appUserService.loadUserByUsername(jwtUtils.getEmailFromJwtToken(jwt));
        return ResponseEntity.ok(new LoginResponse(
                jwt,
                appUser.getId(),
                appUser.getEmail(),
                appUser.getName()
        ));
    }
}