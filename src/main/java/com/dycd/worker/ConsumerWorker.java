package com.dycd.worker;


import com.dycd.handler.MessageHandler;
import com.dycd.handler.impl.DefaultMessageHandlerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

public class ConsumerWorker<K, V> implements Runnable {

	private KafkaStream<K, V> mStream;
	private int threadNo;

	private final static Logger log = LoggerFactory.getLogger(ConsumerWorker.class);

	public ConsumerWorker(KafkaStream mStream, int threadNo) {
		this.mStream = mStream;
		this.threadNo = threadNo;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		MessageHandler<V> msgHandler = new DefaultMessageHandlerImpl();
		ConsumerIterator it = mStream.iterator();
		while (it.hasNext()) {
			MessageAndMetadata<K, V> meta = it.next();
			msgHandler.dealMessage(meta.topic(), (V) meta.message());
			log.info("thread:" + threadNo + " get message:" + meta.message());
		}
	}

}
