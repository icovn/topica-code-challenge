package net.friend.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.friend.model.MdlUser;
import net.friend.model.User;
import net.friend.repository.MdlUserRepository;
import net.friend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("jdbc")
@Service
@Slf4j
public class UserServiceJdbcImpl implements UserService {

  @Autowired
  private MdlUserRepository mdlUserRepository;

  @Override
  public void save(User user) {
    log.info("(save){}", user);
    mdlUserRepository.save(new MdlUser(user));
  }

  @Override
  public long count() {
    log.info("(count)");
    return mdlUserRepository.count();
  }

  @Override
  public User findByUsername(String username) {
    log.info("(findByUsername) {}", username);
    return mdlUserRepository.findByUsername(username);
  }
}
