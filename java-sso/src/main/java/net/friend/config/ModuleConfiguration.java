package net.friend.config;

import net.friend.service.OauthClientService;
import net.friend.service.UserService;
import net.friend.service.impl.OauthClientServiceJdbcImpl;
import net.friend.service.impl.UserServiceJdbcImpl;
import org.h2.server.web.WebServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableResourceServer
public class ModuleConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public OauthClientService oauthClientService() {
    return new OauthClientServiceJdbcImpl();
  }

  @Bean
  public StringRedisSerializer stringRedisSerializer() {
    return new StringRedisSerializer();
  }

  @Bean
  @ConditionalOnMissingBean
  public UserService userService() {
    return new UserServiceJdbcImpl();
  }

  @Bean
  ServletRegistrationBean h2servletRegistration(){
    ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
    registrationBean.addUrlMappings("/h2/*");
    return registrationBean;
  }
}
