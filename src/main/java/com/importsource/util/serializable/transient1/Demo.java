package com.importsource.util.serializable.transient1;

import java.io.*;

public class Demo {
	public static void main(String[] args) throws Exception {
		writeObj();
		readObj();
	}

	public static void writeObj() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.txt"));

		Person p = new Person("zhangsan", 22, "kr", "country1");

		oos.writeObject(p);
		oos.close();

	}

	public static void readObj() throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.txt"));

		Person p = (Person) (ois.readObject());

		sop(p.toString());

	}

	public static void sop(Object obj) {
		System.out.println(obj);
	}
}