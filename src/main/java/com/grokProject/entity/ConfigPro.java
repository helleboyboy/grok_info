package com.grokProject.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;


public class ConfigPro {
    /**
     * 提供获取fileProperties对象的属性；
     */
//    Properties fileProperties = new Properties(); // 配置文件
//    Properties locationProperties = new Properties();
    static String CONFIGURABLE_LOCATION = "D:\\software-data\\idea_history\\testIt\\system_in_pro\\location_pro.properties";
//    static String CONFIGURABLE_LOCATION = "/opt/aspire/bigdata/grokProject/bin/location_pro.properties";
    static Properties firstProperties = new Properties();
    static Properties secondProperties = new Properties();

//    // 获取location_pro配置文件
//    Properties toLocationProperties() throws IOException {
////        Properties locationProperties = new Properties();
//        first.load(new BufferedReader(new FileReader(CONFIGURABLE_LOCATION)));
//        return first;
//    }
    private static final Logger logger = LoggerFactory.getLogger(ConfigPro.class);

    public static Properties readProperties() {
        // 获取配置文件的path,将path值的文件传递给reader
        logger.info("配置文件读取开始");
        try {
            logger.info("配置文件读取: " + CONFIGURABLE_LOCATION);
            firstProperties.load(new BufferedReader(new FileReader(CONFIGURABLE_LOCATION)));
        } catch (IOException e) {
            logger.error(CONFIGURABLE_LOCATION + " has no founded!");
            e.printStackTrace();
        }
        Reader reader = null;
        try {
            reader = new FileReader(firstProperties.getProperty("location"));
        } catch (FileNotFoundException e) {
            logger.error(CONFIGURABLE_LOCATION + " 配置文件存在缺失location字段");
            e.printStackTrace();
        }
        BufferedReader bufferedReader1 = new BufferedReader(reader);
//        Properties fileProperties = new Properties();
        try {
            secondProperties.load(bufferedReader1);
        } catch (IOException e) {
            logger.error(CONFIGURABLE_LOCATION + " 配置文件location字段有误 # 主配置文件有误");
            e.printStackTrace();
        }
//        System.out.println(firstProperties.getProperty("location"));
        return secondProperties;
    }
}
