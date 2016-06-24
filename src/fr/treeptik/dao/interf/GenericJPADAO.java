package fr.treeptik.dao.interf;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;


import fr.treeptik.dao.exception.DAOException;
import fr.treeptik.dao.impl.JPAutils;

public class GenericJPADAO<T, K> implements GenericDAO<T, K>{
	EntityManager en = JPAutils.getEn();
	private Class<T> type;
	
	

	public GenericJPADAO(Class<T> type) {
		this.type = type;
	}

	@Override
	public T add(T entity) throws DAOException {
		try {
			en.persist(entity);
			return entity;
		} catch (PersistenceException e) {
			throw new DAOException(
					"ERREUR GenricJPADAO add()" + e.getMessage(), e);
		}
	}

	@Override
	public void remove(T entity) throws DAOException {
		try {
			en.remove(entity);
		} catch (PersistenceException e) {
			throw new DAOException(
					"ERREUR GenricJPADAO remove()" + e.getMessage(), e);
		}
	}

	@Override
	public T update(T entity) throws DAOException {
		try {
			en.merge(entity);
			return entity;
		} catch (PersistenceException e) {
			throw new DAOException(
					"ERREUR GenricJPADAO update()" + e.getMessage(), e);
		}
	}

	@Override
	public T findById(K key) throws DAOException {
		try {
			TypedQuery<T> query = en.createQuery(""
					+ "SELECT e FROM "
					+type.getSimpleName()
					+" WHERE e.id=:key",
					type);
			query.setParameter("key", key);
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new DAOException(
					"ERREUR GenricJPADAO findById()" + e.getMessage(), e);
		}
	}

	@Override
	public List<T> findAll() throws DAOException {
		TypedQuery<T> query = null;
		try {
			query = en.createQuery(
					"SELECT e FROM "+type.getSimpleName()+" e ", type);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException(
					"ERREUR GenricJPADAO findAll()" + e.getMessage(), e);
		}
	}

	@Override
	public List<T> find(Map<String, Object> map) throws DAOException {
		try {
			
			String querry;
			TypedQuery<T> inteligentQuerry;
			querry = new String(
					"SELECT o FROM "+ type.getSimpleName()+ " o ");
			
			if (map != null && map.size()>0) {
				querry += " WHERE ";
				Set<String> key = map.keySet();
				for (String string : key) {
					querry += "o." + string + "=:" + string + " AND ";
				}
				querry = querry.substring(0, querry.lastIndexOf("AND "));
				
				inteligentQuerry = en.createQuery(querry, type);
				for (String string : key) {
					inteligentQuerry.setParameter(string, map.get(string));
				}
			}else {
				inteligentQuerry = en.createQuery(querry, type);
			}
			return inteligentQuerry.getResultList();
			
		} catch (PersistenceException|NullPointerException e) {
			throw new DAOException(
					"ERREUR GenricJPADAO find(Map)" + e.getMessage(), e);
		}
		
		
		
		
	}

}
