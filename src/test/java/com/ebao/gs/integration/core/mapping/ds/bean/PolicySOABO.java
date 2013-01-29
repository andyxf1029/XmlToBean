package com.ebao.gs.integration.core.mapping.ds.bean;

import java.util.List;

public class PolicySOABO {

	private Long policyID;

	private String policyNo;

	private String GLPolicyNo;

	private List<LocationSOABO> lociationList;

	private List<ClassificationSOABO> classificationList;

	public Long getPolicyID() {
		return policyID;
	}

	public void setPolicyID(Long policyID) {
		this.policyID = policyID;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public List<LocationSOABO> getLociationList() {
		return lociationList;
	}

	public void setLociationList(List<LocationSOABO> lociationList) {
		this.lociationList = lociationList;
	}

	@Override
	public String toString() {
		return "PolicySOABO [policyID=" + policyID + ", policyNo=" + policyNo
				+ ", GLPolicyNo=" + GLPolicyNo + ", lociationList="
				+ lociationList + ", classificationList=" + classificationList
				+ "]";
	}

	public List<ClassificationSOABO> getClassificationList() {
		return classificationList;
	}

	public void setClassificationList(
			List<ClassificationSOABO> classificationList) {
		this.classificationList = classificationList;
	}

	public String getGLPolicyNo() {
		return GLPolicyNo;
	}

	public void setGLPolicyNo(String gLPolicyNo) {
		GLPolicyNo = gLPolicyNo;
	}

}
