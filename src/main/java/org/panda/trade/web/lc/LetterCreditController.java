package org.panda.trade.web.lc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.panda.trade.entity.Goods;
import org.panda.trade.entity.LetterCredit;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Controller
@RequestMapping(value = "/lc/application")
public class LetterCreditController {

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
	@ModelAttribute("preloadLC")
	public LetterCredit getLC(
			@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return lcService.getLetterCredit(id);
		}
		return null;
	}

	@RequestMapping(value = "/view")
	public String view() {
		return "lc/application/lcForm";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<LetterCredit> letters = lcService.getAll();
		model.addAttribute("letters", letters);
		return "lc/application/lcList";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("lc", new LetterCredit());
		model.addAttribute("action", "create");
		return "/lc/application/lcForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid LetterCredit newLc,
			RedirectAttributes redirectAttributes, Model model) {
		if (lcService.isExistLcNo(newLc.getLcNo())) {
			model.addAttribute("message", "信用证号已存在");
			model.addAttribute("lc", newLc);
			model.addAttribute("action", "create");
			return "/lc/application/lcForm";
		}
		lcService.saveLc(newLc);
		redirectAttributes.addFlashAttribute("message", "创建信用证申请书成功");
		return "redirect:/lc/application";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("lc", lcService.getLetterCredit(id));
		model.addAttribute("action", "update");
		return "/lc/application/lcForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("preloadLC") LetterCredit lc,
			RedirectAttributes redirectAttributes) {
		lcService.saveLc(lc);
		redirectAttributes.addFlashAttribute("message", "更新任务成功");
		return "redirect:/lc/application";
	}

	@RequestMapping(value = "/goods/{id}")
	public String listGoods(@PathVariable("id") Long id, Model model) {
		model.addAttribute("lc", lcService.getLetterCredit(id));
		return "/lc/application/goodsList";
	}

	@RequestMapping(value = "/download/{id}")
	public void download(@PathVariable("id") Long id,
			HttpServletResponse response) throws IOException, DocumentException {

		LetterCredit lc = lcService.getLetterCredit(id);
		/*****************/
		// TODO Auto-generated method stub
		Document document = new Document();
		// step 2
		// PdfWriter writer = PdfWriter.getInstance(document,
		// new FileOutputStream(LetterCreditController.class
		// .getClassLoader().getResource("lc.pdf").getFile()));
		// step 3
		document.open();
		String pdfFilename = "lc_" + lc.getId() + "_" + new Date().getTime()
				+ ".pdf";

		PdfReader reader = new PdfReader(LetterCreditController.class
				.getClassLoader().getResource("lc.pdf").getFile());
		String url = LetterCreditController.class.getClassLoader().getResource(
				".")
				+ pdfFilename;
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(url
				.substring(6)));

		AcroFields form = reader.getAcroFields();
		for (String name : form.getFields().keySet()) {
			System.out.println(name);
		}
		stamper.getAcroFields().setField(
				"topmostSubform[0].Page1[0].DateTimeField1[0]",
				new SimpleDateFormat("yyyy-MM-dd").format(lc.getIssueDate()));
		stamper.getAcroFields().setField(
				"topmostSubform[0].Page1[0].TextField1[0]",
				lc.getCustomerName() + "\n" + lc.getCustomerAddress());
		stamper.getAcroFields().setField(
				"topmostSubform[0].Page1[0].TextField1[1]",
				lc.getBeneName() + "\n" + lc.getBeneAddress());
		/* check box:establish type */
		stamper.getAcroFields().setField(
				"topmostSubform[0].Page1[0].CheckBox1[0]", "0");
		stamper.getAcroFields().setField(
				"topmostSubform[0].Page1[0].CheckBox1[1]", "0");
		stamper.getAcroFields().setField(
				"topmostSubform[0].Page1[0].CheckBox1[2]", "0");
		switch (lc.getEstablishType()) {
		case 1:
			stamper.getAcroFields().setField(
					"topmostSubform[0].Page1[0].CheckBox1[0]", "1");
			break;
		case 2:
			stamper.getAcroFields().setField(
					"topmostSubform[0].Page1[0].CheckBox1[1]", "1");
			break;
		case 3:
			stamper.getAcroFields().setField(
					"topmostSubform[0].Page1[0].CheckBox1[2]", "1");
			break;
		default:
			break;
		}

		stamper.close();
		reader.close();
		/*****************/
		File file = new File(LetterCreditController.class.getClassLoader()
				.getResource(pdfFilename).getFile());
		InputStream input = FileUtils.openInputStream(file);
		byte[] data = IOUtils.toByteArray(input);

		String fileName = URLEncoder.encode(file.getName(), "UTF-8");

		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IOUtils.write(data, response.getOutputStream());
		IOUtils.closeQuietly(input);
	}
}
