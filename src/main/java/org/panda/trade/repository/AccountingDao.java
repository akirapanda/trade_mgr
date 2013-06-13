package org.panda.trade.repository;

import java.util.List;

import org.panda.trade.entity.Accounting;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountingDao extends
		PagingAndSortingRepository<Accounting, Long>,
		JpaSpecificationExecutor<Accounting> {
	
	public List<Accounting>  findByOurRef(String ourRef);
}
