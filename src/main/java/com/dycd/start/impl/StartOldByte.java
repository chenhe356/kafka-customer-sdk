package com.dycd.start.impl;

import java.util.List;

import com.dycd.config.ConsumerInit;
import com.dycd.core.ConsumerEngine;
import com.dycd.start.StartWithTopicInterface;
import kafka.serializer.Decoder;
import kafka.serializer.DefaultDecoder;

public class StartOldByte implements StartWithTopicInterface {
	@Override
	public void start(List<String> topicList) {
		for(String topic : topicList){
			ConsumerEngine<byte[], byte[]> ce = new ConsumerEngine<byte[], byte[]>(topic, Integer.parseInt(ConsumerInit.PARTITION_STATEGY));
			Decoder<byte[]> decoder = new DefaultDecoder(null);
			ce.run(decoder, decoder);
		}
	}

}
