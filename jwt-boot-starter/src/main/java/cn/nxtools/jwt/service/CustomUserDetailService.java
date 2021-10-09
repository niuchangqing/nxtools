package cn.nxtools.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author ncq
 */
public class CustomUserDetailService implements UserDetailsService {

    @Autowired(required = false)
    @Qualifier("customUserService")
    CustomUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.loadByUsername(username);
    }
}
