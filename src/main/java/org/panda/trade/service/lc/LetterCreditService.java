package org.panda.trade.service.lc;

import java.util.List;
import java.util.Map;

import org.panda.trade.entity.LetterCredit;
import org.panda.trade.repository.LetterCreditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

@Component
@Transactional(readOnly = true)
public class LetterCreditService {
	@Autowired
	private LetterCreditDao letterCreditDao;

	public List<LetterCredit> getAll() {
		return (List<LetterCredit>) letterCreditDao.findAll();
	}

	@Transactional(readOnly = false)
	public void saveLc(LetterCredit lc) {
		letterCreditDao.save(lc);
	}

	// public List<LetterCredit> getLetterCreditByContractOrderNo(
	// final String contractOrderNo) {
	// return letterCreditDao.findAll(new Specification<LetterCredit>() {
	// @Override
	// public Predicate toPredicate(Root<LetterCredit> root,
	// CriteriaQuery<?> query, CriteriaBuilder cb) {
	// root = query.from(LetterCredit.class);
	// Path<String> nameExp = root.get("contractOrderNo");
	// return cb.equal(nameExp, contractOrderNo);
	// };
	// });

	// }
	public List<LetterCredit> getLetterCreditByContractOrderNo(String orderNo) {
		return letterCreditDao.findByContractOrderNo(orderNo);
	}

	public LetterCredit getLetterCredit(Long id) {
		return letterCreditDao.findById(id);
	}

	public boolean isExistLcNo(String lcNo) {
		return letterCreditDao.findByLcNo(lcNo) != null;
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<LetterCredit> buildSpecification(Long id,
			Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ,
		// userId));
		Specification<LetterCredit> spec = DynamicSpecifications
				.bySearchFilter(filters.values(), LetterCredit.class);
		return spec;
	}
}
