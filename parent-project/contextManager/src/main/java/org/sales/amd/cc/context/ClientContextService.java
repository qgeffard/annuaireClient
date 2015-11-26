package org.sales.amd.cc.context;

import java.util.ArrayList;
import java.util.List;

import org.sales.amd.cc.entity.Client;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class ClientContextService implements IContextService<Client> {
	private CacheManager cacheManager;
	private Cache cache;

	public ClientContextService() {
		ClassLoader classLoader = getClass().getClassLoader();
		cacheManager = CacheManager
				.newInstance(classLoader.getResource("ehcache.xml").getFile());
		cache = cacheManager.getCache("clientsCache");
		System.out.println("ready: " + cache.getName());
	}

	@Override
	public Client get(String ref) {
		// TODO Auto-generated method stub
		Client client = null;
		if (cache.isKeyInCache(ref)) {
			Element elem = cache.get(ref);
			client = (Client) elem.getObjectValue();
		}
		return client;
	}

	@Override
	public void put(Client entity) {
		// TODO Auto-generated method stub
		if (cache != null)
			cache.put(new Element(entity.getEmail(), entity));
	}

	@Override
	public void update(Client entity) {
		// TODO Auto-generated method stub
		if (cache != null) {
			if (cache.isKeyInCache(entity.getEmail())) {
				cache.acquireWriteLockOnKey(entity.getEmail());
				cache.remove(entity.getEmail());
				cache.put(new Element(entity.getEmail(), entity));
				cache.releaseWriteLockOnKey(entity.getEmail());
			} else
				cache.put(new Element(entity.getEmail(), entity));
		}
	}

	@Override
	public void delete(String ref) {
		// TODO Auto-generated method stub
		if (cache.isKeyInCache(ref)) {
			cache.remove(ref);
		}
	}

	@Override
	public List<Client> searchAll() {
		List<Client> clients = new ArrayList<Client>();
		for (Object key : cache.getKeys()) {
			Element element = cache.get(key);
			if (element != null) {
				clients.add((Client) element.getObjectValue());
			}
		}
		return clients;
	}

}
