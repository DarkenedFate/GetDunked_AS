package com.jt.getdunked.championdata;

import com.google.gson.annotations.Expose;

public class Var {

	@Expose
	private String key;
	@Expose
	private String link;
	@Expose
	private Double coeff;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Double getCoeff() {
		return coeff;
	}

	public void setCoeff(Double coeff) {
		this.coeff = coeff;
	}

}
