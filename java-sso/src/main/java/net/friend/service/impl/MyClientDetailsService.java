package net.friend.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.friend.service.OauthClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyClientDetailsService implements ClientDetailsService {

  @Autowired
  private OauthClientService oauthClientService;

  @Override
  public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    log.info("(loadClientByClientId) {}", clientId);
    return oauthClientService.findByClientId(clientId);
  }
}
