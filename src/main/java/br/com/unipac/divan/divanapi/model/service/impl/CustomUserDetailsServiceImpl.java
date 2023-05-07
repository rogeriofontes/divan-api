package br.com.unipac.divan.divanapi.model.service.impl;

import br.com.unipac.divan.divanapi.model.entities.user.Profile;
import br.com.unipac.divan.divanapi.model.entities.user.User;
import br.com.unipac.divan.divanapi.model.service.CustomUserDetailsService;
import br.com.unipac.divan.divanapi.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Custom user details service.
 */
@Slf4j
@Service
@Qualifier("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService, UserDetailsService {

    private UserService userService;

    /**
     * Instantiates a new Custom user details service.
     *
     * @param userService the user service
     */
    public CustomUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return userService.findByEmail(email).map(user -> {
            var profiles = user.getProfiles();
            var authorities = buildUserAuthority(profiles);

            return buildUserForAuthentication(user, authorities);
        }).orElse(null);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Profile> profiles) {
        var auths = new HashSet<GrantedAuthority>();

        profiles.stream().forEach(profile -> {
            auths.add(new SimpleGrantedAuthority(profile.getRole().toString()));
        });

        return new ArrayList<>(auths);
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true,
                true, true, true, authorities);
    }


    @Override
    public User loadCurrentUser() {
        return userService.loadCurrentUser();
    }
}
