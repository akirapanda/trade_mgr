package org.panda.trade.repository;

import java.util.List;

import org.panda.trade.entity.LetterCredit;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LetterCreditDao extends
		PagingAndSortingRepository<LetterCredit, Long>,
		JpaSpecificationExecutor<LetterCredit> {
	LetterCredit findById(Long id);

	List<LetterCredit> findByContractOrderNo(String orderNo);

	LetterCredit findByLcNo(String lcNo);
}
