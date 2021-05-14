package com.prosjekt.prosjekt.security.config;

import com.prosjekt.prosjekt.appuser.AppUserService;
import com.prosjekt.prosjekt.security.jwt.JwtRequestFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * CLASS WebSecurityConfig extending WebSecurityConfigurerAdapter, which is the standard spring security class for
 * implementing security restrictions on certain requests.
 *
 * Taken inspiration from https://bezkoder.com/spring-boot-security-postgresql-jwt-authentication/
 */
@Configuration
@AllArgsConstructor
@EnableWebSecurity // allows Spring to find and automatically apply the class to the global Web Security.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final AppUserService appUserService; // UserDetailsService
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final JwtRequestFilter jwtRequestFilter;

    /**
     * Used to disable cors for testing in web browser. Is <b>not</b> needed once we setup nqinx as a webproxy.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
                registry.addMapping("/**").allowedMethods("*");}
        };
    }

    /**
     * Configurer for authorized requests. Get all items, login and register are permitted by all users. Only authorized(logged in)
     * users are authorized to request other things, such as orderList.
     * @param http configuration
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/item/**").permitAll()
                .antMatchers("/api/v1/authenticate/login").permitAll()
                .antMatchers("/api/v1/registration").permitAll()
                .anyRequest().authenticated()
        .and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Don't manage or create session(no memory of previous actions).

        // Add JwtRequestFilter before username password filter is called.
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
