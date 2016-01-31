package org.sales.amd.cc.rest;

import java.util.Collection;
import java.util.Map;

import org.sales.amd.cc.dynProxy.ContextManager;
import org.sales.amd.cc.entity.Client;

/**
 * @author QGd
 * date : 31 janv. 2016 - 18:29:58
 *
 * modified by
 * 
 */
public class ClientManager {
	Map<String, Client> context;
	
	public ClientManager() {
		context = ContextManager.getContext();
	}

	public Collection<Client> getAllClient() {
		return context.values();
	}
	
	public Client getClientByEmail(String email) {
		return context.get(email);
	}
	
	public Client createOrUpdateClient(Client client) {
		return context.put(client.getEmail(), client);
	}
	

	public Client delete(Client client) {
		return context.remove(client.getEmail());
	}
	
	
//	public Client updateClient(Client client) {
//		Client clientNeedUpdate = context.get(client.getEmail());
//		clientNeedUpdate.setEnterprise(client.getEnterprise());
//		clientNeedUpdate.setName(client.getName());
//		return context.put(client.getEnterprise(),clientNeedUpdate);
//	}

}
