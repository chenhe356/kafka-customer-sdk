package com.dycd.start.impl;

import com.dycd.config.ConsumerInit;
import com.dycd.core.ConsumerEngineBootServer;
import com.dycd.core.impl.ConsumerEngineBootStrapImpl;
import com.dycd.handler.MessageHandler;
import com.dycd.handler.impl.DefaultMessageHandlerImpl;
import com.dycd.start.StartWithPartitionInterface;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;


public class StartWithPartitions<K, V> implements StartWithPartitionInterface {
	
	private ConsumerEngineBootServer<K, V> booServer;
	private MessageHandler<V> handler;
	private Consumer<K, V> consumer;
	
	public StartWithPartitions(){
		handler = new DefaultMessageHandlerImpl<>();
	}
	
	public StartWithPartitions(MessageHandler<V> handler){
		this.handler = handler;
		if(handler == null) {
			handler = new DefaultMessageHandlerImpl<>();
		}
	}
	@Override
	public void start(String topic, int ...partitons) {
		handler = new DefaultMessageHandlerImpl<>();
		consumer = new KafkaConsumer<>(ConsumerInit.CONSUMER_PROP);
		booServer = new ConsumerEngineBootStrapImpl<>(consumer, handler);
		
		booServer.subscribePartition(topic, partitons);
	}
}
