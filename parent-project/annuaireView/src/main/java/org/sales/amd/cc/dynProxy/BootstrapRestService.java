package org.sales.amd.cc.dynProxy;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

/**
 * Class who initializes all rest service instances. Take care she is referred in the web.xml file
 * @author QGd
 *
 */
public class BootstrapRestService extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	
	public BootstrapRestService() {
		CorsFilter corsFilter = new CorsFilter();
	    corsFilter.getAllowedOrigins().add("*");
	    
	    singletons.add(corsFilter);
	    
	    //ADD HERE YOUR REST SERVICE INSTANCE
		
	}
	
	@Override
	public Set<Object> getSingletons(){
		return singletons;
	}
	
}