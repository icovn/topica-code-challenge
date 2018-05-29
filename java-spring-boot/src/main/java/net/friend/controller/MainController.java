package net.friend.controller;

import de.ailis.pherialize.Mixed;
import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class MainController {

  @Value("${security.oauth2.client.user-logout-uri}")
  private String userLogoutUri;

  @GetMapping({"/", "/home"})
  String index() {
    return "index";
  }

  @GetMapping("/hello")
  String hello() {
    return "hello";
  }

  @RequestMapping("/signOut")
  String signOut(HttpSession session, Principal user) {
    log.info("(signOut) {}", user.getName());
    session.invalidate();
    return "redirect:" + userLogoutUri;
  }

  @PostMapping("/php")
  @ResponseBody
  String phpUnserialize (@RequestParam(name="data") String data) {
    //https://github.com/kayahr/pherialize
    Mixed deserializeData = Pherialize.unserialize(data);
    log.info("unserializeData: {}", deserializeData);

    MixedArray deserializeArrayData = deserializeData.toArray();
    log.info("deserializeArrayData: {}", deserializeArrayData);

    return deserializeArrayData.toString();
  }

  @GetMapping("/session")
  @ResponseBody
  String session(HttpSession session, Principal user) {
    return session.getId() + " | " + user.getName();
  }
}
