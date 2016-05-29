package com.importsource.util.xio.nio.datagramchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

public class DatagramChannelReveiver {
	public static void main(String[] args) {
		try {
			receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void receive() throws IOException {
		DatagramChannel channel = DatagramChannel.open();
		channel.socket().bind(new InetSocketAddress(8888));
		ByteBuffer buffer = ByteBuffer.allocate(100);
		while (channel.receive(buffer) == null) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		buffer.flip();
		String recStr = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
		System.out.println(recStr);
		channel.close();
	}
}