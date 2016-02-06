package org.sales.amd.cc.dynProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReplicatedMapHandler implements InvocationHandler {
	private static Logger logger = LoggerFactory.getLogger(ReplicatedMapHandler.class);
	private Object obj;

	public static Object newInstance(Object obj) {
		Class<?>[] interfaces = obj.getClass().getSuperclass().getInterfaces();
		return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(), interfaces , new ReplicatedMapHandler(obj));
	}

	private ReplicatedMapHandler(Object obj) {
		this.obj = obj;
	}

	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		for (int i = 0; i < obj.getClass().getDeclaredMethods().length; i++) {
			logger.debug(obj.getClass().getDeclaredMethods()[i].getName());
		}
		
		Object result;
		try {
			logger.debug("before method " + m.getName());
			result = m.invoke(obj, args);
			ContextManager.send_multicast_message(m.getName() + ": \"" + args[0] + "\" => " + args[1]);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		} catch (Exception e) {
			throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
		} finally {
			logger.debug("after method " + m.getName());
		}
		return result;
	}

}
