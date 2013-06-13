package org.panda.trade.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "s_accounting")
public class Accounting extends IdEntity {
	private String no;
	private Long cateId;
	private Date valueDate;
	private String bankRef;
	private String ourRef;
	private String ccy;
	private BigDecimal amount;
	private String counterParty;
	private String remark;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Long getCateId() {
		return cateId;
	}

	public void setCateId(Long cateId) {
		this.cateId = cateId;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public String getBankRef() {
		return bankRef;
	}

	public void setBankRef(String bankRef) {
		this.bankRef = bankRef;
	}

	public String getOurRef() {
		return ourRef;
	}

	public void setOurRef(String ourRef) {
		this.ourRef = ourRef;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(String counterParty) {
		this.counterParty = counterParty;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
