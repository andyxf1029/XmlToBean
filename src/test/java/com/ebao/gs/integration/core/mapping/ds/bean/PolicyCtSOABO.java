package com.ebao.gs.integration.core.mapping.ds.bean;

import java.math.BigDecimal;

public class PolicyCtSOABO {

	private String coverageCd;

	private BigDecimal premium;

	public String getCoverageCd() {
		return coverageCd;
	}

	public void setCoverageCd(String coverageCd) {
		this.coverageCd = coverageCd;
	}

	public BigDecimal getPremium() {
		return premium;
	}

	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	@Override
	public String toString() {
		return "PolicyCtSOABO [coverageCd=" + coverageCd + ", premium="
				+ premium + "]";
	}

}
