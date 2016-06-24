package fr.treeptik.service.interf;

import java.util.Date;
import java.util.List;

import fr.treeptik.pojo.Personne;
import fr.treeptik.service.exception.ServiceException;

public interface PersonneService extends GenericService<Personne, Integer>{
	List<Personne> findByName(String nom) throws ServiceException;
	List<Personne> findByFName(String prenom) throws ServiceException;
	List<Personne> findByBirth(Date naissance) throws ServiceException;
	List<Personne> findAllWPersonne() throws ServiceException;

}
