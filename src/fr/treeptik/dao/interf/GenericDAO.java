package fr.treeptik.dao.interf;

import java.util.List;
import java.util.Map;

import fr.treeptik.dao.exception.DAOException;

public interface GenericDAO<T1, K> {
	T1 add(T1 entity) throws DAOException;
	void remove (T1 entity) throws DAOException;
	T1 update(T1 entity) throws DAOException;
	T1 findById(K key) throws DAOException;
//	T1 findByObject(T1 entity) throws DAOException;
	List<T1> findAll() throws DAOException;
	List<T1> find(Map<String, Object> map) throws DAOException;

}
