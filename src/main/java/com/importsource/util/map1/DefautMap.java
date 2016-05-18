package com.importsource.util.map1;


/**
 * 默认的map
 * @author Hezf
 *
 * @param <K>
 * @param <V>
 */
public class DefautMap<K, V> implements Map<K, V> {
	Entry<?, ?>[] entrySet = new Entry<?, ?>[100];
    private int index=0;
    
	public void put(K key, V value) {
		Entry<K, V> entry = new Entry<K, V>();
		entry.setKey(key);
		entry.setValue(value);

		entrySet[index] = entry;
		index++;
	}

	public V get(K key) {
		return get1(key);
	}

	private V get1(K key) {
        //loop the array,then find the entry object
		V result=null;
		for(int i=0;i<entrySet.length;i++){
			Entry<K, V> entry=(Entry<K, V>) entrySet[i];
			K key1=entry.getKey();
			if(key1.equals(key)){
				result=entry.getValue();
				break;
			}
			
		}
		return result;
	}

}
