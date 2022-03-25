//package com.grokProject.entity;
//
//import java.io.IOException;
//import java.util.Properties;
//
//public class ConsumerPro {
//    ConfigPro configPro = new ConfigPro();
//
//    public ConsumerPro() throws IOException {
//    }
//
//    public Properties consumerProperties() throws IOException {
//        Properties properties = configPro.readProperties();
//        String CONSUMERBROKERSLIST = properties.getProperty("consumerBrokersList");
//        String CONSUMERGROUPID = properties.getProperty("consumerGroupid");
//        Properties consumerProperties = new Properties();
//        consumerProperties.put("bootstrap.servers", CONSUMERBROKERSLIST);
//        consumerProperties.put("group.id", CONSUMERGROUPID);
//        consumerProperties.put("enable.auto.commit", "false");
//        consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        return consumerProperties;
//    }
//}
