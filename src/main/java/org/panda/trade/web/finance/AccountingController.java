package org.panda.trade.web.finance;

import org.panda.trade.service.finance.AccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/finance")
public class AccountingController {

	@Autowired
	private AccountingService acctService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(Model model) {
		System.out.println(acctService.getAccounting().getContent().size());
		model.addAttribute("accountings", acctService.getAccounting());
		return "/finance/accountingList";
	}
}
