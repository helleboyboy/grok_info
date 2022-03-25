package com.grokProject.entity;


import java.io.IOException;
import java.util.Properties;

public class ProducerPro {
    ConfigPro configPro = new ConfigPro();

    public ProducerPro() throws IOException {
    }

    public Properties producerProperties() throws IOException {
        Properties properties = configPro.readProperties();
        Properties producerProperties = new Properties();
        producerProperties.put("bootstrap.servers", properties.getProperty("produceBrokersList"));
        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return producerProperties;
    }



}
