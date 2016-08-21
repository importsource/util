package com.importsource.util.kyro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;

/**
 * 序列化类
 * @author Hezf
 */
public class SerializeUtil {
	public static byte[] toBytes(Object obj) {
		Kryo kryo = new Kryo();
		kryo.setReferences(false);
		// kryo.setRegistrationRequired(false);
		Output output = null;
		OutputStream out;
		byte[] userBytes = null;
		try {
			out = new ByteArrayOutputStream();
			output = new Output(out);

			kryo.writeClassAndObject(output, obj);
			userBytes = output.toBytes();
		} catch (Exception e) {
			Log.TRACE();
		} finally {
			if(output!=null){
				output.close();
			}
			
		}
		return userBytes;
	}
	
	public static Object toObject(byte[] bs) {
		Input input = null;
		Object obj = null;
		try {
			Kryo kryo1 = new Kryo();
			kryo1.setReferences(false);
			
			kryo1.setRegistrationRequired(false);
			input = null;
			input = new Input(new ByteArrayInputStream(bs), 10);
			obj = null;
			obj =  kryo1.readClassAndObject(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(input!=null){
				input.close();
			}
		}
		return obj;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		User user = new User();
		user.setName("importsource");

		byte[] userBytes = toBytes(user);

		User userResult = (User) toObject(userBytes);
		System.out.println(userResult.getName());
		
		
		List<User> list=new ArrayList<User>();
		list.add(user);
		
		byte[] listBytes=toBytes(list);
		@SuppressWarnings("unchecked")
		List<User> listResult = (List<User>) toObject(listBytes);
		System.out.println(listResult.get(0).getName());
		
		
		Map<String,User> map=new HashMap<String,User>();
		map.put("user", user);
		
		byte[] mapBytes=toBytes(map);
		@SuppressWarnings("unchecked")
		Map<String,User> mapResult = (Map<String,User>) toObject(mapBytes);
		System.out.println(mapResult.get("user").getName());

	}
	
	
	static class User  {
		private String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}

}
