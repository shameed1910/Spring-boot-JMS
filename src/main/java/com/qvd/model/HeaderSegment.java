package com.qvd.model;

public class HeaderSegment {
	
	//private String segmentId;
	private String requestType;
	private String subscriptionId;
	//private String companyHashNumber;
	private String Timestamp;
	private String accountSegmentCount;
	private String lIDSegmentCount;
	private String addressSegmentCount;
	private String totalSegmentCount;
	
	/*public String getSegmentId() {
		return segmentId;
	}
	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}*/
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public String getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(String timestamp) {
		Timestamp = timestamp;
	}
/*	public String getCompanyHashNumber() {
		return companyHashNumber;
	}
	public void setCompanyHashNumber(String companyHashNumber) {
		this.companyHashNumber = companyHashNumber;
	}
*/	public String getAccountSegmentCount() {
		return accountSegmentCount;
	}
	public void setAccountSegmentCount(String accountSegmentCount) {
		this.accountSegmentCount = accountSegmentCount;
	}
	public String getlIDSegmentCount() {
		return lIDSegmentCount;
	}
	public void setlIDSegmentCount(String lIDSegmentCount) {
		this.lIDSegmentCount = lIDSegmentCount;
	}
	public String getAddressSegmentCount() {
		return addressSegmentCount;
	}
	public void setAddressSegmentCount(String addressSegmentCount) {
		this.addressSegmentCount = addressSegmentCount;
	}
	public String getTotalSegmentCount() {
		return totalSegmentCount;
	}
	public void setTotalSegmentCount(String totalSegmentCount) {
		this.totalSegmentCount = totalSegmentCount;
	}
}
