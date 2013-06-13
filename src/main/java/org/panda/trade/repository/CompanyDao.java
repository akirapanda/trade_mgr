package org.panda.trade.repository;

import org.panda.trade.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyDao extends PagingAndSortingRepository<Company, Long>,
		JpaSpecificationExecutor<Company> {
	Company findById(Long id);

	Company findByEnglishName(String englishName);

	Page<Company> findByStatus(int status, Specification<Company> sp,
			Pageable pageable);
}
