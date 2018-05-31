package net.friend.service.impl;

import net.friend.model.User;
import net.friend.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("redis")
@Service
public class UserServiceRedisImpl implements UserService {

  @Override
  public void save(User user) {

  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public User findByUsername(String username) {
    return null;
  }
}
