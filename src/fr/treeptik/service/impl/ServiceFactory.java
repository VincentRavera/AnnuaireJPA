package fr.treeptik.service.impl;

import fr.treeptik.service.interf.NumeroService;
import fr.treeptik.service.interf.PersonneService;

public class ServiceFactory {
	public static PersonneService getPersonneService(){
		return new PersonneServiceImpl();
	}
	public static NumeroService getNumeroService(){
		return new NumeroServiceImpl();
	}

}
