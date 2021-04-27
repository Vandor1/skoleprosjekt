package com.prosjekt.prosjekt.appuser;

import com.prosjekt.prosjekt.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1")
public class UserController {
    private final AppUserService appUserService;

    @Autowired
    public UserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }

    @PutMapping(path = "/addToCart/{userId}{itemId}")
    public void addToCart(
            @PathVariable("userId") Long userId,
            @PathVariable("itemId") Long itemId)
    {
        appUserService.addToCart(itemId, userId);
    }


}
