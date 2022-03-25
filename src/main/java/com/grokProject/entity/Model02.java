package com.grokProject.entity;

import com.alibaba.fastjson.annotation.JSONField;
public class Model02 {
    //    offset,host,fields,logtype,collector_node_id,
//    gl2_source_collector,source,beat,hostname,
//    name,prospector,message,input,@timestamp,@version,tags
    private Integer offset;
    private Host host;
    private Fields fields;
    private String source;
    private Beat beat;
    private String hostname;
    private String name;
    private String prospector;
    private String message;
    private String input;
    @JSONField(name = "@timestamp")
    private String timestamp;
    @JSONField(name = "@version")
    private String version;
    private String tags;

    @Override
    public String toString() {
        return "Model02{" +
                "offset=" + offset +
                ", host=" + host +
                ", fields=" + fields +
                ", source='" + source + '\'' +
                ", beat=" + beat +
                ", hostname='" + hostname + '\'' +
                ", name='" + name + '\'' +
                ", prospector='" + prospector + '\'' +
                ", message='" + message + '\'' +
                ", input='" + input + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", version='" + version + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Beat getBeat() {
        return beat;
    }

    public void setBeat(Beat beat) {
        this.beat = beat;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProspector() {
        return prospector;
    }

    public void setProspector(String prospector) {
        this.prospector = prospector;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Model02() {
    }

    public Model02(Integer offset, Host host, Fields fields, String source, Beat beat, String hostname, String name, String prospector, String message, String input, String timestamp, String version, String tags) {
        this.offset = offset;
        this.host = host;
        this.fields = fields;
        this.source = source;
        this.beat = beat;
        this.hostname = hostname;
        this.name = name;
        this.prospector = prospector;
        this.message = message;
        this.input = input;
        this.timestamp = timestamp;
        this.version = version;
        this.tags = tags;
    }
}