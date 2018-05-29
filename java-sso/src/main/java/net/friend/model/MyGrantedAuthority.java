package net.friend.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class MyGrantedAuthority implements GrantedAuthority {

  private String authority;

  @Override
  public String getAuthority() {
    return authority;
  }
}
