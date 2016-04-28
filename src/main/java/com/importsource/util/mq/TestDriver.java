package com.importsource.util.mq;

public class TestDriver {
	ThreadPoolManager tpm = ThreadPoolManager.newInstance(new DBAccessThread());

	public void sendMsg(String msg) {
	//	tpm.addLogMsg(msg + "记录一条日志 ");
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new TestDriver().sendMsg(Integer.toString(i));
		}

	}
}