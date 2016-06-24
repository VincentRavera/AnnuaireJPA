package fr.treeptik.service.interf;

import java.util.List;
import java.util.Map;

import fr.treeptik.service.exception.ServiceException;

public interface GenericService<T1, K> {
	T1 add(T1  entity) throws ServiceException;
	void remove(T1 entity) throws ServiceException;
	T1 update(T1 entity) throws ServiceException;
	T1 findById(K key) throws ServiceException;
	List<T1> findAll() throws ServiceException;
	List<T1> find(Map<String, Object> map) throws ServiceException;

}
