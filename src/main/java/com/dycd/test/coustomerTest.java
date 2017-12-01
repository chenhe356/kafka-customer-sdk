package com.dycd.test;

import com.dycd.handler.MessageHandler;
import com.dycd.handler.impl.DefaultMessageHandlerImpl;
import com.dycd.start.impl.StartWithThreadPartition;
import com.dycd.start.impl.StartWithThreadTopic;
import com.dycd.start.impl.StartWithTopic;

import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author ${user}
 * @date 2017/11/28
 * @time 下午2:51
 **/
public class coustomerTest {
    public static void main(String[] args) {
//        MessageHandler handler = new DefaultMessageHandlerImpl();
//        MessageHandler handler = new handlecallBack();
//        StartWithThreadTopic startWithTopic = new StartWithThreadTopic();
//        List<String> topic = new ArrayList<>();
//        topic.add("fuck");
//        startWithTopic.start(topic);
        MessageHandler handler = new handlecallBack();
        StartWithTopic startWithThreadPartition = new StartWithTopic(handler);
        List<String> list = new ArrayList<>();
        list.add("fuck");
        startWithThreadPartition.start(list);
    }
}
