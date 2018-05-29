package net.friend.service.impl;

import net.friend.model.MyClientDetails;
import net.friend.service.OauthClientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("redis")
@Service
public class RedisOauthClientServiceImpl implements OauthClientService {

  @Override
  public void save(MyClientDetails myClientDetails) {

  }

  @Override
  public void deleteByClientId(String clientId) {

  }

  @Override
  public MyClientDetails findByClientId(String clientId) {
    return null;
  }
}
