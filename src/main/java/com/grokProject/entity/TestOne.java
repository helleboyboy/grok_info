package com.grokProject.entity;
import com.alibaba.fastjson.JSONObject;
import io.krakens.grok.api.Grok;
import io.krakens.grok.api.GrokCompiler;
import io.krakens.grok.api.Match;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * test
 */
public class TestOne {
    static Map<String,Object> newMap = new HashMap();
    public static void main(String[] args) throws IOException {
//        Map<String, Object> hashMap = new HashMap<>();
//        hashMap.put("1",1);
//        hashMap.put("2",2);
//        hashMap.put("3",3);
//        hashMap.put("4",4);
//        hashMap.put("5",5);
//        if (hashMap.get("5") != null) {
//            hashMap.put("5",new Integer(10));
//            hashMap.put("5","i am string ??? ");
//        }
//        JSONObject jsonObject = new JSONObject(hashMap);
//        System.out.println(jsonObject.toJSONString());



        Properties properties = new Properties();
        BufferedReader bufferedReader =
                new BufferedReader(
                        new FileReader("D:\\software-data\\idea_history\\testIt\\system_in_pro\\text.properties")
                );
        properties.load(bufferedReader);
        String items = properties.getProperty("items");
        String values = properties.getProperty("values");
        String[] value = values.split(",");
        String[] items_i = items.split(",");
       //demo02
        String message = "2022-01-20 14:58:23,282 slava 12345469870";
        GrokCompiler grokCompiler = GrokCompiler.newInstance();
        grokCompiler.registerDefaultPatterns();
        String grokPattern = "%{TIMESTAMP_ISO8601:time} %{NOTSPACE:money} %{BASE10NUM:num}";
        ArrayList<String> fileds = new ArrayList<>();
        for (String s : items_i) {
//        for (String s : value) {
            fileds.add(s);
        }
        Grok grok = grokCompiler.compile(grokPattern);
        Match match = grok.match(message);
        Map<String, Object> capture = match.capture();
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(capture);
        // 先清空数据！！
        jsonObject.clear();
        // list和hashmap的交集，完成对字段的提取！！！
        for (String filed : fileds) {
            jsonObject.put(filed, (String) capture.get(filed));
        }
        if (jsonObject.get("hello") == null){
            System.out.println("hello is null");
        }else {
            System.out.println(jsonObject.get("hello"));
        }


        // 查看结果如何
//        for (String s : jsonObject.keySet()) {
//            System.out.println(s + "的值为： " + jsonObject.get(s));
//        }






    }
}
