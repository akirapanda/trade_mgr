package org.panda.trade.web.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.panda.trade.entity.Company;
import org.panda.trade.entity.Contract;
import org.panda.trade.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

/***
 * 
 * @author AkiraPanda List JSON : /list
 */
@Controller
@RequestMapping(value = "/company")
public class CompanyController {

	private static final int PAGE_SIZE = 10;
	private Logger log = Logger.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;

	@ModelAttribute("preloadCompany")
	public Company getCompany(
			@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return companyService.getCompany(id);
		}
		return null;
	}

	private static String _LIST_PATH = "company/companyList";
	private static String _FORM_PATH = "company/companyForm";

	/***
	 * 汇总查询
	 * 
	 * @param sortType
	 * @param pageNumber
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			Model model, ServletRequest request) {

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");

		log.debug("[客户公司列表查询参数为]" + searchParams.values());
		System.out.println("[客户公司列表查询参数为]"
				+ searchParams.get("LIKE_chineseName"));

		Page<Company> companys = companyService.getCompany(searchParams,
				pageNumber, PAGE_SIZE, sortType);

		// 返回结果集
		model.addAttribute("companys", companys);
		model.addAttribute("sortType", sortType);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "company/companyList";
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public Map<String, Object> getUserList() {
		List<Company> list = companyService.findAll();
		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("data", list);
		modelMap.put("success", "true");
		System.out.println("##");
		return modelMap;
	}

	@RequestMapping(value = "/viewByName/{name}")
	@ResponseBody
	public Map<String, Object> viewByEnglish(@PathVariable("name") String name,
			RedirectAttributes redirectAttributes) {
		Company companyDetail = companyService.getByEnglishName(name);
		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("total", "1");
		modelMap.put("data", companyDetail);
		modelMap.put("success", "true");
		System.out.println("query company by name");
		return modelMap;
	}

	@RequestMapping(value = "/listName")
	@ResponseBody
	public Map<String, Object> getCompanyNameList() {
		List<Company> list = companyService.findAll();
		List<String> names = new ArrayList();

		for (Company company : list) {
			names.add(company.getChineseName());
		}

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("total", "1");
		modelMap.put("data", names);
		modelMap.put("success", "true");
		System.out.println("**");
		return modelMap;
	}

	// @RequestMapping(value = "/list")
	// @ResponseBody
	// public String getUserList() {
	// List<Company> list = companyService.findAll();
	// Map<String, Object> modelMap = new HashMap<String, Object>(3);
	// modelMap.put("total", "1");
	// modelMap.put("data", list);
	// modelMap.put("success", "true");
	// System.out.println("##");
	//
	// return "manage/company/companyPick";
	// }

	/***
	 * Create part
	 */

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("cmp", new Company());
		model.addAttribute("action", "create");
		return _FORM_PATH;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Company newCompany,
			RedirectAttributes redirectAttributes) {

		companyService.createCompany(newCompany);
		redirectAttributes.addFlashAttribute("message", "创建公司成功");
		return "redirect:/company";
	}

	/***
	 * Update part
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cmp", companyService.getCompany(id));
		model.addAttribute("action", "update");
		return _FORM_PATH;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(
			@Valid @ModelAttribute("preloadCompany") Company company,
			RedirectAttributes redirectAttributes) {
		companyService.updateCompany(company);

		return "redirect:/company";
	}

	/**
	 * View Part
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cmp", companyService.getCompany(id));
		model.addAttribute("action", "view");
		return _FORM_PATH;
	}

	@RequestMapping(value = "delete/{id}")
	public String remove(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes) {
		Company company = companyService.getCompany(id);
		companyService.removeCompany(company);

		return "redirect:/company";
	}

}
