package org.sales.amd.cc.context;

import java.util.List;

public interface IContextService<E> {
	public E get(String ref);
	public void put(E entity);
	public void update(E entity);
	public void delete(String ref);
	public List<E> searchAll();
}
