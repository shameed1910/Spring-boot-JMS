package com.qvd.model;

public class NotificationSegmentStructure {
	//private String  SegmentId;
	private String  languageTypeCode;
	private String  languageCountryCode;
	private String  electronicAddressText;
	private String  electronicAddressNumber;
/*	public String getSegmentId() {
		return SegmentId;
	}
	public void setSegmentId(String segmentId) {
		SegmentId = segmentId;
	}
*/	public String getLanguageTypeCode() {
		return languageTypeCode;
	}
	public void setLanguageTypeCode(String languageTypeCode) {
		this.languageTypeCode = languageTypeCode;
	}
	public String getLanguageCountryCode() {
		return languageCountryCode;
	}
	public void setLanguageCountryCode(String languageCountryCode) {
		this.languageCountryCode = languageCountryCode;
	}
	public String getElectronicAddressText() {
		return electronicAddressText;
	}
	public void setElectronicAddressText(String electronicAddressText) {
		this.electronicAddressText = electronicAddressText;
	}
	public String getElectronicAddressNumber() {
		return electronicAddressNumber;
	}
	public void setElectronicAddressNumber(String electronicAddressNumber) {
		this.electronicAddressNumber = electronicAddressNumber;
	}
	
	

}
