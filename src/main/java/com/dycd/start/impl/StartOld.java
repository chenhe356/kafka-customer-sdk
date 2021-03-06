package com.dycd.start.impl;

import java.util.List;


import com.dycd.config.ConsumerInit;
import com.dycd.core.ConsumerEngine;
import com.dycd.start.StartWithTopicInterface;
import kafka.serializer.Decoder;
import kafka.serializer.StringDecoder;

public class StartOld implements StartWithTopicInterface {
	@Override
	public void start(List<String> topicList) {
		for (String topic : topicList) {
			ConsumerEngine<String, String> ce = new ConsumerEngine<String, String>(topic,
					Integer.parseInt(ConsumerInit.PARTITION_STATEGY));
			Decoder<String> stringDecoder = new StringDecoder(null);
			ce.run(stringDecoder, stringDecoder);
		}
	}
}
