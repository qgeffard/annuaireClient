package org.sales.amd.cc.dynProxy;

public class MyProxy {
	
	public static void main(String[] args) {
		
	    MyInterface foo = (MyInterface) MyInvocationHandler.newInstance(new MyInterfaceImpl());
	    foo.maMethod();
	}
	
	
}
