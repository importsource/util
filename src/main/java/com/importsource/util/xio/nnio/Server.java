package com.importsource.util.xio.nnio;

import java.io.IOException;

public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		 int port = 8080;          
		 new Thread(new ServerReactor(port)).start();
	}
}