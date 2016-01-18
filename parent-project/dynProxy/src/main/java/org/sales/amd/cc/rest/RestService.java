package org.sales.amd.cc.rest;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/client")
public class RestService {
	private static Logger logger = LoggerFactory.getLogger(RestService.class);
	private UUID whoami;
	
	
	@GET
	public Response startSimulation(){
		
		return Response.status(200).entity("it's work, I'M "+this.whoami.toString().substring(0, 5)).build();
	}


	public UUID getWhoami() {
		return whoami;
	}


	public void setWhoami(UUID whoami) {
		this.whoami = whoami;
	}
	
	

	
}
