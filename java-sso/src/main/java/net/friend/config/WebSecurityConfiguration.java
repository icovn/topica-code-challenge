package net.friend.config;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import net.friend.auth.ClientResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

@Configuration
@EnableOAuth2Client
@EnableWebSecurity
@Order(200)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Value("${spring.security.user.name}")
  private String username;

  @Value("${spring.security.user.password}")
  private String password;

  @Autowired
  private OAuth2ClientContext oauth2ClientContext;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    UserDetails user =
        User.withDefaultPasswordEncoder()
            .username(username)
            .password(password)
            .roles("USER")
            .build();

    UserDetails gitUser =
        User.withDefaultPasswordEncoder()
            .username("icovn")
            .password("123")
            .roles("USER")
            .build();

    return new InMemoryUserDetailsManager(user, gitUser);
  }

  @Bean
  public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
    FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
    registration.setFilter(filter);
    registration.setOrder(-100);
    return registration;
  }

  @Bean
  @ConfigurationProperties("github")
  public ClientResources github() {
    return new ClientResources();
  }

  @Bean
  @ConfigurationProperties("facebook")
  public ClientResources facebook() {
    return new ClientResources();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    // @formatter:off
    httpSecurity.antMatcher("/**").authorizeRequests().antMatchers("/", "/h2**", "/h2/**", "/login**", "/login/**", "/webjars/**").permitAll().anyRequest()
        .authenticated().and().exceptionHandling()
        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/")).and().logout()
        .logoutSuccessUrl("/").permitAll().and().csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
        .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);

    //Uncomment to access H2 console
//    httpSecurity.csrf().disable();
//    httpSecurity.headers().frameOptions().disable();
    // @formatter:on
  }

  @Configuration
  @EnableResourceServer
  protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
      // @formatter:off
      http.antMatcher("/me").authorizeRequests().anyRequest().authenticated();
      // @formatter:on
    }
  }

  private Filter ssoFilter() {
    CompositeFilter filter = new CompositeFilter();
    List<Filter> filters = new ArrayList<>();
    filters.add(ssoFilter(facebook(), "/login/facebook"));
    filters.add(ssoFilter(github(), "/login/github"));
    filter.setFilters(filters);
    return filter;
  }

  private Filter ssoFilter(ClientResources client, String path) {
    OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(
        path);
    OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
    filter.setRestTemplate(template);
    UserInfoTokenServices tokenServices = new UserInfoTokenServices(
        client.getResource().getUserInfoUri(), client.getClient().getClientId());
    tokenServices.setRestTemplate(template);
    filter.setTokenServices(tokenServices);
    return filter;
  }
}
