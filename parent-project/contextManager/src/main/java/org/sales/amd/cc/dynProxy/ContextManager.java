package org.sales.amd.cc.dynProxy;

import java.util.List;
import java.util.Map;

import org.sales.amd.cc.entity.Client;

public class ContextManager {
	private static Map<String, Client> context = (Map<String, Client>) ReplicatedMapHandler.newInstance(new ReplicatedMap());;

	public static Map<String, Client> getContext() {
		return context;
	}
	
}
