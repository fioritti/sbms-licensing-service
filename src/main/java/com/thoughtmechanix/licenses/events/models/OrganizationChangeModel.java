package com.thoughtmechanix.licenses.events.models;

public class OrganizationChangeModel {

	private String type;
	private String action;
	private String organizationId;
	private String correlationId;

	public String getAction() {
		return action;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public String getType() {
		return type;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public void setType(String type) {
		this.type = type;
	}

}
