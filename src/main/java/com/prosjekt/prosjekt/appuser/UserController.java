package com.prosjekt.prosjekt.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final AppUserService appUserService;

    @Autowired
    public UserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }

    @PutMapping(path = "{user_id}/{item_id}")
    public void addToCart(@PathVariable("user_id") Long userId,
                        @PathVariable("item_id") Long itemId){
        appUserService.addToCart(userId, itemId);
    }


    @PutMapping(path = "/checkout/{user_id}")
    public void checkOut(@PathVariable("user_id") Long userId){
        appUserService.checkOut(userId);
    }

    @PostMapping(value = "signUpUser")
    public void signUpUser(@RequestBody AppUser user){
        appUserService.signUpUser(user);
    }

    @GetMapping
    public List<AppUser> getItems(){
        return appUserService.getUsers();
    }



}
