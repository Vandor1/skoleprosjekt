package com.prosjekt.prosjekt.appuser;

import com.prosjekt.prosjekt.item.Item;
import com.prosjekt.prosjekt.item.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequestMapping
public class AppUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final static String LOGIN_ERROR = "user name in use";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;




    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(LOGIN_ERROR));
    }

    public String signUpUser(AppUser appUser){
        boolean userExists = userRepository.findByEmail(appUser.getEmail()).isPresent();
        if(userExists){ throw new IllegalStateException("Email already taken!"); }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        userRepository.save(appUser); //added to database
        return "works now :D";
    }

    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }



    public void addToCart(Long userId, Long itemId){
        Optional<AppUser> user = userRepository.findAppUserById(userId);
        Optional<Item> item = itemRepository.findItemById(itemId);
        if(user.isPresent() && item.isPresent()) {
            user.get().addItem(item.get());

            System.out.println(user.get().getCartItems());
            System.out.println(item.get().getName());
        }
    }

    public void checkOut(Long userId) {
        Optional<AppUser> user = userRepository.findAppUserById(userId);
        if (user.isEmpty()){
            throw new IllegalStateException("user id not found");
        }

        user.get().addOrder(user.get().getCartItems());
    }
}
