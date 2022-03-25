package com.grokProject.entity;
public class Fields {
    private String logtype;
    private String gl2_source_collector;
    private String collector_node_id;
    private String type;

    @Override
    public String toString() {
        return "Fields{" +
                "logtype='" + logtype + '\'' +
                ", gl2_source_collector='" + gl2_source_collector + '\'' +
                ", collector_node_id='" + collector_node_id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getLogtype() {
        return logtype;
    }

    public void setLogtype(String logtype) {
        this.logtype = logtype;
    }

    public String getGl2_source_collector() {
        return gl2_source_collector;
    }

    public void setGl2_source_collector(String gl2_source_collector) {
        this.gl2_source_collector = gl2_source_collector;
    }

    public String getCollector_node_id() {
        return collector_node_id;
    }

    public void setCollector_node_id(String collector_node_id) {
        this.collector_node_id = collector_node_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Fields() {
    }

    public Fields(String logtype, String gl2_source_collector, String collector_node_id, String type) {
        this.logtype = logtype;
        this.gl2_source_collector = gl2_source_collector;
        this.collector_node_id = collector_node_id;
        this.type = type;
    }
}
