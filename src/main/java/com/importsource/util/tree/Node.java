package com.importsource.util.tree;

public class Node {
	
    public Node(Node parent,String name){
    	this.parent=parent;
    	this.name=name;
    }
    private String name;
    
    
    
    public Node parent;
    

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
}
