package org.example.security.userdetails;

import org.example.dao.UserRepository;
import org.example.model.User;
import org.example.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUserName("techie");
        return user.map(UserDetailsImpl::new).orElseGet(this::guestUser);
    }

    private UserDetailsImpl guestUser() {
        User guestUser = new User();
        guestUser.setUserName("GUEST");
        guestUser.setPassword("GUEST");
        return new UserDetailsImpl(guestUser, new HashSet<GrantedAuthority>(Collections.singletonList(new SimpleGrantedAuthority("GUEST"))));
    }
}
