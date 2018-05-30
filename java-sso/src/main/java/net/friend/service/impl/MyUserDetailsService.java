package net.friend.service.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.friend.model.User;
import net.friend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.findByUsername(username);
    log.info("(loadUserByUsername) {}, {}", username, user);

    if (user == null) {
      throw new UsernameNotFoundException(String.format("The username %s doesn't exist", username));
    }

    if (user.isActive()) {
      throw new UsernameNotFoundException(String.format("The user has %s is not active", username));
    }

    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String role : user.getRoles()) {
      authorities.add(new SimpleGrantedAuthority(role));
    }

    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), authorities);
  }
}
