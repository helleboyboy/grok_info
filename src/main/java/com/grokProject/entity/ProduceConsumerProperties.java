package com.grokProject.entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class ProduceConsumerProperties {
    private static final Logger logger = LoggerFactory.getLogger(ConfigPro.class);
//    ConfigPro configPro = new ConfigPro();

    static Properties pro;
    static {
        logger.info("读取配置文件！");
        pro = new ConfigPro().readProperties();
    }

    public ProduceConsumerProperties(){
    }

    /**
     * @TIPS 获取消费者配置参数
     * @return
     * @throws IOException
     */
    public Properties consumerProperties(){

//        Properties properties = configPro.readProperties();
        String consumerBrokersList = pro.getProperty("consumerBrokersList");
        String consumerGroupid = pro.getProperty("consumerGroupid");

        Properties consumerProperties = new Properties();
        consumerProperties.put("bootstrap.servers", consumerBrokersList);
        consumerProperties.put("group.id", consumerGroupid);
        consumerProperties.put("enable.auto.commit", "false");
        consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return consumerProperties;
    }

    /**
     * @TIPS 生产者给broker生产数据
     * @return 生产者的broker信息
     * @throws IOException
     */
    public Properties producerProperties() throws IOException {
//        Properties properties = configPro.readProperties();
        String produceBrokersList = pro.getProperty("produceBrokersList");

        Properties producerProperties = new Properties();
        producerProperties.put("bootstrap.servers", produceBrokersList);
        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return producerProperties;
    }
}
