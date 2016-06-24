package fr.treeptik.pojo;

import java.util.ArrayList;
import java.util.Date;

public class PersonneBuilder {
	
	private Personne personne;
	
	
	public static PersonneBuilder createInstance() {
		PersonneBuilder builder = new PersonneBuilder();
		builder.personne = new Personne();
		
		return builder;
	}

	
	public PersonneBuilder withNom(String nom) {
		this.personne.setNom(nom);
		return this;
	}
	
	public PersonneBuilder withPrenom(String prenom) {
		this.personne.setPrenom(prenom);
		return this;
	}
	
	public PersonneBuilder withDate(Date naissance) {
		this.personne.setDateNaissance(naissance);
		return this;
		
	}
	public PersonneBuilder withNum(Numero n) {
		if (personne.getNumero()==null) {
			personne.setNumero(new ArrayList<Numero>());
		}
		this.personne.getNumero().add(n);
		return this;
		
	}
	
	public Personne build() {
		return this.personne;
		
	}
}
