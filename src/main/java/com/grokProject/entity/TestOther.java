package com.grokProject.entity;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestOther {
    public static void main(String[] args) throws IOException {
        final Logger logger = LoggerFactory.getLogger(ConfigPro.class);
        // 获取到kafka生产者和消费者的属性，并进行配置
        ProduceConsumerProperties produceConsumerProperties = new ProduceConsumerProperties();
        ConsumptionDetails consumptionDetails = new ConsumptionDetails();

//        Properties fileProperties = new ConfigPro().readProperties();
        Properties fileProperties = ConfigPro.readProperties();
        Integer corePoolSize = Integer.valueOf(fileProperties.getProperty("corePoolSize"));
        Integer maximumPoolSize = Integer.valueOf(fileProperties.getProperty("maximumPoolSize"));
        Long keepAliveTime = Long.valueOf(fileProperties.getProperty("keepAliveTime"));
        String consumerTopic = fileProperties.getProperty("consumerTopic");
//        Long runTimes = Long.valueOf(fileProperties.getProperty("runTimes"));
//        Long consumerNum = Long.valueOf(fileProperties.getProperty("consumerNum"));

        logger.info("kafka 消费 " + consumerTopic + " topic , 所用资源是：corePoolSize为 " + corePoolSize + " ， maximumPoolSize为 " + maximumPoolSize);
        Properties consumerPro = produceConsumerProperties.consumerProperties();
//        Properties consumerPro = produceConsumerProperties.consumerProperties();
        //用不同的方法来进行调用不同的grok！！！！！！
//        Runnable task = consumptionDetails.consumptionInfos();
        Runnable task = consumptionDetails.consumptionInfos_blockreport();


        final ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerPro);
        consumer.subscribe(Collections.singletonList(consumerTopic));
//        final long CURRENTTIME = System.currentTimeMillis();
//        final long ENDTIME = CURRENTTIME + TimeUnit.MINUTES.toMillis(runTimes);
        String value;
        int size = 0;

        boolean flag = true;
        LinkedBlockingQueue<String> linkedBlockingQueue1 = consumptionDetails.linkedBlockingQueue1;
        try {
            int threads = 0;
            while (threads < maximumPoolSize) {
                executor.execute(task);
                threads++;
            }

            while (flag) {
//                while (size < consumerNum) {
                    ConsumerRecords<String, String> records = consumer.poll(100);

                    if (records.count() != 0){
//                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                        for (ConsumerRecord<String, String> record : records) {
                            value = record.value();
                            linkedBlockingQueue1.offer(value);
                            size++;
                        }
                    }
            }
            consumer.commitAsync();
            consumptionDetails.finish1.getAndSet(false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
            while (linkedBlockingQueue1.size() == 0){
                executor.shutdown();
            }
        }

    }
}
