package com.ebao.gs.integration.core.mapping.ds.bean;

import java.util.ArrayList;
import java.util.List;

public class ClassificationSOABO {

	private List<PolicyCtSOABO> policyCtList = new ArrayList<PolicyCtSOABO>();

	public List<PolicyCtSOABO> getPolicyCtList() {
		return policyCtList;
	}

	public void setPolicyCtList(List<PolicyCtSOABO> policyCtList) {
		this.policyCtList = policyCtList;
	}

	@Override
	public String toString() {
		return "ClassificationSOABO [policyCtList=" + policyCtList + "]";
	}

}
