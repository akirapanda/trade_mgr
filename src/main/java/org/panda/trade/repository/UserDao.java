package org.panda.trade.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.panda.trade.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);
}
