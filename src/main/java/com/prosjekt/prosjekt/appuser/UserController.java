package com.prosjekt.prosjekt.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User Controller class - responsible for the requests which can be made to App Users.
 * These include; Sign up user and get users. Login and register functionality is its own controller, as it works closely
 * with spring security and needs special handling.
 */
@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final AppUserService appUserService;

    @Autowired
    public UserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }

    /**
     * Sign up user request.
     * @param user to signup.
     */
    @PostMapping(value = "signUpUser")
    public void signUpUser(@RequestBody AppUser user){
        appUserService.signUpUser(user);
    }

    /**
     * Get all AppUsers request. {NOT USED}
     * @return list of AppUsers.
     */
    @GetMapping
    public List<AppUser> getUsers(){
        return appUserService.getUsers();
    }



}
