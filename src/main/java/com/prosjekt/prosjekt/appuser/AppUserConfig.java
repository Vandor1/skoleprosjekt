package com.prosjekt.prosjekt.appuser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppUserConfig {
    @Bean(name = "initUsers")
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            AppUser user = new AppUser(
                    "mathias",
                    "gmail",
                    "jegErHomo",
                    AppUserRole.USER
            );
            userRepository.save(user);
        };
    }
}
