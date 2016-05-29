package com.importsource.util.xio.nio.datagramchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Demo {

	public static void main(String[] args) throws IOException {
		DatagramChannel channel = DatagramChannel.open();
		channel.socket().bind(new InetSocketAddress(9999));
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		channel.receive(buf);
		
		
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf1 = ByteBuffer.allocate(48);
		buf1.clear();
		buf1.put(newData.getBytes());
		buf1.flip();
		int bytesSent = channel.send(buf, new InetSocketAddress("localhost", 80));

	}

}
