package net.friend.repository;

import net.friend.model.MyClientDetails;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MyClientDetailsRepository extends
    PagingAndSortingRepository<MyClientDetails, String> {

  void deleteByClientId(String clientId);

  MyClientDetails findByClientId(String clientId);
}
