package net.friend.service;

import net.friend.model.MyClientDetails;

public interface OauthClientService {

  void save(MyClientDetails myClientDetails);

  void deleteByClientId(String clientId);

  long count();

  MyClientDetails findByClientId(String clientId);
}
