package net.friend;

import lombok.extern.slf4j.Slf4j;
import net.friend.model.MdlUser;
import net.friend.model.MyClientDetails;
import net.friend.service.OauthClientService;
import net.friend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Value("${app.default.client.id}")
  private String defaultClientId;

  @Value("${app.default.client.secret}")
  private String defaultClientSecret;

  @Value("${app.default.user.username}")
  private String defaultUsername;

  @Value("${app.default.user.password}")
  private String defaultPassword;

  @Autowired
  private OauthClientService oauthClientService;

  @Autowired
  private UserService userService;

  @Override
  public void run(String... args) throws Exception {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    long clientsCount = oauthClientService.count();
    log.info("clientsCount {}", clientsCount);
    if(clientsCount == 0){
      MyClientDetails clientDetails = new MyClientDetails();
      clientDetails.setClientId(defaultClientId);
      clientDetails.setClientSecret(defaultClientSecret);
      clientDetails.setScope("read,write,profile,email");
      clientDetails.setAuthorizedGrantTypes("authorization_code");
      clientDetails.setIsAutoApprove(true);
      log.info("(initClient) {}", clientDetails);
      oauthClientService.save(clientDetails);
    }

    long usersCount = userService.count();
    log.info("usersCount {}", usersCount);
    if(usersCount == 0){
      for(int i = 0; i <= 10; i++){
        MdlUser user = new MdlUser((long)i, i + defaultUsername, passwordEncoder.encode(defaultPassword), true, "ADMIN");
        log.info("(initUser) {}, {}, {}", i + defaultUsername, defaultPassword, user);
        userService.save(user);
      }
    }
  }
}



