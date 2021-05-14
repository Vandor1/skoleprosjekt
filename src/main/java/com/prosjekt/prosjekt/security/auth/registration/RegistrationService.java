package com.prosjekt.prosjekt.security.auth.registration;

import com.prosjekt.prosjekt.appuser.AppUser;
import com.prosjekt.prosjekt.appuser.AppUserRole;
import com.prosjekt.prosjekt.appuser.AppUserService;
import com.prosjekt.prosjekt.appuser.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Registration service class - responsible for registration logic.
 */
@Service
@AllArgsConstructor
public class RegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    private final AppUserService appUserService;

    @Autowired
    private final UserRepository userRepository;

    /**
     * Register new app user. If email is in use, register fails.
     * @param registrationRequest registration details for the application user to be registered.
     * @return boolean success is false if register fails, and true if it is successful.
     */
    public boolean register(RegistrationRequest registrationRequest) {
        boolean success = false;
        if (!userRepository.existsByEmail(registrationRequest.getEmail())) {
            appUserService.signUpUser(
                    new AppUser(
                            registrationRequest.getName(),
                            registrationRequest.getEmail(),
                            registrationRequest.getPassword(),
                            AppUserRole.USER
                    ));
            logger.info("Successfully registered user.");
            success = true;
        } else {
            logger.warn("Could not register user. Email in use.");
        }
        return success;
    }
}
