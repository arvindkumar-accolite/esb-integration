
package com.pru.model.esb;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "lifeId", "lifeParty", "anbAge", "dateOfBirth", "occupationCode", "relationshipToLifeInsured",
		"selection", "sex", "smokingIndicator", "height", "weight" })
public class LifeDetails {

	@JsonProperty("lifeId")
	private String lifeId;
	@JsonProperty("lifeParty")
	private String lifeParty;
	@JsonProperty("anbAge")
	private BigInteger anbAge;
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	@JsonProperty("occupationCode")
	private String occupationCode;
	@JsonProperty("relationshipToLifeInsured")
	private String relationshipToLifeInsured;
	@JsonProperty("selection")
	private String selection;
	@JsonProperty("sex")
	private String sex;
	@JsonProperty("smokingIndicator")
	private String smokingIndicator;
	@JsonProperty("height")
	private BigInteger height;
	@JsonProperty("weight")
	private BigInteger weight;

	@JsonProperty("lifeId")
	public String getLifeId() {
		return lifeId;
	}

	@JsonProperty("lifeId")
	public void setLifeId(String lifeId) {
		this.lifeId = lifeId.isEmpty() ? "50002307" : lifeId;
	}

	@JsonProperty("lifeParty")
	public String getLifeParty() {
		return lifeParty;
	}

	@JsonProperty("lifeParty")
	public void setLifeParty(String lifeParty) {
		this.lifeParty = lifeParty.isEmpty() ? "50002307" : lifeParty;
	}

	@JsonProperty("anbAge")
	public BigInteger getAnbAge() {
		return anbAge;
	}

	@JsonProperty("anbAge")
	public void setAnbAge(BigInteger anbAge) {
		this.anbAge = anbAge;
	}

	@JsonProperty("dateOfBirth")
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonProperty("dateOfBirth")
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth.isEmpty() ? "1978-08-06T00:00:00.000Z" : dateOfBirth;
	}

	@JsonProperty("occupationCode")
	public String getOccupationCode() {
		return occupationCode;
	}

	@JsonProperty("occupationCode")
	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode.isEmpty() ? "ACCT" : occupationCode;
	}

	@JsonProperty("relationshipToLifeInsured")
	public String getRelationshipToLifeInsured() {
		return relationshipToLifeInsured;
	}

	@JsonProperty("relationshipToLifeInsured")
	public void setRelationshipToLifeInsured(String relationshipToLifeInsured) {
		this.relationshipToLifeInsured = relationshipToLifeInsured.isEmpty() ? "ch" : relationshipToLifeInsured;
	}

	@JsonProperty("selection")
	public String getSelection() {
		return selection;
	}

	@JsonProperty("selection")
	public void setSelection(String selection) {
		this.selection = selection.isEmpty() ? "OTH" : selection;
	}

	@JsonProperty("sex")
	public String getSex() {
		return sex;
	}

	@JsonProperty("sex")
	public void setSex(String sex) {
		this.sex = sex.isEmpty() ? "M" : sex;
	}

	@JsonProperty("smokingIndicator")
	public String getSmokingIndicator() {
		return smokingIndicator;
	}

	@JsonProperty("smokingIndicator")
	public void setSmokingIndicator(String smokingIndicator) {
		this.smokingIndicator = smokingIndicator.isEmpty() ? "N" : smokingIndicator;
	}

	@JsonProperty("height")
	public BigInteger getHeight() {
		return height;
	}

	@JsonProperty("height")
	public void setHeight(BigInteger height) {
		this.height = height == null ? new BigInteger("170") : height;
	}

	@JsonProperty("weight")
	public BigInteger getWeight() {
		return weight;
	}

	@JsonProperty("weight")
	public void setWeight(BigInteger weight) {
		this.weight = weight == null ? new BigInteger("80") : weight;
	}
}
