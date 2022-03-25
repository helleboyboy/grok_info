//package com.grokProject;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.grokProject.entity.Model02;
//import com.grokProject.entity.OutputModel;
//import com.grokProject.entity.ProduceConsumerProperties;
//import io.krakens.grok.api.Grok;
//import io.krakens.grok.api.GrokCompiler;
//import io.krakens.grok.api.Match;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Map;
//import java.util.Properties;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.atomic.AtomicLong;
//
//public class OtherRunIt {
////    static final String PATH = "D:\\software-data\\idea_history\\testIt\\grokProject\\src\\main\\resources\\config1.properties";
////    /*
////    corePoolSize,maximumPoolSize,keepAliveTime,consumerBrokersList,consumerGroupid,consumerTopic,
////    consumerPollTime,consumerNum,produceBrokersList,produceTopic,runTimes,grokPattern,dateType
////     */
////    public static Properties readProperties() throws IOException {
////        Properties properties = new Properties();
////        BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));
////        properties.load(bufferedReader);
////        return properties;
////    }
//    public static void main(String[] args) throws IOException {
////        Properties properties = readProperties();
////        System.out.println(properties.getProperty("corePoolSize"));
//
//
//    }
//
//    static AtomicBoolean finish1 = new AtomicBoolean(true); // 判断是否结束
//    static LinkedBlockingQueue<String> linkedBlockingQueue1 = new LinkedBlockingQueue<String>(); //不同的队列，根据不同的topic进入不同的队列
//    static AtomicLong count = new AtomicLong(0L);
//    static LinkedBlockingQueue proLinkedBlockingQueue1 = new LinkedBlockingQueue();
//
//
//
//    public Runnable consumptionDetails() throws IOException {
//        Properties producerLocalPro = new ProduceConsumerProperties().producerProperties();
//        KafkaProducer<String, String> producer1 = new KafkaProducer<>(producerLocalPro);
//        return () -> {
//            while (finish1.get()) {
//                String value = linkedBlockingQueue1.poll();
//                if (value != null) {
//                    Model02 model02 = JSON.parseObject(value, Model02.class);
//                    String message = model02.getMessage();
//                    GrokCompiler grokCompiler = GrokCompiler.newInstance();
//                    grokCompiler.registerDefaultPatterns();
//                    Grok grok = grokCompiler.compile("%{TIMESTAMP_ISO8601:time1}");
//                    Match match = grok.match(message);
//                    Map<String, Object> capture = match.capture();
//                    String time = (String) capture.get("time1");
//                    if (time != null){
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
//                        Date date = null;
//                        try {
//                            date = simpleDateFormat.parse(time);
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                        long ts = date.getTime() / 1000L;
//                        long keyCount =count.getAndIncrement();
//                        String key = String.valueOf(keyCount);
//                        String source = model02.getSource();
//                        String host = model02.getHost().getName();
//                        OutputModel outputModel = new OutputModel(message,ts,source,host);
//                        String output = JSONObject.toJSONString(outputModel);
//                        System.out.println(output);
//                        proLinkedBlockingQueue1.offer(output);
//                        if (key != null || outputModel.getMessage() != null){
//                            producer1.send(new ProducerRecord<String, String>("datanode_table1", key, output));
////                        produce_count.getAndIncrement();
//                        }
//                    }else {
//                        continue;
//                    }
//                }
//            }
//        };
//    }
//
//
//
//}
