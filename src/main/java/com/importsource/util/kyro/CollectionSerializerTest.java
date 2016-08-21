package com.importsource.util.kyro;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.DefaultSerializers.StringSerializer;

public class CollectionSerializerTest extends KryoTestCase {
	public void testCollections () {
		kryo.register(ArrayList.class);
		kryo.register(LinkedList.class);
		kryo.register(CopyOnWriteArrayList.class);
		roundTrip(11, list("1", "2", "3"));
		roundTrip(13, list("1", "2", null, 1, 2));
		roundTrip(15, list("1", "2", null, 1, 2, 5));
		roundTrip(11, list("1", "2", "3"));
		roundTrip(11, list("1", "2", "3"));
		roundTrip(13, list("1", "2", list("3")));
		roundTrip(13, new LinkedList(list("1", "2", list("3"))));
		roundTrip(13, new CopyOnWriteArrayList(list("1", "2", list("3"))));

		CollectionSerializer serializer = new CollectionSerializer(kryo);
		kryo.register(ArrayList.class, serializer);
		kryo.register(LinkedList.class, serializer);
		kryo.register(CopyOnWriteArrayList.class, serializer);
		serializer.setElementClass(String.class);
		roundTrip(11, list("1", "2", "3"));
		serializer.setElementClass(String.class, new StringSerializer());
		roundTrip(11, list("1", "2", "3"));
		serializer.setElementsCanBeNull(false);
		roundTrip(8, list("1", "2", "3"));
		serializer.setLength(3);
		roundTrip(7, list("1", "2", "3"));
	}
}
