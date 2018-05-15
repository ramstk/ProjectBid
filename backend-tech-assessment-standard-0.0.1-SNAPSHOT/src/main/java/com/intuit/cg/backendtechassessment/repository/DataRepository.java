package com.intuit.cg.backendtechassessment.repository;

import java.util.List;

public interface DataRepository<T> {
	public List<T> findAll();

	public T findById(long id);

	public int insert(T item);

}
