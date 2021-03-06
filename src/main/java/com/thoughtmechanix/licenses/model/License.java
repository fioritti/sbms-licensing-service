package com.thoughtmechanix.licenses.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "licenses")
public class License {

	@Id
	@Column(name = "license_id", nullable = false)
	private String licenseId;

	@Column(name = "organization_id", nullable = false)
	private String organizationId;

	@Column(name = "product_name", nullable = false)
	private String productName;

	@Column(name = "license_type", nullable = false)
	private String licenseType;

	@Column(name = "license_max", nullable = false)
	private Integer licenseMax;

	@Column(name = "license_allocated", nullable = false)
	private Integer licenseAllocated;

	@Column(name = "comment")
	private String comment;

	@Transient
	private String organizationName = "";

	@Transient
	private String contactName = "";

	@Transient
	private String contactPhone = "";

	@Transient
	private String contactEmail = "";

	public String getComment() {
		return comment;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public Integer getLicenseAllocated() {
		return licenseAllocated;
	}

	public String getLicenseId() {
		return licenseId;
	}

	public Integer getLicenseMax() {
		return licenseMax;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public String getProductName() {
		return productName;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public void setLicenseAllocated(Integer licenseAllocated) {
		this.licenseAllocated = licenseAllocated;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}

	public void setLicenseMax(Integer licenseMax) {
		this.licenseMax = licenseMax;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public License withComment(String comment) {
		this.setComment(comment);
		return this;
	}

	public License withContactEmail(String contactEmail) {
		this.setContactEmail(contactEmail);
		return this;
	}

	public License withContactName(String contactName) {
		this.setContactName(contactName);
		return this;
	}

	public License withContactPhone(String contactPhone) {
		this.setContactPhone(contactPhone);
		return this;
	}

	public License withId(String id) {
		this.setLicenseId(id);
		return this;
	}

	public License withLicenseAllocated(Integer licenseAllocated) {
		this.setLicenseAllocated(licenseAllocated);
		return this;
	}

	public License withLicenseMax(Integer licenseMax) {
		this.setLicenseMax(licenseMax);
		return this;
	}

	public License withLicenseType(String licenseType) {
		this.setLicenseType(licenseType);
		return this;
	}

	public License withOrganizationId(String organizationId) {
		this.setOrganizationId(organizationId);
		return this;
	}

	public License withOrganizationName(String organizationName) {
		this.setOrganizationName(organizationName);
		return this;
	}

	public License withProductName(String productName) {
		this.setProductName(productName);
		return this;
	}

}
