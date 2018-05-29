package net.friend.config;

import org.h2.server.web.WebServlet;
import net.friend.service.OauthClientService;
import net.friend.service.impl.JdbcOauthClientServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public OauthClientService oauthClientService() {
    return new JdbcOauthClientServiceImpl();
  }

  @Bean
  ServletRegistrationBean h2servletRegistration(){
    ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
    registrationBean.addUrlMappings("/h2/*");
    return registrationBean;
  }
}
