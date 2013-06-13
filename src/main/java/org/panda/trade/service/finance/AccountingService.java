package org.panda.trade.service.finance;

import java.util.List;

import org.panda.trade.entity.Accounting;
import org.panda.trade.repository.AccountingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class AccountingService {
	@Autowired
	private AccountingDao acctDao;

	public List<Accounting> getAccountingByOurRef(String ourRef) {
		return acctDao.findByOurRef(ourRef);
	}

	public Page<Accounting> getAccounting() {
		return acctDao.findAll(new PageRequest(0, 5));
	}

}
