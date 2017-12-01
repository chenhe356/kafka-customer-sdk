package com.dycd.start.impl;

import java.util.List;

import com.dycd.config.ConsumerInit;
import com.dycd.core.ConsumerEngineBootServer;
import com.dycd.core.impl.ConsumerEngineBootStrapImpl;
import com.dycd.handler.MessageHandler;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;


public class StartWithTopic {

	private ConsumerEngineBootServer<String, String> booServer;
	private MessageHandler<String> handler;
	private Consumer<String, String> consumer;
	
	public StartWithTopic(MessageHandler<String> handler){
		this.handler = handler;
	}
	
	public void start(List<String> listTopic) {
		consumer = new KafkaConsumer<>(ConsumerInit.CONSUMER_PROP);
		booServer = new ConsumerEngineBootStrapImpl<>(consumer, handler);
		booServer.subscribeTopic(listTopic);
	}
}
