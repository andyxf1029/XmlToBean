package com.ebao.gs.integration.core.mapping.ds.bean;

public class LocationSOABO {

	private Long locationId;

	private String locationNo;

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationNo() {
		return locationNo;
	}

	public void setLocationNo(String locationNo) {
		this.locationNo = locationNo;
	}

	@Override
	public String toString() {
		return "LocationSOABO [locationId=" + locationId + ", locationNo="
				+ locationNo + "]";
	}

}
