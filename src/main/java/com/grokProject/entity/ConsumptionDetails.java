package com.grokProject.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.krakens.grok.api.Grok;
import io.krakens.grok.api.GrokCompiler;
import io.krakens.grok.api.Match;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class ConsumptionDetails {
    static LinkedBlockingQueue<String> linkedBlockingQueue1 = new LinkedBlockingQueue<String>(); //不同的队列，根据不同的topic进入不同的队列
    AtomicBoolean finish1 = new AtomicBoolean(true); // 判断是否结束
    AtomicLong count = new AtomicLong(0L);
    LinkedBlockingQueue<String> proLinkedBlockingQueue1 = new LinkedBlockingQueue();
    Properties producerLocalPro;

    {
        try {
            producerLocalPro = new ProduceConsumerProperties().producerProperties();
        } catch (IOException e) {
            logger.error("kafka生产配置文件配置出错！");
            e.printStackTrace();
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(ConsumptionDetails.class);


    KafkaProducer<String, String> producer1 = new KafkaProducer<>(producerLocalPro);


    // 判断是否需要添加到结果字段
    boolean isSource = false;
    boolean isHost = false;
    boolean isMessage = false;
    boolean isOffset = false;
    boolean isFields = false;
    boolean isBeat = false;
    boolean isProspector = false;
    boolean isInput = false;
    boolean isTimestamp = false;
    boolean isVersion = false;
    boolean isTags = false;

    public ConsumptionDetails(){
    }

    /**
     * 特定的grok模板
     * @return
     * @throws IOException
     */
//    public Runnable consumptionInfos() throws IOException {
//        return () -> {
//            Properties configPro = null;
//            try {
//                configPro = new ConfigPro().readProperties();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            while (finish1.get()) {
//                String value = linkedBlockingQueue1.poll();
//                if (value != null) {
//                    Model02 model02 = JSON.parseObject(value, Model02.class);
//                    String message = model02.getMessage();
//                    GrokCompiler grokCompiler = GrokCompiler.newInstance();
//                    grokCompiler.registerDefaultPatterns();
//                    String grokPattern = configPro.getProperty("grokPattern");
//                    Grok grok = grokCompiler.compile(grokPattern);
////                    Grok grok = grokCompiler.compile("%{TIMESTAMP_ISO8601:time1}");
//                    Match match = grok.match(message);
//                    Map<String, Object> capture = match.capture();
//                    String time = (String) capture.get("time1");
//                    String dateType = configPro.getProperty("dateType");
//                    String produceTopic = configPro.getProperty("produceTopic");
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
//                        proLinkedBlockingQueue1.offer(output);
//                        if (key != null || outputModel.getMessage() != null){
//                            producer1.send(new ProducerRecord<String, String>(produceTopic , key, output));
////                        produce_count.getAndIncrement();
//                        }
//                    }else {
//                        continue;
//                    }
//                }
//            }
//        };
//    }

    static Properties configPro;

    static {
        logger.info("读取配置 - 消费配置！");
        configPro = ConfigPro.readProperties();
    }


    public Runnable consumptionInfos_blockreport() {
        return () -> {

            // 装载字段的值
            String logstash_source = null;
            String logstash_message = null;
            Integer logstash_offset = null;
            String logstash_hostname = null;

            Host logstash_host = null;
//    String logstash_host_name = null;

            Fields logstash_fields = null;
//    String logstash_fields_logtype = null;
//    String logstash_fields_gl2_source_collector = null;
//    String logstash_fields_collector_node_id = null;
//    String logstash_fields_type = null;

            Beat logstash_beat = null;
//    String logstash_beat_version = null;
//    String logstash_beat_Hostname = null;
//    String logstash_beat_Name = null;

            String logstash_prospector = null;
            String logstash_input = null;
            String logstash_timestamp = null;
            String logstash_version = null;
            String logstash_tags = null;

            String logstash_beatHostname = null;
            String logstash_beatName = null;
            String logstash_beatVersion = null;
            String logstash_logtype = null;
            String logstash_collector_node_id = null;
            String logstash_type = null;
            String logstash_gl2_source_collector = null;
//            try {
//                configPro = ConfigPro.readProperties();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            String dateType = configPro.getProperty("dateType");
//            String timeColum = configPro.getProperty("timeColum");
            // 获取解析里面所需的字段！
            String[] parseFileds = configPro.getProperty("parseColum").split(",");
            // 读取配置文件来决定 是否添加字段到结果发送到下一级！！！
            isSource = Boolean.parseBoolean(configPro.getProperty("logstash_source"));
            isHost = Boolean.parseBoolean(configPro.getProperty("logstash_host"));
            isMessage = Boolean.parseBoolean(configPro.getProperty("logstash_message"));
            isOffset = Boolean.parseBoolean(configPro.getProperty("logstash_offset"));
            isFields = Boolean.parseBoolean(configPro.getProperty("logstash_fields"));
            isBeat = Boolean.parseBoolean(configPro.getProperty("logstash_beat"));
            isProspector = Boolean.parseBoolean(configPro.getProperty("logstash_prospector"));
            isInput = Boolean.parseBoolean(configPro.getProperty("logstash_input"));
            isTimestamp = Boolean.parseBoolean(configPro.getProperty("logstash_timestamp"));
            isVersion = Boolean.parseBoolean(configPro.getProperty("logstash_version"));
            isTags = Boolean.parseBoolean(configPro.getProperty("logstash_tags"));
            while (finish1.get()) {
                String value = linkedBlockingQueue1.poll();
                // 如果队列中没有数据，则跳过！！！
                if (value != null) {
                    // offset,fields,beat,prospector,input,timestamp,version,tags
                    Model02 model02 = JSON.parseObject(value, Model02.class);
                    logstash_message = model02.getMessage();
                    logstash_source = model02.getSource();

                    logstash_host = model02.getHost();
                    logstash_hostname = logstash_host.getName();
                    logstash_offset = model02.getOffset();

                    logstash_fields = model02.getFields();
                    logstash_logtype = logstash_fields.getLogtype();
                    logstash_collector_node_id = logstash_fields.getCollector_node_id();
                    logstash_type = logstash_fields.getType();
                    logstash_gl2_source_collector = logstash_fields.getGl2_source_collector();

                    logstash_beat = model02.getBeat();
                    logstash_beatHostname = logstash_beat.getBeatHostname();
                    logstash_beatName = logstash_beat.getBeatName();
                    logstash_beatVersion = logstash_beat.getBeatVersion();

                    logstash_prospector = model02.getProspector();
                    logstash_input = model02.getInput();
                    logstash_timestamp = model02.getTimestamp();
                    logstash_version = model02.getVersion();
                    logstash_tags = model02.getTags();


                    GrokCompiler grokCompiler = GrokCompiler.newInstance();
                    grokCompiler.registerDefaultPatterns();
                    String grokPattern = configPro.getProperty("grokPattern");
                    String produceTopic = configPro.getProperty("produceTopic");
                    Grok grok = grokCompiler.compile(grokPattern);
                    Match match = grok.match(logstash_message);
                    //存储着grok模板的值。
                    Map<String, Object> capture = match.capture();
                    JSONObject jsonObject = (JSONObject) JSONObject.toJSON(capture);
                    // 先清空jsonObject的元素
                    jsonObject.clear();
                    // 从capture获取 parsefiled中包含的字段值
                    for (String parseFiled : parseFileds) {
                        jsonObject.put(parseFiled,(String) capture.get(parseFiled));
                    }
                    //获取定义名
//                    String time = (String) capture.get(timeColum);
                    // 获取conversion字段值
                    String conversion = configPro.getProperty("conversion");
                    String time = null;
                    // 如果conversion为null则不进行解析！！！
                    if (conversion != null || !conversion.equals("")){
                        time = (String) capture.get(conversion);
//                        time = (String) jsonObject.get(conversion);
                        if (time != null){
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateType);
                            Date date = null;
                            try {
                                date = simpleDateFormat.parse(time);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            long ts = date.getTime() / 1000L;
                            // 先把 解析的旧字段值去掉
                            jsonObject.remove(conversion);
                            jsonObject.put(conversion,String.valueOf(ts));

                        }
                    }else {
                        break;
                    }
                    /**
                     * 根据配置文件的字段决定是否向下游发送数据
                     */
                    if (isSource){
                        jsonObject.put("source",logstash_source);
                    }
                    if (isHost){
//                                jsonObject.put("logstash_host",logstash_host);
                        jsonObject.put("host",logstash_hostname);
                    }
                    if (isMessage){
                        jsonObject.put("message",logstash_message);
                    }
                    if (isOffset){
                        jsonObject.put("logstash_offset",logstash_offset);
                    }
                    if (isFields){
                        jsonObject.put("logstash_logtype",logstash_logtype);
                        jsonObject.put("logstash_collector_node_id",logstash_collector_node_id);
                        jsonObject.put("logstash_type",logstash_type);
                        jsonObject.put("logstash_gl2_source_collector",logstash_gl2_source_collector);
                    }
                    if (isBeat){
//                                jsonObject.put("logstash_beat",logstash_beat);
                        jsonObject.put("logstash_beatHostname",logstash_beatHostname);
                        jsonObject.put("logstash_beatName",logstash_beatName);
                        jsonObject.put("logstash_beatVersion",logstash_beatVersion);
                    }
                    if (isProspector){
                        jsonObject.put("logstash_prospector",logstash_prospector);
                    }
                    if (isInput){
                        jsonObject.put("logstash_input",logstash_input);
                    }
                    if (isTimestamp){
                        jsonObject.put("logstash_timestamp",logstash_timestamp);
                    }
                    if (isVersion){
                        jsonObject.put("logstash_version",logstash_version);
                    }
                    if (isTags){
                        jsonObject.put("logstash_tags",logstash_tags);
                    }
                    if (!jsonObject.toJSONString().equals("{}")){
                        String input = jsonObject.toJSONString();
                        System.out.println(input);

                        long keyCount =count.getAndIncrement();
                        String key = String.valueOf(keyCount);
//                        proLinkedBlockingQueue1.offer(input);

                        producer1.send(new ProducerRecord<String, String>(produceTopic , key, input));
                        logger.info("数据发送成功！主题 -->  " + produceTopic);
//                        if (key != null || input != null){
//                            producer1.send(new ProducerRecord<String, String>(produceTopic , key, input));
//                        }
                    }
                }
            }




        };
    }



}
