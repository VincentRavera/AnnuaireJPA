package fr.treeptik.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import fr.treeptik.dao.exception.DAOException;
import fr.treeptik.dao.interf.GenericJPADAO;
import fr.treeptik.dao.interf.NumeroDAO;
import fr.treeptik.pojo.Numero;

public class NumeroDAOJPQL extends GenericJPADAO<Numero, Integer> implements NumeroDAO{
	
	EntityManager en = JPAutils.getEn();
	


	public NumeroDAOJPQL() {
		super(Numero.class);
	}

	@Override
	public Numero findByTel(String tel) throws DAOException{
		try{
			TypedQuery<Numero> findIdQuerry = en.createQuery(
					"SELECT DISTINCT n FROM Numero n "
					+ "JOIN FETCH n.personnes "
					+ "WHERE n.tel=:tel ",
//					+ "GROUP BY n.id",
							Numero.class);
			findIdQuerry.setParameter("tel", tel);
			return findIdQuerry.getSingleResult();
		}catch(PersistenceException e){
			throw new DAOException(
					"Erreur NumeroDAOJPQL findByTel(String)"
							+e.getMessage(), e);
		}
	}

	@Override
	public List<Numero> findByType(String type) throws DAOException{
		try{
			TypedQuery<Numero> findIdQuerry = en.createQuery(
					"SELECT DISTINCT n FROM Numero n "
					+ "JOIN FETCH n.personnes "
					+ "WHERE n.type=:type "
					+ "GROUP BY n.id ",
							Numero.class);
			findIdQuerry.setParameter("type", type);
			return findIdQuerry.getResultList();
		}catch(PersistenceException e){
			throw new DAOException(
					"Erreur NumeroDAOJPQL findByType(String)"
							+e.getMessage(), e);
		}
	}

	@Override
	public List<Numero> findAllWPersonne() throws DAOException {
		try{
			TypedQuery<Numero> findIdQuerry = en.createQuery(
					"SELECT DISTINCT n FROM Numero n "
					+ "LEFT JOIN FETCH n.personnes ",
							Numero.class);
			return findIdQuerry.getResultList();
		}catch(PersistenceException e){
			throw new DAOException(
					"Erreur PersonneDAOJPQL findAllWPersonne()"
							+e.getMessage(), e);
			
		}
	}

}
