package net.friend.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.friend.util.ExceptionUtil;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class MainController {

  @GetMapping("/login")
  String login() {
    log.info("(login)");
    return "login";
  }

  @RequestMapping("/oauth/exit")
  public void exit(HttpServletRequest request, HttpServletResponse response) {
    // token can be revoked here if needed
    new SecurityContextLogoutHandler().logout(request, null, null);
    try {
      //sending back to client app
      response.sendRedirect(request.getHeader("referer"));
    } catch (IOException ex) {
      log.error(ExceptionUtil.getStackTrace(ex));
    }
  }
}
