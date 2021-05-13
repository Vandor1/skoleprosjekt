package com.prosjekt.prosjekt.appuser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dummy users, meant for testing primarily.
 */
@Configuration
public class AppUserConfig {

    /**
     * CommandLineRunner is ran on application start.
     * @param appUserService
     * @return
     */
    @Bean(name = "initUsers")
    CommandLineRunner commandLineRunner(AppUserService appUserService){
        return args -> {
            AppUser user1 = new AppUser(
                    "Jeff",
                    "b",
                    "c",
                    AppUserRole.USER
            );
            AppUser user2 = new AppUser(
                    "Frank",
                    "a",
                    "b",
                    AppUserRole.USER
            );
            appUserService.signUpUser(user1);
            appUserService.signUpUser(user2);
        };
    }
}
