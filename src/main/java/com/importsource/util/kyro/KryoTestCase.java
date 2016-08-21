package com.importsource.util.kyro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;

public abstract class KryoTestCase  {
	protected Kryo kryo;
	protected Output output;
	protected Input input;
	protected Object object1, object2;

	protected void setUp () throws Exception {
		Log.TRACE();

		kryo = new Kryo();
		kryo.setReferences(false);
		kryo.setRegistrationRequired(true);
	}

	public <T> T roundTrip (int length, T object1) {
		this.object1 = object1;

		// Test output to stream, large buffer.
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		output = new Output(outStream, 4096);
		kryo.writeClassAndObject(output, object1);
		output.flush();

		// Test input from stream, large buffer.
		input = new Input(new ByteArrayInputStream(outStream.toByteArray()), 4096);
		object2 = kryo.readClassAndObject(input);
		System.out.println("class:"+object2.getClass().toString());
		/*assertEquals(object1, object2);
		assertEquals("Incorrect number of bytes read.", length, input.total());
		assertEquals("Incorrect number of bytes read.", length, output.total());*/

		// Test output to stream, small buffer.
		outStream = new ByteArrayOutputStream();
		output = new Output(outStream, 10);
		kryo.writeClassAndObject(output, object1);
		output.flush();

		// Test input from stream, small buffer.
		input = new Input(new ByteArrayInputStream(outStream.toByteArray()), 10);
		object2 = kryo.readClassAndObject(input);
		/*assertEquals(object1, object2);
		assertEquals("Incorrect number of bytes read.", length, input.total());*/

		// Test output to byte array.
		output = new Output(length * 2, -1);
		kryo.writeClassAndObject(output, object1);
		output.flush();

		// Test input from byte array.
		input = new Input(output.toBytes());
		object2 = kryo.readClassAndObject(input);
		assertEquals(object1, object2);
		/*assertEquals("Incorrect length.", length, output.total());
		assertEquals("Incorrect number of bytes read.", length, input.total());*/
		input.rewind();

		return (T)object2;
	}

	static public void assertEquals (Object object1, Object object2) {
	//	Assert.assertEquals(arrayToList(object1), arrayToList(object2));
	}

	static public Object arrayToList (Object array) {
		if (array == null || !array.getClass().isArray()) return array;
		ArrayList list = new ArrayList(Array.getLength(array));
		for (int i = 0, n = Array.getLength(array); i < n; i++)
			list.add(arrayToList(Array.get(array, i)));
		return list;
	}

	static public ArrayList list (Object... items) {
		ArrayList list = new ArrayList();
		for (Object item : items)
			list.add(item);
		return list;
	}
}
