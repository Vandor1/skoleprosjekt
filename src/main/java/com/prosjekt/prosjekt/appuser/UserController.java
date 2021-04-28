package com.prosjekt.prosjekt.appuser;

import com.prosjekt.prosjekt.item.ItemRepository;
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

    @PostMapping()
    public void addToCart(@RequestBody Long userId,
                          @RequestBody Long itemId)
    {
        appUserService.addToCart(itemId, userId);
    }


    @GetMapping
    public List<AppUser> getItems(){
        return appUserService.getUsers();
    }


}
