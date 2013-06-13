package org.panda.trade.service.contract;

import java.util.Map;

import org.panda.trade.entity.Contract;
import org.panda.trade.repository.ContractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

@Component
@Transactional(readOnly = true)
public class ContractService {
	@Autowired
	private ContractDao contractDao;
	
	@Transactional(readOnly = false)
	public void saveContract(Contract contract) {
		contractDao.save(contract);
	}

	public Contract getContract(Long id) {
		return contractDao.findOne(id);
	}

	public boolean isExisting(String orderNo) {
		return contractDao.findByOrderNo(orderNo) != null;
	}

	public Page<Contract> getContracts(Long id,
			Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize,
				sortType);
		Specification<Contract> spec = buildSpecification(id, searchParams);
		return contractDao.findAll(pageRequest);
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize,
			String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("orderNo".equals(sortType)) {
			sort = new Sort(Direction.ASC, "orderNo");
		}
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<Contract> buildSpecification(Long id,
			Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ,
		// userId));
		Specification<Contract> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), Contract.class);
		return spec;
	}
}
