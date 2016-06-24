package fr.treeptik.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.treeptik.dao.exception.DAOException;
import fr.treeptik.dao.impl.DAOFactoryJPQL;
import fr.treeptik.dao.impl.JPAutils;
import fr.treeptik.dao.interf.NumeroDAO;
import fr.treeptik.dao.interf.PersonneDAO;
import fr.treeptik.pojo.Numero;
import fr.treeptik.pojo.Personne;
import fr.treeptik.service.exception.ServiceException;
import fr.treeptik.service.interf.PersonneService;

public class PersonneServiceImpl implements PersonneService{
	
	private PersonneDAO personneDAO = DAOFactoryJPQL.getPersonneDAO();
	private NumeroDAO numeroDAO = DAOFactoryJPQL.getNumeroDAO();

	@Override
	public Personne add(Personne entity) throws ServiceException {
		try{
			JPAutils.begin();
			List<Numero> ln = entity.getNumero();
			List<Personne> lp = new ArrayList<>();
			lp.add(entity);
			if (!ln.isEmpty()) {
				for (Numero numero : ln) {
					numero.setPersonnes(lp);
					numero = numeroDAO.add(numero);
				}
			}
			Personne pers = personneDAO.add(entity);
			JPAutils.commit();
			return pers;
			
		} catch (DAOException e) {
			JPAutils.rollback();
			throw new ServiceException(
					"Erreur PersonneService add(Personne)"+e.getMessage(), e);
		}
	}

	@Override
	public void remove(Personne entity) throws ServiceException {
		try {
			JPAutils.begin();
			personneDAO.remove(entity);
			JPAutils.commit();
			
		} catch (DAOException e) {
			JPAutils.rollback();
			throw new ServiceException(
					"Erreur PersonneService remove(Personne)"+e.getMessage(), e);
		}
		
	}

	@Override
	public Personne update(Personne entity) throws ServiceException {
		try {
			JPAutils.begin();
			Personne pers = personneDAO.update(entity);
			JPAutils.commit();
			return pers;
			
		} catch (DAOException e) {
			JPAutils.rollback();
			throw new ServiceException(
					"Erreur PersonneService update(Personne)"+e.getMessage(), e);
		}
	}


	@Override
	public Personne findById(Integer key) throws ServiceException {
		try {
			return personneDAO.findById(key);
			
		} catch (DAOException e) {
			throw new ServiceException(
					"Erreur PersonneService findById(key)"+e.getMessage(), e);
		}
	}

	@Override
	public List<Personne> findAll() throws ServiceException {
		try {
			return personneDAO.find(null);
		} catch (DAOException e) {
			throw new ServiceException(
					"Erreur PersonneService findAll()"+e.getMessage(), e);
		}
	}


	@Override
	public List<Personne> findByName(String nom) throws ServiceException {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("nom", nom);
			return personneDAO.find(map);
			
		} catch (DAOException e) {
			throw new ServiceException(
					"Erreur PersonneService findByName(String)"+e.getMessage(), e);
		}
	}

	@Override
	public List<Personne> findByFName(String prenom) throws ServiceException {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("prenom", prenom);
			return personneDAO.find(map);
			
		} catch (DAOException e) {
			throw new ServiceException(
					"Erreur PersonneService findByFName(String)"+e.getMessage(), e);
		}
	}

	@Override
	public List<Personne> findByBirth(Date naissance) throws ServiceException {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("dateNaissance", naissance);
			return personneDAO.findByBirth(naissance);
			
		} catch (DAOException e) {
			throw new ServiceException(
					"Erreur PersonneService findByBirth(Date)"+e.getMessage(), e);
		}
	}

	@Override
	public List<Personne> find(Map<String, Object> map) throws ServiceException {
		try {
			return personneDAO.find(map);
			
		} catch (DAOException e) {
			throw new ServiceException(
					"Erreur PersonneService find(Map<Str, Obj>)"
							+e.getMessage(), e);
		}
	}

	@Override
	public List<Personne> findAllWPersonne() throws ServiceException {
		try {
			return personneDAO.findAllWNumero();
			
		} catch (DAOException e) {
			throw new ServiceException(
					"Erreur PersonneService findAllWNumero(Map<Str, Obj>)"
							+e.getMessage(), e);
		}
	}

}
