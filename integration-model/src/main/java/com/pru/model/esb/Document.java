package com.pru.model.esb;




import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "type",
    "desc",
    "comments",
    "filename",
    "format",
    "contentType",
    "extension",
    "compression",
    "encryption",
    "tags",
    "url",
    "content",
    "auditDetail"
})
public class Document {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("comments")
    private String comments;
    @JsonProperty("filename")
    private String filename;
    @JsonProperty("format")
    private String format;
    @JsonProperty("contentType")
    private String contentType;
    @JsonProperty("extension")
    private String extension;
    @JsonProperty("compression")
    private String compression;
    @JsonProperty("encryption")
    private String encryption;
    @JsonProperty("tags")
    private Tags tags;
    @JsonProperty("url")
    private String url;
    @JsonProperty("content")
    private String content;
    @JsonProperty("auditDetail")
    private AuditDetail auditDetail;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("desc")
    public String getDesc() {
        return desc;
    }

    @JsonProperty("desc")
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JsonProperty("comments")
    public String getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(String comments) {
        this.comments = comments;
    }

    @JsonProperty("filename")
    public String getFilename() {
        return filename;
    }

    @JsonProperty("filename")
    public void setFilename(String filename) {
        this.filename = filename;
    }

    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    @JsonProperty("format")
    public void setFormat(String format) {
        this.format = format;
    }

    @JsonProperty("contentType")
    public String getContentType() {
        return contentType;
    }

    @JsonProperty("contentType")
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @JsonProperty("extension")
    public String getExtension() {
        return extension;
    }

    @JsonProperty("extension")
    public void setExtension(String extension) {
        this.extension = extension;
    }

    @JsonProperty("compression")
    public String getCompression() {
        return compression;
    }

    @JsonProperty("compression")
    public void setCompression(String compression) {
        this.compression = compression;
    }

    @JsonProperty("encryption")
    public String getEncryption() {
        return encryption;
    }

    @JsonProperty("encryption")
    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    @JsonProperty("tags")
    public Tags getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Tags tags) {
        this.tags = tags;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("auditDetail")
    public AuditDetail getAuditDetail() {
        return auditDetail;
    }

    @JsonProperty("auditDetail")
    public void setAuditDetail(AuditDetail auditDetail) {
        this.auditDetail = auditDetail;
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
