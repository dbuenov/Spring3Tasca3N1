package Programa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Articles.*;

public class Main {

	public static void main(String[] args) {

		boolean sortir = false;
		Floristeria floristeria=Floristeria.getFloristeria();

		do{
			switch(menu()){
			case 1:	creaFloristeria(floristeria);
					break;
			case 2: carregaFloristeria(floristeria);
					break;
			case 3: afegirArbre(floristeria);
					break;
			case 4: afegirFlor(floristeria);
					break;
			case 5: afegirDecoracio(floristeria);
					break;
			case 6: imprimirStock(floristeria);
					break;
			case 7: retirarArbre(floristeria);
					break;
			case 8: retirarFlor(floristeria);
					break;
			case 9: retirarDecoracio(floristeria);
					break;
					
			case 0: System.out.println("Gr�cies per utilitzar l'aplicaci�");
					sortir = true;
					break;
			}
		}while(!sortir);

	}
	public static byte menu(){
		Scanner entrada = new Scanner(System.in);
		byte opcio;
		final byte MINIMO = 0;
		final byte MAXIMO = 9;

		do{
			System.out.println("\nMEN� PRINCIPAL");
			System.out.println("1. Crear Floristeria");
			System.out.println("2. Carregar Floristeria");
			System.out.println("3. Afegir arbre");
			System.out.println("4. Afegir flor");
			System.out.println("5. Afegir decoracio");
			System.out.println("6. Imprimir Stock");
			System.out.println("7. Retirar arbre");
			System.out.println("8. Retirar flor");
			System.out.println("9. Retirar decoracio");
			
			System.out.println("0. Sortir de l'aplicaci�.\n");
			opcio = entrada.nextByte();
			if(opcio < MINIMO || opcio > MAXIMO){
				System.out.println("Escull una opci� v�lida");
			}
		}while(opcio < MINIMO || opcio > MAXIMO);
		return opcio;
	}

	public static void creaFloristeria(Floristeria floristeria){
		//creo la floristeria en blanc
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom de la Floristeria:");
		String nom = sc.nextLine();
		floristeria.setNom(nom);
		System.out.println("Creada la floristeria "+floristeria.getNom());			
	}
	
	public static void carregaFloristeria(Floristeria floristeria){
		//creo la floristeria i carrego l'stock 
		//TODO: crear un fitxer de configuraci� amb el nom de la floristeria 
		//i el fitxer de l'stock
		Scanner sc = new Scanner(System.in);
		System.out.println("Fitxer d'stock:");
		String file = sc.nextLine();	
		floristeria.llegeixStock(file);		
	}

	public static void afegirArbre(Floristeria floristeria){
			Scanner sc = new Scanner(System.in);
			System.out.println("Nom de l'arbre:");
			String nom = sc.nextLine();
			System.out.println("Al�ada de l'arbre:");
			String al�ada = sc.nextLine();
			System.out.println("Preu de l'arbre:");
			float preu = sc.nextFloat();
			Arbre arbre = new Arbre(nom,al�ada,preu);
			floristeria.addArticle(arbre);
			System.out.println(arbre.toString());				
	}
	
	public static void afegirFlor(Floristeria floristeria){
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom de la flor:");
		String nom = sc.nextLine();
		System.out.println("Color de la flor:");
		String color = sc.nextLine();
		System.out.println("Preu de la flor:");
		float preu = sc.nextFloat();
		Flor flor = new Flor(nom,color,preu);
		floristeria.addArticle(flor);
		System.out.println(flor.toString());				
	}
	
	public static void afegirDecoracio(Floristeria floristeria){
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom de la decoracio:");
		String nom = sc.nextLine();
		System.out.println("Preu de la decoracio:");
		Material material;
		float preu = sc.nextFloat();
		byte opcio;
		do{
			System.out.println("\nMaterial de la decoracio:");
			System.out.println("1. Pl�stic");
			System.out.println("2. Fusta");			
			opcio = sc.nextByte();
			if(opcio < 1 || opcio > 2){
				System.out.println("Escull una opci� v�lida");
			}
		}while(opcio < 1 || opcio > 2);
		if (opcio == 1) {
			material = Material.Plastic;
		}else {
			material = Material.Fusta;
		}
		Decoracio decoracio = new Decoracio(nom,material,preu);
		floristeria.addArticle(decoracio);
		System.out.println(decoracio.toString());				
	}
	
	public static void imprimirStock(Floristeria floristeria) {
		System.out.println(floristeria.creaStock());
	}
	
	
	public static void retirarArbre(Floristeria floristeria){
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom de l'arbre:");
		String nom = sc.nextLine();
		System.out.println("Al�ada de l'arbre:");
		String al�ada = sc.nextLine();
		System.out.println("Preu de l'arbre:");
		float preu = sc.nextFloat();
		Article arbre = new Arbre(nom,al�ada,preu);
		ArrayList<Article> llista = floristeria.getArticles();
		boolean trobat = false;
		int i=0;
		while(!trobat&&i<llista.size()) {
			if(llista.get(i).equals(arbre)) {
				trobat=true;
			}
			i++;			
		}		
		if (trobat) {
			floristeria.removeArticle(llista.get(i-1));
			System.out.println("He esborrat l'arbre");
		}else {
			System.out.println("No he trobat l'arbre");
		}					
	}
	
	public static void retirarFlor(Floristeria floristeria){
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom de la flor:");
		String nom = sc.nextLine();
		System.out.println("Color de la flor:");
		String color = sc.nextLine();
		System.out.println("Preu de la flor:");
		float preu = sc.nextFloat();
		Article flor = new Flor(nom,color,preu);
		ArrayList<Article> llista = floristeria.getArticles();
		boolean trobat = false;
		int i=0;
		while(!trobat&&i<llista.size()) {
			if(llista.get(i).equals(flor)) {
				trobat=true;
			}
			i++;			
		}		
		if (trobat) {
			floristeria.removeArticle(llista.get(i-1));
			System.out.println("He esborrat la flor");
		}else {
			System.out.println("No he trobat la flor");
		}					
	}
	
	public static void retirarDecoracio(Floristeria floristeria){
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom de la decoracio:");
		String nom = sc.nextLine();
		System.out.println("Preu de la decoracio:");
		Material material;
		float preu = sc.nextFloat();
		byte opcio;
		do{
			System.out.println("\nMaterial de la decoracio:");
			System.out.println("1. Pl�stic");
			System.out.println("2. Fusta");			
			opcio = sc.nextByte();
			if(opcio < 1 || opcio > 2){
				System.out.println("Escull una opci� v�lida");
			}
		}while(opcio < 1 || opcio > 2);
		if (opcio == 1) {
			material = Material.Plastic;
		}else {
			material = Material.Fusta;
		}
		
		Article decoracio = new Decoracio(nom,material,preu);
		ArrayList<Article> llista = floristeria.getArticles();
		boolean trobat = false;
		int i=0;
		while(!trobat&&i<llista.size()) {
			if(llista.get(i).equals(decoracio)) {
				trobat=true;
			}
			i++;			
		}		
		if (trobat) {
			floristeria.removeArticle(llista.get(i-1));
			System.out.println("He esborrat la decoracio");
		}else {
			System.out.println("No he trobat la decoracio");
		}					
	}
	
	

}
