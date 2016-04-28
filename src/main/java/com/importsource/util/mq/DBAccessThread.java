package com.importsource.util.mq;

public class DBAccessThread extends AbstractThread {
	public DBAccessThread(String msg) {
		this.msg = msg;
	}
	
	public DBAccessThread() {
		super();
	}

	@Override
	public void doIt() {
		System.out.println("Added the message: " + msg + " into the Database");

	}

}
