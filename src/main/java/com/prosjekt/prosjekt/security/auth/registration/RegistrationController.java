package com.prosjekt.prosjekt.security.auth.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Registration controller, identifies the request required to register a user.
 */
@CrossOrigin
@RestController
@RequestMapping(path =  "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    /**
     * Register new user. Post method as we are adding a <b>new</b> user.
     * @param request registration request
     * @return boolean success or failure to front end.(true for success, false for failure)
     */
    @PostMapping
    public boolean register(@RequestBody RegistrationRequest request){
         return registrationService.register(request);
    }
}
