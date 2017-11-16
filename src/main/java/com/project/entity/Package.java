package com.project.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Package")
public class Package {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Id")
	private long id;
	
	@NotNull
	@Column(name="P_Id")
	private String packageId;
	
	@NotNull
	@Column(name="PackageName")
	private String packageName;
	
	@NotNull
	@Column(name="ValidFrom")
	private Date  validFrom;
	
	@Column(name="ValidTill")
	private int validTill;
	
	@Column(name="ExpiresOn")
	private Date expiresOn;
	
	@OneToMany(mappedBy = "packages", cascade = {CascadeType.MERGE,CascadeType.REMOVE}, fetch=FetchType.EAGER, orphanRemoval=true)
	private Set<Topic> topics = new HashSet<>();
	
	public Package(){}

	public Date getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(Date expiresOn) {
		this.expiresOn = expiresOn;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPackageId() {
		return packageId;
	}
	
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public int getValidTill() {
		return validTill;
	}

	public void setValidTill(int validTill) {
		this.validTill = validTill;
	}

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	@Override
	public String toString() {
		return "Package [packageId=" + packageId + ", packageName=" + packageName + "]";
	}
	
}
