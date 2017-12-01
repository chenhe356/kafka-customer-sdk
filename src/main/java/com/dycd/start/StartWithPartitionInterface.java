package com.dycd.start;

public interface StartWithPartitionInterface {
	public void start(String topic, int ...partitions);
}
