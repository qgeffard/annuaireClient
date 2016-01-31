package org.sales.amd.cc.dynProxy;

import java.util.Map;

import org.sales.amd.cc.entity.Client;

/**
 * @author QGd
 * date : 31 janv. 2016 - 19:08:52
 *
 * modified by
 * 
 */
public class Main {
	
	public static void main(String[] args) {
		Map<String, Client> context = ContextManager.getContext();
		
		context.put("test", new Client());
		System.out.println(context.get("test"));
	}

}
