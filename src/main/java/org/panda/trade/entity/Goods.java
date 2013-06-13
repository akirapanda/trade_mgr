package org.panda.trade.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "s_goods")
public class Goods extends IdEntity {
	private Long lcId;
	private String band;
	private String goodsName;
	private String ccy;
	private Double price;
	private String tradeTerm;
	private String unit;
	private String pymtType;
	private String pymtTime;
	private Double weight;
	private String weightUnit;

	public Long getLcId() {
		return lcId;
	}

	public void setLcId(Long lcId) {
		this.lcId = lcId;
	}

	@NotBlank
	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTradeTerm() {
		return tradeTerm;
	}

	public void setTradeTerm(String tradeTerm) {
		this.tradeTerm = tradeTerm;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPymtType() {
		return pymtType;
	}

	public void setPymtType(String pymtType) {
		this.pymtType = pymtType;
	}

	public String getPymtTime() {
		return pymtTime;
	}

	public void setPymtTime(String pymtTime) {
		this.pymtTime = pymtTime;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

}
