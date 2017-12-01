package com.dycd.handler.impl;


import com.dycd.handler.MessageHandler;

public class DefaultMessageHandlerImpl<String> implements MessageHandler<String> {

	@Override
	public void dealMessage(java.lang.String topic, String t) {
		// TODO Auto-generated method stub
		System.out.println("topic"+ topic +"-----------"+"value"+t);

	}

}
