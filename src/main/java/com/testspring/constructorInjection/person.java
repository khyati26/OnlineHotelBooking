package com.testspring.constructorInjection;

public class person {
	private int id;
	private String name;
	private Certi certi;
	
	public person(int id,String name,Certi certi) {
		this.id=id;
		this.name=name;
		this.certi=certi;
	}

	@Override
	public String toString() {
		return "person [id=" + this.id + ", name=" + this.name + ", certi= "+ this.certi.name +"]";
	}
	
	
	
}
