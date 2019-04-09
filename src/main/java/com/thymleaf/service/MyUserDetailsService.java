package com.thymleaf.service;

import com.thymleaf.helper.UserHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService
{
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
    {
        return new MyUserPrincipal(UserHelper.listOfUsers().stream()
                .filter(user -> user.getLogin().equals(login)).findFirst().get());
    }
}
