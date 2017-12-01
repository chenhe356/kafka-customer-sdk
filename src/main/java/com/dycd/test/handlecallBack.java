package com.dycd.test;

import com.dycd.handler.MessageHandler;

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
        System.out.println("头疼 可咋整");
    }
}
