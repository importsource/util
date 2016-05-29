package com.importsource.util.xio.nio.datagramchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelSender {
	public static void main(String[] args) {
		try {
			send();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void send() throws IOException {
		DatagramChannel channel = DatagramChannel.open();
		ByteBuffer buffer = ByteBuffer.wrap("这个夏天不太热".getBytes("UTF-8"));
		channel.send(buffer, new InetSocketAddress("localhost", 8888));
		channel.close();
	}
}