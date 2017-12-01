package com.dycd.handler;

public interface MessageHandler<T> {
	public void dealMessage(String topic, T t);
}
