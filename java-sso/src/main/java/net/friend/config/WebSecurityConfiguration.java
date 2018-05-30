package net.friend.config;

import net.friend.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private MyUserDetailsService myUserDetailsService;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    //IMPORTANT: change this could make application not working (loop redirect at RESOURCE APP)
    httpSecurity.requestMatchers()
        .antMatchers("/", "/h2**", "/h2/**", "/login", "/oauth/authorize", "/oauth/exit")
          .and()
        .authorizeRequests()
          .antMatchers("/admin").hasRole("ADMIN")
          .anyRequest().authenticated()
          .and()
        .formLogin()
          .loginPage("/login")
          .permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.parentAuthenticationManager(authenticationManager)
      .userDetailsService(myUserDetailsService)
      .passwordEncoder(new BCryptPasswordEncoder());
  }
}
