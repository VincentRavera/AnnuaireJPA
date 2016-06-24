package fr.treeptik.presentation;


public class Runtime {

	public static void main(String[] args) {
		Boolean loop = true;
		MenuPresentation menu = new MenuPresentation();
		System.out.println("////////BIENVENUE DANS VOTRE ANNAIRE///////////");
		while(loop){
			loop = menu.menuPrincipal();
		}
		
		System.out.println("See ya Amigo :)");
		
	}

}
