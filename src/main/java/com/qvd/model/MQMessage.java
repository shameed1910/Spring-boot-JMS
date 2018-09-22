package com.qvd.model;

import java.util.List;

public class MQMessage 
{
	private HeaderSegment headerSegment;
	private SpanSegmentStructure spanSegmentStructure;
	private MainSegmentStructure mainSegmentStructure;
	private DeliverySegmentStructure deliverySegmentStructure;
	private NotificationSegmentStructure notificationSegmentStructure;
	private EDISegmentStructure eDISegmentStructure;
	private List<AccountSegmentStructure> accountSegmentStructures;
	private List<LIDSegmentStructure> lidSegmentStructures;
	private List<AddressSegmentStructure> addressSegmentStructures ;
	
	public HeaderSegment getHeaderSegment() {
		return headerSegment;
	}
	public void setHeaderSegment(HeaderSegment headerSegment) {
		this.headerSegment = headerSegment;
	}
	public SpanSegmentStructure getSpanSegmentStructure() {
		return spanSegmentStructure;
	}
	public void setSpanSegmentStructure(SpanSegmentStructure spanSegmentStructure) {
		this.spanSegmentStructure = spanSegmentStructure;
	}
	public MainSegmentStructure getMainSegmentStructure() {
		return mainSegmentStructure;
	}
	public void setMainSegmentStructure(MainSegmentStructure mainSegmentStructure) {
		this.mainSegmentStructure = mainSegmentStructure;
	}
	public DeliverySegmentStructure getDeliverySegmentStructure() {
		return deliverySegmentStructure;
	}
	public void setDeliverySegmentStructure(DeliverySegmentStructure deliverySegmentStructure) {
		this.deliverySegmentStructure = deliverySegmentStructure;
	}
	public NotificationSegmentStructure getNotificationSegmentStructure() {
		return notificationSegmentStructure;
	}
	public void setNotificationSegmentStructure(NotificationSegmentStructure notificationSegmentStructure) {
		this.notificationSegmentStructure = notificationSegmentStructure;
	}
	public EDISegmentStructure geteDISegmentStructure() {
		return eDISegmentStructure;
	}
	public void seteDISegmentStructure(EDISegmentStructure eDISegmentStructure) {
		this.eDISegmentStructure = eDISegmentStructure;
	}
	public List<AccountSegmentStructure> getAccountSegmentStructures() {
		return accountSegmentStructures;
	}
	public void setAccountSegmentStructures(List<AccountSegmentStructure> accountSegmentStructures) {
		this.accountSegmentStructures = accountSegmentStructures;
	}
	public List<LIDSegmentStructure> getLidSegmentStructures() {
		return lidSegmentStructures;
	}
	public void setLidSegmentStructures(List<LIDSegmentStructure> lidSegmentStructures) {
		this.lidSegmentStructures = lidSegmentStructures;
	}
	public List<AddressSegmentStructure> getAddressSegmentStructures() {
		return addressSegmentStructures;
	}
	public void setAddressSegmentStructures(List<AddressSegmentStructure> addressSegmentStructures) {
		this.addressSegmentStructures = addressSegmentStructures;
	}

}
