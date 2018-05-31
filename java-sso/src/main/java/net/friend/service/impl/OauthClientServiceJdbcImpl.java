package net.friend.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.friend.model.MyClientDetails;
import net.friend.repository.MyClientDetailsRepository;
import net.friend.service.OauthClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("jdbc")
@Service
@Slf4j
public class OauthClientServiceJdbcImpl implements OauthClientService {

  @Autowired
  private MyClientDetailsRepository myClientDetailsRepository;

  @Override
  public void save(MyClientDetails myClientDetails) {
    log.info("(save) {}", myClientDetails);
    myClientDetailsRepository.save(myClientDetails);
  }

  @Override
  public void deleteByClientId(String clientId) {
    log.info("(deleteByClientId) {}", clientId);
    myClientDetailsRepository.deleteByClientId(clientId);
  }

  @Override
  public long count() {
    log.info("(count)");
    return myClientDetailsRepository.count();
  }

  @Override
  public MyClientDetails findByClientId(String clientId) {
    log.info("(findByClientId) {}", clientId);
    return myClientDetailsRepository.findByClientId(clientId);
  }
}
