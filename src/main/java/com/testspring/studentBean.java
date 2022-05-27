package com.testspring;

public class studentBean {
	private int id;
	private String name;
	
	
	public studentBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public studentBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
//		System.out.println("in id setter");
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
//		System.out.println("in name setter");
		this.name = name;
	}
	@Override
	public String toString() {
		return "studentBean [id=" + id + ", name=" + name + "]";
	}
	
}
