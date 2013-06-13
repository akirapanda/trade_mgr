package org.panda.trade.service.company;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.panda.trade.entity.Company;
import org.panda.trade.repository.CompanyDao;
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
import org.springside.modules.persistence.SearchFilter.Operator;

@Component
@Transactional(readOnly = true)
public class CompanyService {
	@Autowired
	private CompanyDao companyDao;

	public Company getCompany(Long id) {
		return companyDao.findById(id);
	}

	public List<Company> findAll() {
		return (List<Company>) companyDao.findAll();
	}

	public Page<Company> getCompany(Map<String, Object> searchParams,
			int pageNumber, int pageSize, String sortType) {

		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize,
				sortType);
		Specification<Company> spec = buildSpecification(searchParams);

		return companyDao.findAll(spec, pageRequest);
	}

	public Company getByEnglishName(String englishName) {
		return companyDao.findByEnglishName(englishName);
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize,
			String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("chineseName".equals(sortType)) {
			sort = new Sort(Direction.ASC, "chineseName");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<Company> buildSpecification(
			Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("EQ_status", new SearchFilter("status", Operator.EQ,
				Company.STATUS_OK));
		Specification<Company> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), Company.class);
		return spec;
	}

	@Transactional(readOnly = false)
	public void createCompany(Company entity) {
		entity.setRegisterDate(new Date(System.currentTimeMillis()));
		entity.setUpdateDate(new Date(System.currentTimeMillis()));

		companyDao.save(entity);
	}

	@Transactional(readOnly = false)
	public void updateCompany(Company entity) {
		entity.setUpdateDate(new Date(System.currentTimeMillis()));

		companyDao.save(entity);
	}

	@Transactional(readOnly = false)
	public void removeCompany(Company entity) {
		entity.setUpdateDate(new Date(System.currentTimeMillis()));
		entity.setStatus(Company.STATUS_REMOVED);
		companyDao.save(entity);
	}

}
