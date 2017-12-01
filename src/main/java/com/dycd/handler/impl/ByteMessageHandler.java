package com.dycd.handler.impl;


import com.dycd.handler.MessageHandler;

public class ByteMessageHandler implements MessageHandler<byte[]> {
	@Override
	public void dealMessage(String topic, byte[] t) {
		// TODO Auto-generated method stub
		System.out.println(new String(t));
	}

}
