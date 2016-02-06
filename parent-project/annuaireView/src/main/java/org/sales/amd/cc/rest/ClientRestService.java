package org.sales.amd.cc.rest;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.sales.amd.cc.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/client")
public class ClientRestService {
	private static Logger logger = LoggerFactory.getLogger(ClientRestService.class);
	private ClientManager manager;
	
	public ClientRestService() {
		manager = new ClientManager();
	}
	
	@GET
	@Produces("application/json")
	public Collection<Client> getAll(){
		return manager.getAllClient();
	}
	
	@GET
	@Produces("application/json")
	@Path("/{email}")
	public Client getByEmail(@PathParam(value = "email") String email){
		return manager.getClientByEmail(email);
	}
	
	@POST
	@Consumes("application/json")
	public Client createOrUpdate(Client client){
		return manager.createOrUpdateClient(client);
	}
	
	@POST
	@Consumes("application/json")
	public Client delete(Client client){
		return manager.delete(client);
	}

	
}
