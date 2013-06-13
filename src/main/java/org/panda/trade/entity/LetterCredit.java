package org.panda.trade.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "s_lc")
public class LetterCredit extends IdEntity {
	private String contractOrderNo;
	private String lcNo;
	private Integer lcType;

	private Date issueDate;
	private Date lastIssueDate;
	private Date billDate;

	private String issueBankName;
	private String ccy;
	private Double amount;
	private Integer establishType;

	private String customerName;
	private String customerAddress;
	private String beneName;
	private String beneAddress;

	private List<Goods> goods=new ArrayList<Goods>();

	public String getContractOrderNo() {
		return contractOrderNo;
	}

	public void setContractOrderNo(String contractOrderNo) {
		this.contractOrderNo = contractOrderNo;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="lcId")
	public List<Goods> getGoods() {
		return goods;
	}
	
	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	@Column(nullable = false, unique = true)
	@NotBlank
	public String getLcNo() {
		return lcNo;
	}

	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}

	public Integer getLcType() {
		return lcType;
	}

	public void setLcType(Integer lcType) {
		this.lcType = lcType;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getLastIssueDate() {
		return lastIssueDate;
	}

	public void setLastIssueDate(Date lastIssueDate) {
		this.lastIssueDate = lastIssueDate;
	}

	public String getIssueBankName() {
		return issueBankName;
	}

	public void setIssueBankName(String issueBankName) {
		this.issueBankName = issueBankName;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@NotNull
	public Integer getEstablishType() {
		return establishType;
	}

	public void setEstablishType(Integer establishType) {
		this.establishType = establishType;
	}

	@NotBlank
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@NotBlank
	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	@NotBlank
	public String getBeneName() {
		return beneName;
	}

	public void setBeneName(String beneName) {
		this.beneName = beneName;
	}

	@NotBlank
	public String getBeneAddress() {
		return beneAddress;
	}

	public void setBeneAddress(String beneAddress) {
		this.beneAddress = beneAddress;
	}

}
