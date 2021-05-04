package com.prosjekt.prosjekt.security.auth.registration;

import com.prosjekt.prosjekt.appuser.AppUser;
import com.prosjekt.prosjekt.appuser.AppUserRole;
import com.prosjekt.prosjekt.appuser.AppUserService;
import com.prosjekt.prosjekt.appuser.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    @Autowired
    private final UserRepository userRepository;
    public String register(RegistrationRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new IllegalStateException("Email already taken, try logging in instead!");
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                ));
    }
}
