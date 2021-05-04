package com.prosjekt.prosjekt.appuser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppUserConfig {
    @Bean(name = "initUsers")
    CommandLineRunner commandLineRunner(AppUserService appUserService){
        return args -> {
            AppUser user = new AppUser(
                    "Jeff",
                    "b",
                    "c",
                    AppUserRole.USER
            );
            appUserService.signUpUser(user);
        };
    }
}
