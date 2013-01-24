package com.ebao.as.gs.integration.mapping.merge;

import java.util.List;
import java.util.Map;

import com.ebao.gs.integration.core.mapping.ds.bean.ClassificationSOABO;
import com.ebao.gs.integration.core.mapping.ds.bean.LocationSOABO;
import com.ebao.gs.integration.core.mapping.ds.bean.PolicySOABO;
import com.ebao.gs.integration.mapping.merge.Merger;

public class USMerger implements Merger {

	public Object merge(Map<String, Object> resultMap) {
		List<PolicySOABO> policyList = (List<PolicySOABO>) resultMap
				.get("commonPolicy");
		List<LocationSOABO> LocationSOABOList = (List<LocationSOABO>) resultMap
				.get("loaction");
		policyList.get(0).setLociationList(LocationSOABOList);
		List<ClassificationSOABO> classificationList = (List<ClassificationSOABO>) resultMap
				.get("Classification");
		policyList.get(0).setClassificationList(classificationList);
		return policyList.get(0);
	}

}
