package fr.treeptik.dao.interf;

import java.util.Date;
import java.util.List;

import fr.treeptik.dao.exception.DAOException;
import fr.treeptik.pojo.Personne;

public interface PersonneDAO extends GenericDAO<Personne, Integer>{
	
	List<Personne> findByName(String nom) throws DAOException;
	List<Personne> findByFName(String prenom) throws DAOException;
	List<Personne> findByBirth(Date naissance) throws DAOException;
	List<Personne> findAllWNumero() throws DAOException;
	

}
