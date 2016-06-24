package fr.treeptik.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import fr.treeptik.pojo.Numero;
import fr.treeptik.pojo.Personne;
import fr.treeptik.service.impl.ServiceFactory;
import fr.treeptik.service.interf.NumeroService;
import fr.treeptik.service.interf.PersonneService;

public class MenuPresentation {
	
	private static Scanner scan = new Scanner(System.in);
	
	PersonneService servP = ServiceFactory.getPersonneService();
	
	NumeroService servN = ServiceFactory.getNumeroService();
	
	/*
	 * 
	 * 
	 * 
	 * PERSONNE
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	public void menuPersonne(){
		Integer input;
		System.out.println("----------Menu Personne-----------");
		System.out.println("--Ajouter une Personne : 1");
		System.out.println("--Supprimer une Personne : 2");
		System.out.println("--Modifier une Personne : 3");
		System.out.println("--Rechercher une Personne : 4");
		System.out.println("-Quiter : >3");
		input = this.scanInteger();
		switch (input) {
		case 1:
			this.ajoutPersonne();
			System.out.println("Ajout terminé :)");
			break;
		case 2:
			this.delP();
			break;
		case 3:
			this.upP();
			break;
		case 4:
			this.findP();
			break;

		default:
			System.out.println("Retour au menu Principal");
			break;
		}
	}
	
	private void ajoutPersonne() {
			Boolean bool = false;
			Personne p = this.addP();
			System.out.println("On Passe au numero de tel");
			List<Numero> n = new ArrayList<>();
			do{
				n.add(this.addN());
				System.out.println("Un Autre ? oui:1 / non:0");
				Integer input = this.scanInteger();
				if (input==1) {
					bool=true;
				}
				
			}while(bool);
			p.setNumero(n);
			
			servP.add(p);
	}

	public Personne addP(){
		System.out.println("--->Ajout de Personne:");
		Personne p = new Personne();
		System.out.println("Nom :");
		String nom = scan.nextLine();
		p.setNom(nom);
		System.out.println("Prenom :");
		String prenom = scan.nextLine();
		p.setPrenom(prenom);
		System.out.println("Pas de date d'anniveraire pour lui :p");
		p.setDateNaissance(new Date());
		return p;
		
	}
	public void delP(){
		System.out.println("--->Qui allons nous tuer xD:");
		Personne p;
		do {
			p = findP();
			
		} while (p==null);
		servP.remove(p);
		System.out.println("->Fin du massacre :p");
		
	}
	public void upP(){
		System.out.println("--->Modif de Personne:");
		Personne p;
		do {
			p = findP();
			
		} while (p==null);
		System.out.println("Nom :");
		String nom = scan.nextLine();
		p.setNom(nom);
		System.out.println("Prenom :");
		String prenom = scan.nextLine();
		p.setPrenom(prenom);
		System.out.println("Pas de date d'anniveraire pour lui :p");
		servP.update(p);
		
	}
	public Personne findP(){
		System.out.println("-->Recherche :");
		System.out.println("1. Par id");
		System.out.println("2. Par Nom");
		System.out.println("3. Par Prenom");
		System.out.println("4. Tous");
		System.out.println("5. Tous et avec leur numeros");
		
		
		Integer input = this.scanInteger();
		switch (input) {
		case 1:
			System.out.println("->id :");
			input = this.scanInteger();
			Personne p = servP.findById(input);
			System.out.println(p.toString());
			return p;
		case 2:
			this.findPWithNom();
			break;
		case 3:
			this.findPWithPrenom();
			break;
		case 4:
			this.findPAll();
			break;
		case 5:
			this.findPAllWNumero();
			break;

		default:
			System.out.println("Ok pas de recherche ...");
			break;
		}
		
		
		
		return null;
	}
	
	private void findPAllWNumero() {
		List<Personne> personnes = servP.findAllWPersonne();
		for (Personne personne : personnes) {
			System.out.println(personne.toString());
			List<Numero> numeros = personne.getNumero();
			if (!numeros.isEmpty()) {
				for (Numero numero : numeros) {
					System.out.println("\t\t"+numero.toString());
				}
			}
		}
	}

	private void findPWithNom() {
		System.out.println("-> nom :");
		String strinput = this.scanString();
		List<Personne> lp = servP.findByName(strinput);
		for (Personne personne : lp) {
			System.out.println(personne.toString());
		}
	}
	private void findPWithPrenom() {
		System.out.println("-> prenom :");
		String strinput = this.scanString();
		List<Personne> lp = servP.findByFName(strinput);
		for (Personne personne : lp) {
			System.out.println(personne.toString());
		}
	}
	private void findPAll() {
		System.out.println("->TOUT :");
		List<Personne> lp = servP.findAll();
		for (Personne personne : lp) {
			System.out.print(personne.toString()+"\n");
		}
		
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * NUMERO
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */


	public void menuNumero(){
		System.out.println("----------Menu Numero-----------");
		System.out.println("--Ajouter un Numero: 1");
		System.out.println("--Supprimer un Numero: 2");
		System.out.println("--Modifier un Numero: 3");
		System.out.println("--Rechercher un Numero: 4");
		System.out.println("-Quiter : 99");
		Integer input = this.scanInteger();
		switch (input) {
		case 1:
			this.ajoutNumero();
			System.out.println("Ajout terminé :)");
			break;
		case 2:
			this.delN();;
			break;
		case 3:
			this.upN();
			break;
		case 4:
			this.findN();
			break;

		default:
			System.out.println("Retour au menu Principal :)");
			break;
		}
	}
	
	private void ajoutNumero() {
			Numero n = this.addN();
			List<Personne> p = new ArrayList<>();
			Integer input;
			do{
				System.out.println("Qui possede ce numero ?");
				p.add(this.addP());
				System.out.println("Un Autre ? oui:1 / non:0");
				input = this.scanInteger();
			} while (input==1);
			n.setPersonnes(p);
			servN.add(n);
		
	}

	public Numero addN(){
		System.out.println("--->Ajout de Numero:");
		Numero n = new Numero();
		System.out.println("Numero :");
		String number = scan.nextLine();
		n.setTel(number);
		System.out.println("Type :");
		String type = scan.nextLine();
		n.setType(type);
		return n;
		
	}
	public void delN(){
		System.out.println("--->Deletion de Numero:");
		Numero n;
		do {
			n = findN();
		} while (n==null);
		servN.remove(n);
		System.out.println("->Fin de la deletion");
		
		
	}
	public void upN(){
		System.out.println("--->Modif de Numero:");
		Numero n;
		do {
			n = findN();
		} while (n==null);
		System.out.println("Numero :");
		String number = scan.nextLine();
		n.setTel(number);
		System.out.println("Type :");
		String type = scan.nextLine();
		n.setType(type);
		servN.update(n);
		
	}
	public Numero findN(){
		Integer input;
		
		System.out.println("-->Recherche :");
		System.out.println("1. Par id");
		System.out.println("2. Par Tel");
		System.out.println("3. Par Type");
		System.out.println("4. Tout");
		System.out.println("5. Tout et avec les Personnes associées");
		input = this.scanInteger();
		switch (input) {
		case 1:
			System.out.println("->id :");
			input = this.scanInteger();
			Numero n = servN.findById(input);
			System.out.println(n.toString());
			return n;
		case 2:
				System.out.println("-> Tel :");
				String strinput = this.scanString();
				Numero n1 = servN.findByTel(strinput);
				System.out.println(n1.toString());
				return n1;
		case 3:
			System.out.println("-> Type :");
			this.findNType();
			break;
		case 4:
			this.findNAll();
			break;
		case 5:
			this.findNAllWPersonne();
			break;

		default:
			System.out.println("Ok pas de recherche ...");
			break;
		}
		return null;
	}
	
	
	
	private void findNAllWPersonne() {
		List<Numero> numeros = servN.findAllWPersonne();
		for (Numero numero : numeros) {
			System.out.println(numero.toString());
			List<Personne> personnes = numero.getPersonnes();
			if (!personnes.isEmpty()) {
				for (Personne personne : personnes) {
					System.out.println("\t\t"+personne.toString());
				}
			}
		}
		
	}

	private void findNAll() {
		System.out.println("->TOUT");
		List<Numero> ln = servN.findAll();
		for (Numero numero : ln) {
			System.out.println(numero.toString());
		}
	}

	private void findNType() {
		String strinput = this.scanString();
		List<Numero> ln = servN.findByType(strinput);
		for (Numero numero : ln) {
			System.out.println(numero.toString());
		}
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Menu Principal 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private String scanString() {
		String strInput;
		do {
			strInput = scan.nextLine();
		} while (strInput==null);
		return strInput;
	}
	
	
	private Integer scanInteger() {
		Integer input = null;
		do {
			//TODO WARNING INFINTE LOOP
			try{
				input = scan.nextInt();
				scan.nextLine();
			}catch(Exception e){
				scan.nextLine();
				System.out.println("BZZZZZZZ Pas bon !");
			}
			
		} while (input==null);
		return input;
	}

	public Boolean menuPrincipal(){
		System.out.println("----------Menu Principal-----------");
		System.out.println("--Menu Personne: 1");
		System.out.println("--Menu Numero : 2");
		System.out.println("-Quiter : >3");
		
		Integer input = scanInteger();
			
		switch (input) {
		case 1:
			this.menuPersonne();
			return true;
		case 2:
			this.menuNumero();
			return true;
		default:
			System.out.println("////C'etais VOTRE Annuaire //////");
			return false;
		}
	}

}
