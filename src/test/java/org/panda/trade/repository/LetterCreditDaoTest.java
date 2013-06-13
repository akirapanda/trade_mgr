package org.panda.trade.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.panda.trade.entity.LetterCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class LetterCreditDaoTest extends SpringTransactionalTestCase {

	@Autowired
	private LetterCreditDao lcDao;

	@Test
	public void findLetterCreditByContractNo() throws Exception {
		List<LetterCredit> letters = lcDao.findByContractOrderNo("BNF003");
		assertEquals(1, letters.size());
	}
}
