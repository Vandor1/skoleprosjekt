package com.prosjekt.prosjekt.appuser;
import com.prosjekt.prosjekt.item.Item;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    private Long id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked=false;
    private Boolean enabled=true;

    @JoinTable(name = "cart")
    @OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
    @JoinColumn
    private List<Item> cartItems;

    @JoinTable(name = "orders")
    @OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
    @JoinColumn
    private List<Item> orderItems;

    public AppUser(String name,
                   String email,
                   String password,
                   AppUserRole appUserRole){
        this.name = name;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    public AppUser(String name,
                   String email,
                   String password,
                   AppUserRole appUserRole,
                   List<Item> cartItems,
                   List<Item> orderItems) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.cartItems = cartItems;
        this.orderItems = orderItems;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    public List<Item> getCartItems() {
        return cartItems;
    }

    public void addOrder(List<Item> cartItems){
        this.orderItems.addAll(cartItems);
        cartItems.clear();
    }

    public void addItem(Item item){
        this.cartItems.add(item);
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getName(){return name;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
