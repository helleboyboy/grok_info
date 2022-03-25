package com.grokProject.entity;


import com.alibaba.fastjson.annotation.JSONField;
public class Beat {
    @JSONField(name = "version")
    private String beatVersion;
    @JSONField(name = "Hostname")
    private String beatHostname;
    @JSONField(name = "Name")
    private String beatName;

    @Override
    public String toString() {
        return "Beat{" +
                "beatVersion='" + beatVersion + '\'' +
                ", beatHostname='" + beatHostname + '\'' +
                ", beatName='" + beatName + '\'' +
                '}';
    }

    public String getBeatVersion() {
        return beatVersion;
    }

    public void setBeatVersion(String beatVersion) {
        this.beatVersion = beatVersion;
    }

    public String getBeatHostname() {
        return beatHostname;
    }

    public void setBeatHostname(String beatHostname) {
        this.beatHostname = beatHostname;
    }

    public String getBeatName() {
        return beatName;
    }

    public void setBeatName(String beatName) {
        this.beatName = beatName;
    }

    public Beat() {
    }

    public Beat(String beatVersion, String beatHostname, String beatName) {
        this.beatVersion = beatVersion;
        this.beatHostname = beatHostname;
        this.beatName = beatName;
    }
}
