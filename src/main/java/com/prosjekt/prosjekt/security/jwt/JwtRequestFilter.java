package com.prosjekt.prosjekt.security.jwt;

import com.prosjekt.prosjekt.appuser.AppUser;
import com.prosjekt.prosjekt.appuser.AppUserService;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Request filter extending OncePerRequestFilter, is used for authorizing users once per request made.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Examine the incoming request header to check if its valid. If it is valid it will save the user in the security context.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String email = null;
        String jwt = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7);
            email = jwtUtils.extractEmail(jwt);
        }
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            AppUser appUser = (AppUser) this.appUserService.loadUserByUsername(email);
            if(jwtUtils.validateToken(jwt, appUser)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(appUser, null, appUser.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                throw new SignatureException("Token is no longer valid.");
            }
        }
        filterChain.doFilter(request, response);
    }
}
