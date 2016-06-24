package fr.treeptik.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import fr.treeptik.dao.exception.DAOException;
import fr.treeptik.dao.interf.GenericJPADAO;
import fr.treeptik.dao.interf.PersonneDAO;
import fr.treeptik.pojo.Personne;

public class PersonneDAOJPQL extends GenericJPADAO<Personne, Integer> implements PersonneDAO{
	

	EntityManager en = JPAutils.getEn();
	
	
	
	public PersonneDAOJPQL() {
		super(Personne.class);
	}
	
	
	
	
	
	@Override
	public List<Personne> findByName(String nom) throws DAOException{
		try{
			TypedQuery<Personne> findIdQuerry = en.createQuery(
					"SELECT DISTINCT p FROM Personne p "
					+ "JOIN FETCH p.numeros "
					+ "WHERE p.nom=:nom ",
//					+ "GROUP BY p.id",
							Personne.class);
			findIdQuerry.setParameter("nom", nom);
			return findIdQuerry.getResultList();
		}catch(PersistenceException e){
			throw new DAOException(
					"Erreur PersonneDAOJPQL findByName()"+e.getMessage(), e);
			
		}
	}

	@Override
	public List<Personne> findByFName(String prenom) throws DAOException{
		try{
			TypedQuery<Personne> findIdQuerry = en.createQuery(
					"SELECT DISTINCT p FROM Personne p "
					+ "JOIN FETCH p.numeros "
					+ "WHERE p.prenom= :prenom ",
//					+ "GROUP BY p.id",
							Personne.class);
			findIdQuerry.setParameter("prenom", prenom);
			return findIdQuerry.getResultList();
		}catch(PersistenceException e){
			throw new DAOException(
					"FATAL ERROR !!!! PersonneDAOJPQL findByFName()"+e.getMessage(), e);
			
		}
	}

	@Override
	public List<Personne> findByBirth(Date naissance) throws DAOException{
		try{
			TypedQuery<Personne> findIdQuerry = en.createQuery(
					"SELECT DISTINCT p FROM Personne p "
					+ "JOIN FETCH p.numeros "
					+ "WHERE p.dateNaissance=:date ",
//					+ "GROUP BY p.id",
							Personne.class);
			findIdQuerry.setParameter("date", naissance);
			return findIdQuerry.getResultList();
		}catch(PersistenceException e){
			throw new DAOException(
					"Erreur PersonneDAOJPQL findByBirth()"+e.getMessage(), e);
			
		}
	}





	@Override
	public List<Personne> findAllWNumero() throws DAOException {
		try{
			TypedQuery<Personne> findIdQuerry = en.createQuery(
					"SELECT DISTINCT p FROM Personne p "
					+ "LEFT JOIN FETCH p.numeros ",
							Personne.class);
			return findIdQuerry.getResultList();
		}catch(PersistenceException e){
			throw new DAOException(
					"Erreur PersonneDAOJPQL findAllWNumero()"
							+e.getMessage(), e);
			
		}
	}

}
