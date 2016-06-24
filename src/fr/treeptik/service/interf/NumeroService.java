package fr.treeptik.service.interf;

import java.util.List;

import fr.treeptik.pojo.Numero;
import fr.treeptik.service.exception.ServiceException;

public interface NumeroService extends GenericService<Numero, Integer>{
	Numero findByTel(String tel) throws ServiceException;
	List<Numero> findByType(String type) throws ServiceException;
	List<Numero> findAllWPersonne() throws ServiceException;
	

}
