package com.lucifer.netty.ch4.pool;

import java.nio.channels.ServerSocketChannel;
/**
 * boss接口
 * @author -琴兽-
 *
 */
public interface Boss {
	
	/**
	 * 加入一个新的ServerSocket
	 * @param serverChannel
	 */
	public void registerAcceptChannelTask(ServerSocketChannel serverChannel);
}
