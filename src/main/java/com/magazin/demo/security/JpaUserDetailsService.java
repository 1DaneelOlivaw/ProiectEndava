package com.magazin.demo.security;

import com.magazin.demo.model.User;
import com.magazin.demo.model.UserGroup;
import com.magazin.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> customer = userRepository.findUserByUsername(username);
        if (customer.isEmpty()) {
            throw new UsernameNotFoundException("User "+username+" was not found in the database");
        }
        return new MyUserPrincipal(customer.get());
    }

    private Collection<GrantedAuthority> getAuthorities(User user){
        Set<UserGroup> userGroups = user.getUserGroups();
        Collection<GrantedAuthority> authorities = new ArrayList<>(userGroups.size());
        for(UserGroup userGroup : userGroups){
            authorities.add(new SimpleGrantedAuthority(userGroup.getCode().toUpperCase()));
        }
        return authorities;
    }
}