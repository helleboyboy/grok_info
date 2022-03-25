//package com.grokProject;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.grokProject.entity.Model02;
//import com.grokProject.entity.OutputModel;
//import io.krakens.grok.api.Grok;
//import io.krakens.grok.api.GrokCompiler;
//import io.krakens.grok.api.Match;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import java.io.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.util.*;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.concurrent.atomic.AtomicLong;
//public class RunIt {
//    public static Properties readProperties1()  {
//        ClassLoader cl = RunIt.class.getClassLoader();
//        Properties p =new Properties();
//        InputStream in = null;
//        try {
//            if  (cl !=  null ) {
//                in = cl.getResourceAsStream( "config.properties" );
//            }  else {
//                in = ClassLoader.getSystemResourceAsStream( "config.properties" );
//            }
//            p.load(in);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                in.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return p;
//    }
//
//    public static Properties readProperties() throws IOException {
//        Properties properties = new Properties();
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));
//        properties.load(bufferedReader);
//        return properties;
//    }
//
//    public static Properties consumerPro(){
//        Properties consumerPro = new Properties();
////        consumerPro.put("bootstrap.servers", "hebsjzx-jzhjf-jzywgk-104-93:6667,hebsjzx-jzhjf-jzywgk-104-114:6667,hebsjzx-jzhjf-jzywgk-104-98:6667,hebsjzx-jzhjf-jzywgk-104-89:6667");
//        consumerPro.put("bootstrap.servers", CONSUMERBROKERSLIST);
//        consumerPro.put("group.id", CONSUMERGROUPID);
//        consumerPro.put("enable.auto.commit", "false");
//        consumerPro.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        consumerPro.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        return consumerPro;
//    }
//
//    public static Properties producerPro(){
//        Properties producerPro = new Properties();
////        producerPro.put("bootstrap.servers", "hebsjzx-jzhjf-jzywgk-104-93:6667,hebsjzx-jzhjf-jzywgk-104-114:6667,hebsjzx-jzhjf-jzywgk-104-98:6667,hebsjzx-jzhjf-jzywgk-104-89:6667");
//        producerPro.put("bootstrap.servers", PRODUCEBROKERSLIST);
//        producerPro.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        producerPro.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        return producerPro;
//    }
//
//    public static Runnable consumerInfo() {
//        return () -> {
//            while (finish.get()) {
//                String value = linkedBlockingQueue.poll();
//                if (value != null) {
//                    Model02 model02 = JSON.parseObject(value, Model02.class);
//                    String message = model02.getMessage();
//                    GrokCompiler grokCompiler = GrokCompiler.newInstance();
//                    grokCompiler.registerDefaultPatterns();
////                    String grokPattern = "%{TIMESTAMP_ISO8601:time}";
//                    Grok grok = grokCompiler.compile(grokPattern);
//                    Match match = grok.match(message);
//                    Map<String, Object> capture = match.capture();
//                    String time = (String) capture.get("time");
//                    if (time != null){
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateType);
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
//                        proLinkedBlockingQueue.offer(output);
//                        if (key != null || outputModel.getMessage() != null){
//                            producer.send(new ProducerRecord<String, String>(TOPICNAME, key, output));
////                        produce_count.getAndIncrement();
//                        }
//                    }else {
//                        continue;
//                    }
//
//
//                }
//            }
//        };
//    }
//
//    static Properties allProperties;
//    static {
//        try {
//            allProperties = readProperties();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    static final String PATH = "/root/a/config.properties";
////    static final String PATH = "D:\\software-data\\idea_history\\testIt\\grokProject\\src\\main\\resources\\config.properties";
//    static final Integer COREPOLLSIZE = Integer.valueOf(allProperties.getProperty("corePoolSize"));
//    static final Integer MAXIMUMPOOLSIZE = Integer.valueOf(allProperties.getProperty("maximumPoolSize"));
//    static final Long KEEPALIVETIME = Long.valueOf(allProperties.getProperty("keepAliveTime"));
//    static final String  CONSUMERBROKERSLIST = allProperties.getProperty("consumerBrokersList");
//    static final String  CONSUMERGROUPID = allProperties.getProperty("consumerGroupid");
//    static final String  CONSUMERTOPIC = allProperties.getProperty("consumerTopic");
//    static final Integer  CONSUMERPOLLTIME = Integer.valueOf(allProperties.getProperty("consumerPollTime"));
//    static final Integer  CONSUMERNUM = Integer.valueOf(allProperties.getProperty("consumerNum"));
//    static final String  PRODUCEBROKERSLIST = allProperties.getProperty("produceBrokersList");
//    static final String  PRODUCETOPIC = allProperties.getProperty("produceTopic");
//    static final Integer  RUNTIMES = Integer.valueOf(allProperties.getProperty("runTimes"));
//    static final String grokPattern = allProperties.getProperty("grokPattern");
//    static final String dateType = allProperties.getProperty("dateType");
//    final static ThreadPoolExecutor executor = new ThreadPoolExecutor(COREPOLLSIZE, MAXIMUMPOOLSIZE, KEEPALIVETIME, TimeUnit.SECONDS,
//            new LinkedBlockingQueue<>());
//    static LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>();
//    static AtomicLong count = new AtomicLong(0L);
//    static AtomicBoolean finish = new AtomicBoolean(true);
////    static AtomicLong produce_count = new AtomicLong(0L);
//
//    static Properties producerPro = producerPro();
//    static KafkaProducer<String, String> producer = new KafkaProducer(producerPro);
//    static final String TOPICNAME = PRODUCETOPIC;
//    static LinkedBlockingQueue proLinkedBlockingQueue = new LinkedBlockingQueue();
//
//    public static void main(String[] args) {
////        System.out.println(COREPOLLSIZE);
////        System.out.println(MAXIMUMPOOLSIZE);
////        System.out.println(KEEPALIVETIME);
////        System.out.println(CONSUMERBROKERSLIST);
////        System.out.println(CONSUMERGROUPID);
////        System.out.println(CONSUMERTOPIC);
////        System.out.println(CONSUMERPOLLTIME);
////        System.out.println(CONSUMERNUM);
////        System.out.println(PRODUCEBROKERSLIST);
////        System.out.println(PRODUCETOPIC);
////        System.out.println(RUNTIMES);
//        Properties props = consumerPro();
//        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
//        consumer.subscribe(Collections.singletonList(CONSUMERTOPIC));
//        final long CURRENTTIME = System.currentTimeMillis();
//        final long ENDTIME = CURRENTTIME + TimeUnit.MINUTES.toMillis(RUNTIMES);
////        final long ENDTIME = CURRENTTIME + TimeUnit.MINUTES.toMillis(1);
//        String value;
//        int size = 0;
//
//        boolean flag = true;
//        try {
//            int threads = 0;
//            while (threads < MAXIMUMPOOLSIZE) {
//                executor.execute(consumerInfo());
//                threads++;
//            }
////            while (System.currentTimeMillis() < ENDTIME) {
//            while (flag) {
//                while (size < CONSUMERNUM) {
//                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(CONSUMERPOLLTIME));
//                    for (ConsumerRecord<String, String> record : records) {
//                        value = record.value();
//                        linkedBlockingQueue.offer(value);
//                        size++;
//                    }
//                }
//            }
//            consumer.commitAsync();
//            finish.getAndSet(false);
////            System.out.println(count);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            consumer.close();
//            executor.shutdown();
////            System.out.println(count);
////            System.out.println(produce_count);
//        }
//    }
//
//
//}
//
