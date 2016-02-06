package org.sales.amd.cc.rest;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
	@Path("/email/{email}")
	public Client getByEmail(@PathParam(value = "email") String email){
		return manager.getClientByEmail(email);
	}
	
	@POST
	@Path("/create")
	public Client create(@FormParam("name") String name, @FormParam("entreprise") String entreprise, @FormParam("email") String email){
		return manager.createOrUpdateClient(new Client(name, entreprise, email));
	}
	
	@POST
	@Path("/update")
	public Client update(@FormParam("name") String name, @FormParam("entreprise") String entreprise, @FormParam("email") String email){
		return manager.createOrUpdateClient(new Client(name, entreprise, email));
	}
	
	@POST
	@Path("/delete")
	public Client delete(@FormParam("name") String name, @FormParam("entreprise") String entreprise, @FormParam("email") String email){
		return manager.delete(email);
	}

//	@GET
//	@Produces("application/json")
//	public Collection<Client> getAll(){
//		
//		Collection<Client> rsl = new ArrayList<Client>();
//		rsl.add(new Client("name", "city", "email"));
//		rsl.add(new Client("name1", "city1", "email1"));
//		
//		return rsl;
//	}
	
}
