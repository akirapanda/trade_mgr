package org.panda.trade.web.lc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.panda.trade.entity.Goods;
import org.panda.trade.entity.LetterCredit;
import org.panda.trade.service.lc.GoodsService;
import org.panda.trade.service.lc.LetterCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

@Controller
@RequestMapping(value = "/lc/goods")
public class GoodsController {
	@Autowired
	GoodsService goodsService;
	@Autowired
	LetterCreditService lcService;

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);

	}

	/**
	 */
	@ModelAttribute("preloadGoods")
	public Goods getGoods(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return goodsService.getGoodsById(id);
		}
		return null;
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes, Model model) {
		Goods goods = goodsService.getGoodsById(id);
		System.out.println("@goods band@ is:" + goods.getBand());
		// redirectAttributes.addAttribute("good", goods);
		model.addAttribute("action", "update");
		model.addAttribute("good", goods);
		return "forward:/lc/application/goods/" + goods.getLcId();
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("preloadGoods") Goods goods,
			RedirectAttributes redirectAttributes) {
		goodsService.save(goods);
		redirectAttributes.addFlashAttribute("message", "更新货物信息成功");
		return "forward:/lc/application/goods/" + goods.getLcId();
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Goods newGoods,
			RedirectAttributes redirectAttributes, Model model) {
		goodsService.save(newGoods);
		redirectAttributes.addFlashAttribute("message", "创建货物信息成功");
		return "redirect:/lc/application/goods/" + newGoods.getLcId();
	}

}
