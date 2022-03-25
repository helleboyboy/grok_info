package com.grokProject.entity;

public class OutputModel {
    private String message;
    private Long ts;
    private String source;
    private String host;

//    @Override

    @Override
    public String toString() {
        return "OutputModel{" +
                "message='" + message + '\'' +
                ", ts=" + ts +
                ", source='" + source + '\'' +
                ", host='" + host + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public OutputModel() {
    }

    public OutputModel(String message, Long ts, String source, String host) {
        this.message = message;
        this.ts = ts;
        this.source = source;
        this.host = host;
    }
//    public String toString() {
//        return "OutputModel{" +
//                "message='" + message + '\'' +
//                ", ts=" + ts +
//                ", source='" + source + '\'' +
//                ", host='" + host + '\'' +
//                '}';
//    }
}