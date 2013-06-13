package org.panda.trade.repository;

import org.panda.trade.entity.Contract;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContractDao extends PagingAndSortingRepository<Contract, Long>,
		JpaSpecificationExecutor<Contract> {
	Contract findById(Long id);

	Contract findByOrderNo(String orderNo);
}
