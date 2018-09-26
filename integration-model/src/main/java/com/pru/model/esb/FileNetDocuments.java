package com.pru.model.esb;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "applicationNumber", "documents" })
public class FileNetDocuments {
	@JsonProperty("applicationNumber")
	private String applicationNumber;
	@JsonProperty("documents")
	private List<Document> documents = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("applicationNumber")
	public String getApplicationNumber() {
		return applicationNumber;
	}

	@JsonProperty("applicationNumber")
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	@JsonProperty("documents")
	public List<Document> getDocuments() {
		return documents;
	}

	@JsonProperty("documents")
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
