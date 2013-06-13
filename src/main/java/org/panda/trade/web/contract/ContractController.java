package org.panda.trade.web.contract;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.panda.trade.entity.Contract;
import org.panda.trade.entity.LetterCredit;
import org.panda.trade.service.contract.ContractService;
import org.panda.trade.service.lc.LetterCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Maps;

/***
 * 
 * @author Akira.Panda
 * 
 *         list - view all create
 * 
 */
@Controller
@RequestMapping(value = "/contract")
public class ContractController {
	@Autowired
	private ContractService contractService;

	@Autowired
	private LetterCreditService lcService;

	private static final int PAGE_SIZE = 10;
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("orderNo", "合同号");
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);

	}

	@ModelAttribute("preloadContract")
	public Contract getContract(
			@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return contractService.getContract(id);
		}
		return null;
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("contract", new Contract());
		model.addAttribute("action", "create");
		return "contract/contractForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Contract newContract, Model model) {
		if (contractService.isExisting(newContract.getOrderNo())) {
			model.addAttribute("errorMessage", "合同号["
					+ newContract.getOrderNo() + "] 已存在");
		} else {
			contractService.saveContract(newContract);
			model.addAttribute("message", "合同号[" + newContract.getOrderNo()
					+ "] 创建成功");
			model.addAttribute("contract", newContract);
			return "contract/contractCreate";
		}
		model.addAttribute("action", "create");
		return "contract/contractForm";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("contract", contractService.getContract(id));
		model.addAttribute("action", "update");
		return "contract/contractForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(
			@Valid @ModelAttribute("preloadContract") Contract contract,
			RedirectAttributes redirectAttributes) {
		contractService.saveContract(contract);
		System.out.println(contract.getOrderNo());
		return "redirect:/contract";
	}

	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String viewItem(@PathVariable("id") Long id, Model model) {
		Contract contract = contractService.getContract(id);
		model.addAttribute("contract", contract);
		System.out.println(lcService.getLetterCreditByContractOrderNo(
				contract.getOrderNo()).size()
				+ ":" + contract.getOrderNo());
		model.addAttribute("letters", lcService
				.getLetterCreditByContractOrderNo(contract.getOrderNo()));
		model.addAttribute("action", "view");
		return "contract/contractForm";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			Model model, ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		Page<Contract> contracts = contractService.getContracts(0l,
				searchParams, pageNumber, PAGE_SIZE, sortType);

		model.addAttribute("contracts", contracts);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);

		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "contract/contractList";
	}

}
