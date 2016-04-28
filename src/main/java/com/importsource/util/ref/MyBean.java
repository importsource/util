package com.importsource.util.ref;

public class MyBean {
	private String id = null;
	private String userName = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getXXX(){
		return this.id;
	}

}
