package org.sales.amd.cc.dynProxy;

import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sales.amd.cc.entity.Client;

/**
 * @author QGd
 * date : 31 janv. 2016 - 19:02:35
 *
 * modified by
 * 
 */
public class ContextManagerTest {
	static Map<String, Client> context;
	
	@BeforeClass
	public static void beforeClass(){
		context = ContextManager.getContext();
	}
	
	@Test
	public void should_insert_3_Client(){
		Client client1 = new Client("client1", "city_client1", "client1_email@gmail.com");
		Client client2 = new Client("client2", "city_client2", "client2_email@gmail.com");
		Client client3 = new Client("client3", "city_client3", "client3_email@gmail.com");
		
		context.put(client1.getEmail(), client1);
		context.put(client2.getEmail(), client2);
		context.put(client3.getEmail(), client3);
		
		Assert.assertEquals(3, context.size());
	}
	
}
