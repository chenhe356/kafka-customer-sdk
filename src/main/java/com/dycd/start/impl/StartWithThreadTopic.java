package com.dycd.start.impl;

import java.util.List;

import com.dycd.config.ConsumerInit;
import com.dycd.core.ConsumerEngineBootServer;
import com.dycd.core.impl.ConsumerEngineThreadImpl;
import com.dycd.handler.MessageHandler;
import com.dycd.handler.impl.DefaultMessageHandlerImpl;
import com.dycd.start.StartWithTopicInterface;
import com.dycd.worker.MutilSubscribeTopic;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;


public class StartWithThreadTopic<K, V> implements StartWithTopicInterface {

	private ConsumerEngineBootServer<K, V> booServer;
	private MessageHandler<V> handler;
	private Consumer<K, V> consumer;
	
	@Override
	public void start(List<String> topicList) {
		handler = new DefaultMessageHandlerImpl<>();
		consumer = new KafkaConsumer<>(ConsumerInit.CONSUMER_PROP);
		booServer = new ConsumerEngineThreadImpl<>(consumer, handler);
		
		MutilSubscribeTopic threadTopic = new MutilSubscribeTopic(consumer, handler, topicList);
		threadTopic.run();
	}
}
