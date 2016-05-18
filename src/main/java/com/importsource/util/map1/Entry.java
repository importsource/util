package com.importsource.util.map1;

/**
 * 包装kv的实体类
 * @author Hezf
 *
 * @param <K>
 * @param <V>
 */
public class Entry<K, V> {
	private K key;
	private V value;

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

}
