package com.dycd.test;

import com.dycd.handler.MessageHandler;
import net.sf.json.JSON;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author ${user}
 * @date 2017/11/30
 * @time 上午11:18
 **/
public class handlecallBack implements MessageHandler {
    @Override
    public void dealMessage(String topic, Object o) {
        Map<String,Object> params = new HashMap<>(16);
        params.put("customer","customer");
        params.put("topic",topic);
        params.put("value",o);
        String flag = com.alibaba.fastjson.JSON.toJSONString(params);
        System.out.println(flag);
     }
}
