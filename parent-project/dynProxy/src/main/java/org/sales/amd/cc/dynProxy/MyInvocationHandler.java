package org.sales.amd.cc.dynProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	 private Object obj;

	    public static Object newInstance(Object obj) {
	        return java.lang.reflect.Proxy.newProxyInstance(
	            obj.getClass().getClassLoader(),
	            obj.getClass().getInterfaces(),
	            new MyInvocationHandler(obj));
	    }

	    private MyInvocationHandler(Object obj) {
	        this.obj = obj;
	    }

	    public Object invoke(Object proxy, Method m, Object[] args)
	        throws Throwable
	    {
	        Object result;
	        try {
	            System.out.println("before method " + m.getName());
	            result = m.invoke(obj, args);
	        } catch (InvocationTargetException e) {
	            throw e.getTargetException();
	        } catch (Exception e) {
	            throw new RuntimeException("unexpected invocation exception: " +
	                                       e.getMessage());
	        } finally {
	            System.out.println("after method " + m.getName());
	        }
	        return result;
	    }

}
