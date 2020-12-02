package br.com.dao;

import java.util.List;

public interface InterfaceDAO <T> {	
public boolean insert(T t);	
public T findById(int id);
public List<T> findAll(); 		
public boolean update(T t);
public boolean delete(T t);
	
}
