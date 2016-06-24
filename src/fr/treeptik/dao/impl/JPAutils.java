package fr.treeptik.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.treeptik.pojo.GenericPojo;

public class JPAutils {
	//Singleton
	private static EntityManager en;
	
	public static EntityManager getEn() {
		if (en==null || !en.isOpen()) {
			en = Persistence.
					createEntityManagerFactory("sample").createEntityManager();
		}
		return en;
	}
	
	
	public static void begin(){
		if (en==null || !en.isOpen()) {
			en = Persistence.
					createEntityManagerFactory("sample").createEntityManager();
		}
		en.getTransaction().begin();
	}
	
	
	public static void commit(){
		en.getTransaction().commit();
	}
	
	
	
	public static void rollback(){
		en.getTransaction().rollback();
	}
	
	
	public static GenericPojo query(TypedQuery<GenericPojo> query){
		if (en==null || !en.isOpen()) {
			en = Persistence.
					createEntityManagerFactory("sample").createEntityManager();
		}
		return (GenericPojo) query.getSingleResult();
	}
	public static void flush() {
		en.flush();
	}
	@SuppressWarnings("unchecked")
	public static List<GenericPojo> lQuery(Query query){
		if (en==null || !en.isOpen()) {
			en = Persistence.
					createEntityManagerFactory("sample").createEntityManager();
		}
		return (List<GenericPojo>) query.getResultList();
	}

}
