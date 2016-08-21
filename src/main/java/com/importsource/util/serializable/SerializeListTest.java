package com.importsource.util.serializable;

import java.util.ArrayList;
import java.util.List;

public class SerializeListTest {

	 
	public static void main(String[] args) {
		TranscoderProxy transcoder=new TranscoderProxy();
		List<User> users=new ArrayList<>();
		users.add(new User("messi"));
		users.add(new User("xavi"));
		byte[] usersBytes=transcoder.serialize(users);
		List<User> resultUsers=(List<User>)transcoder.deserialize(usersBytes);
		System.out.println(resultUsers.size());
		
		byte[] userBytes=transcoder.serialize(new User("inesta"));
		User resultUser=(User)transcoder.deserialize(userBytes);
		System.out.println(resultUser.getName());

	}
	
	

}
