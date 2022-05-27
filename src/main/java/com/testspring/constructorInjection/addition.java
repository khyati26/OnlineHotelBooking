package com.testspring.constructorInjection;

public class addition {
	private int a;
	private int b;
	
	public addition(int a,int b) {
		this.a=a;
		this.b=b;
		System.out.println("constructor int");
	}
	public addition(double a,double b) {
		this.a= (int)a;
		this.b=(int)b;
		System.out.println("constructor double");
	}
	public void Sum() {
		System.out.println("Sum = "+(this.a+this.b));
	}
}
