package net.friend.repository;

import net.friend.model.MdlUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MdlUserRepository extends PagingAndSortingRepository<MdlUser, Long> {

  MdlUser findByUsername(String username);
}
