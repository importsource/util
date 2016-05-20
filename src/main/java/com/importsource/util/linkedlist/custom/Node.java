package com.importsource.util.linkedlist.custom;

/**
 * 
 * @author Hezf
 *
 * @param <T>
 */
public class Node<T> {
	private T element;
	private Node<T> next;
	private Node<T> pre;

	

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node<T> getPre() {
		return pre;
	}

	public void setPre(Node<T> pre) {
		this.pre = pre;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}
}
