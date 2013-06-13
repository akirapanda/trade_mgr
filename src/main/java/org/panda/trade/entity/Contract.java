package org.panda.trade.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "s_contract")
public class Contract extends IdEntity {
	private String orderNo;
	private Date contractDate;
	private Date actContractDate;
	private String buyName;
	private String sellName;
	/* 0-未生效 1-已生效 2-取消 */
	private Integer status = 0;

	@NotBlank
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public Date getActContractDate() {
		return actContractDate;
	}

	public void setActContractDate(Date actContractDate) {
		this.actContractDate = actContractDate;
	}

	public String getBuyName() {
		return buyName;
	}

	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
