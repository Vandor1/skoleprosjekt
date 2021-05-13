package com.prosjekt.prosjekt.appuser;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class AppUserService - AppUser's service component(class) containing the logic handling behind the different requests to be made in the controller.
 */
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AppUserService.class);

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Get 'user details' of user with provided email.
     * @param email of users details queried.
     * @return the user details of user with email @email.
     * @throws NoSuchElementException if no matching email exists.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws NoSuchElementException {
        try {
            AppUser user = userRepository.findByEmail(email).get();
            logger.info("Successfully found user: " + user + "with email: " + email);
            return user;
        } catch (NoSuchElementException e){
            logger.warn("Did not find user with email: " + email);
            logger.warn(e.getMessage(), e);
            return null;
        }
    }

    /**
     * If the AppUser provided does not exist in the repository already(checked via email),
     * we encode the AppUsers password and save it to the database.
     *
     * @param appUser AppUser object with User details of user to be signed up.
     */
    public void signUpUser(AppUser appUser) {
        boolean userExists = userRepository.findByEmail(appUser.getEmail()).isPresent();
        if (userExists) {
            logger.warn("Email: " + appUser.getEmail() + " already in use.");
            throw new IllegalStateException("Email already exists.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        userRepository.save(appUser);
        //added to database
    }

    /**
     * Collects all the users in the repository.
     * @return all the users.
     */
    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }
}