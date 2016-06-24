package fr.treeptik.dao.interf;

import java.util.List;

import fr.treeptik.dao.exception.DAOException;
import fr.treeptik.pojo.Numero;

public interface NumeroDAO extends GenericDAO<Numero, Integer>{
	Numero findByTel(String tel) throws DAOException;
	List<Numero> findByType(String type) throws DAOException;
	List<Numero> findAllWPersonne() throws DAOException;

}
