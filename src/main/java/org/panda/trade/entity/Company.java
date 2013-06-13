package org.panda.trade.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "s_company")
public class Company extends IdEntity {
	private String chineseName;
	private String chineseAddress;
	private String englishName;
	private String englishAddress;
	private Date registerDate;
	private Date updateDate;
	private int category;
	private int status;

	public static int STATUS_OK = 0;
	public static int STATUS_REMOVED = 1;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getChineseAddress() {
		return chineseAddress;
	}

	public void setChineseAddress(String chineseAddress) {
		this.chineseAddress = chineseAddress;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getEnglishAddress() {
		return englishAddress;
	}

	public void setEnglishAddress(String englishAddress) {
		this.englishAddress = englishAddress;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
