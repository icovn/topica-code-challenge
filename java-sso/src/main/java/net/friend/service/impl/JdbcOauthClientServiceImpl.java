package net.friend.service.impl;

import net.friend.model.MyClientDetails;
import net.friend.repository.MyClientDetailsRepository;
import net.friend.service.OauthClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("jdbc")
@Service
public class JdbcOauthClientServiceImpl implements OauthClientService {

  @Autowired
  private MyClientDetailsRepository myClientDetailsRepository;

  @Override
  public void save(MyClientDetails myClientDetails) {
    myClientDetailsRepository.save(myClientDetails);
  }

  @Override
  public void deleteByClientId(String clientId) {
    myClientDetailsRepository.deleteByClientId(clientId);
  }

  @Override
  public MyClientDetails findByClientId(String clientId) {
    return myClientDetailsRepository.findByClientId(clientId);
  }
}
