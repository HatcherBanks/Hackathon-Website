package edu.hackaton.backend.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import edu.hackaton.backend.service.UserService;

@Component
public class DaoUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(DaoUserDetailsService.class);

    private UserService userService;

    public DaoUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("try to authenticate {}",email);
        var user = userService.findUserByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User with email: "+email+" not found"));
        
        return new UserInfo(user);
    }
    
}
