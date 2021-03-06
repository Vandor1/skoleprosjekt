package com.prosjekt.prosjekt.security.auth.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path =  "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public boolean register(@RequestBody RegistrationRequest request){
         return registrationService.register(request);
    }
}
