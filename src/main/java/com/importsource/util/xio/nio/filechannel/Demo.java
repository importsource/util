package com.importsource.util.xio.nio.filechannel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class Demo {
	private static RandomAccessFile randomAccessFile;

	public static void main(String[] args) {
		try {
			randomAccessFile = new RandomAccessFile(new File("C:/Users/Hezf/Documents/HdfsBlobInfo.java"), "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileChannel fileChannel = randomAccessFile.getChannel();
		boolean flag = true;
		List<ByteBuffer> buffers = new ArrayList<ByteBuffer>();

		read(fileChannel, buffers);

		for (int i = 0; i < buffers.size(); i++) {
			ByteBuffer byteBuffer = buffers.get(i);
			System.out.println(new String(byteBuffer.array()));
		}
		
		String newData = "New String to write to file..." + System.currentTimeMillis();

		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());

		buf.flip();

		while(buf.hasRemaining()) {
			try {
				fileChannel.write(buf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static void read(FileChannel fileChannel, List<ByteBuffer> buffers) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(200);
		try {
			int result = fileChannel.read(byteBuffer);
			buffers.add(byteBuffer);
			if (result != -1) {
				read(fileChannel, buffers);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
