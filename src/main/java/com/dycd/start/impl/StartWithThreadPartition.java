package com.dycd.start.impl;

import com.dycd.config.ConsumerInit;
import com.dycd.core.ConsumerEngineBootServer;
import com.dycd.core.impl.ConsumerEngineThreadImpl;
import com.dycd.handler.MessageHandler;
import com.dycd.handler.impl.DefaultMessageHandlerImpl;
import com.dycd.start.StartWithPartitionInterface;
import com.dycd.worker.MutilSubscribePartitions;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class StartWithThreadPartition<K, V> implements StartWithPartitionInterface {

	private ConsumerEngineBootServer<K, V> booServer;
	private MessageHandler<V> handler;
	private Consumer<K, V> consumer;
	
	public StartWithThreadPartition(){
		handler = new DefaultMessageHandlerImpl<>();
	}
	
	public StartWithThreadPartition(MessageHandler<V> handler){
		this.handler = handler;
		if(handler == null){
			handler = new DefaultMessageHandlerImpl<>();
		}
	}
	@Override
	public void start(String topic, int... partition) {
		consumer = new KafkaConsumer<>(ConsumerInit.CONSUMER_PROP);
		booServer = new ConsumerEngineThreadImpl<>(consumer, handler);
		
		MutilSubscribePartitions threadPartition = new MutilSubscribePartitions(consumer, handler, topic, partition);
		threadPartition.run();
	}
}
