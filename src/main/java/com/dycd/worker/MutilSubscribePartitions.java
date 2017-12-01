package com.dycd.worker;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.dycd.core.ConsumerEngineBootServer;
import com.dycd.core.impl.ConsumerEngineBootStrapImpl;
import com.dycd.handler.MessageHandler;
import org.apache.kafka.clients.consumer.Consumer;


public class MutilSubscribePartitions implements Runnable {
	private final AtomicBoolean closed = new AtomicBoolean(false);
	private final String topic;
	private final int [] partitions;
    private final Consumer consumer;
    private final MessageHandler handler;
    private final ConsumerEngineBootServer bootServer;
    
    public MutilSubscribePartitions(Consumer consumer, MessageHandler handler, String topic, int ...partitions){
    	this.consumer = consumer;
    	this.handler = handler;
    	this.topic = topic;
    	this.partitions = partitions;
    	this.bootServer = new ConsumerEngineBootStrapImpl(consumer, handler);
    }
    @Override
    public void run() {
        bootServer.subscribePartition(topic, partitions);
        bootServer.pollMessages(closed);
    }

    // Shutdown hook which can be called from a separate thread
    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }
}
