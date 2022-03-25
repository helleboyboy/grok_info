package com.grokProject.entity;

public class BlockReport {
    private String timestamp;
    private String block;
    private String path;
    private String blockpool;
    private String hostname;

    public BlockReport() {
    }

    public BlockReport(String timestamp, String block, String path, String blockpool, String hostname) {
        this.timestamp = timestamp;
        this.block = block;
        this.path = path;
        this.blockpool = blockpool;
        this.hostname = hostname;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getBlock() {
        return block;
    }

    public String getPath() {
        return path;
    }

    public String getBlockpool() {
        return blockpool;
    }

    public String getHostname() {
        return hostname;
    }
}

