package com.importsource.util.linkedlist.custom;

/**
 * @author Hezf
 * @param <T>
 */
public class LinkedList<T> implements List<T> {
    protected Node<T> first;
    protected Node<T> last;
    
    protected Node<T> now;
    
    private int index;
	
	public void add(T element) {
		addLast(element);
	}

	private void addLast(T element) {
		
		if(index==0){
			Node<T> node=new Node<T>();
			node.setElement(element);
			node.setPre(null);
			first=node;
			now=first;
			index++;
		}else{
			Node<T> node=new Node<T>();
			node.setElement(element);
			node.setPre(now);
			now.setNext(node);
			now=node;
			index++;
		}
	    
		
	}
	
	

	public T get(int index) {
		if(index==0){
		  return first.getElement();
		}else{
			Node<T> tmp=first;
			for(int i=0;i<index;i++){
				tmp=tmp.getNext();
			}
			return tmp.getElement();
		}
		
	}

	public int size() {
		return index;
	}
}
