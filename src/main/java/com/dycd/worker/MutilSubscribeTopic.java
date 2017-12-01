package com.dycd.worker;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.dycd.core.ConsumerEngineBootServer;
import com.dycd.core.impl.ConsumerEngineBootStrapImpl;
import com.dycd.handler.MessageHandler;
import org.apache.kafka.clients.consumer.Consumer;


public class MutilSubscribeTopic implements Runnable {
	private final AtomicBoolean closed = new AtomicBoolean(false);
	private final List<String> topicList;
    private final Consumer consumer;
    private final MessageHandler handler;
    private final ConsumerEngineBootServer bootServer;
    
    public MutilSubscribeTopic(Consumer consumer, MessageHandler handler, List<String> topicList){
    	this.consumer = consumer;
    	this.handler = handler;
    	this.topicList = topicList;
    	this.bootServer = new ConsumerEngineBootStrapImpl(consumer, handler);
    }
    @Override
    public void run() {
        bootServer.subscribeTopic(topicList);
        bootServer.pollMessages(closed);
    }

    // Shutdown hook which can be called from a separate thread
    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }
}
