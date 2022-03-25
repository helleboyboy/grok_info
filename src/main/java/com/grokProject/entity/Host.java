package com.grokProject.entity;

public class Host {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Host() {
    }

    public Host(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Host{" +
                "name='" + name + '\'' +
                '}';
    }
}