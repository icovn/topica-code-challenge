package net.friend.service;

import net.friend.model.User;

public interface UserService {

  void save(User user);

  long count();

  User findByUsername(String username);
}
