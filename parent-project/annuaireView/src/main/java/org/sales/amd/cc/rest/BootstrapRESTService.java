package org.sales.amd.cc.rest;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;


public class BootstrapRESTService extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	
	public BootstrapRESTService() {
		RestService restSvc = new RestService();
		restSvc.setWhoami(UUID.randomUUID());
		
		//proxy dyn
		
		CorsFilter corsFilter = new CorsFilter();
	    corsFilter.getAllowedOrigins().add("*");
	    
	    singletons.add(corsFilter);
		singletons.add(restSvc);
	}
	
	@Override
	public Set<Object> getSingletons(){
		return singletons;
	}
	
}