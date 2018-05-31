package net.friend.controller;

import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

  @RequestMapping("/user/me")
  public Principal user(Principal principal){
    log.info("(user) {}", principal);
    return principal;
  }
}
