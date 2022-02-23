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
			case 10:imprimirQuatitatStock(floristeria);
					break;
			case 11:imprimirValorTotal(floristeria);
					break;
			case 12:creaTicketCompra(floristeria);
					break;	
			case 13:llistaDeTickets(floristeria);
					break;	
			case 14:totalGanancia(floristeria);
					break;	
					
			case 0: System.out.println("Gràcies per utilitzar l'aplicació");
					sortir = true;
					break;
			}
		}while(!sortir);

	}
	public static byte menu(){
		Scanner entrada = new Scanner(System.in);
		byte opcio;
		final byte MINIMO = 0;
		final byte MAXIMO = 14;

		do{
			System.out.println("\nMENú PRINCIPAL");
			System.out.println(" 1. Crear Floristeria");
			System.out.println(" 2. Carregar Floristeria");
			System.out.println(" 3. Afegir arbre");
			System.out.println(" 4. Afegir flor");
			System.out.println(" 5. Afegir decoracio");
			System.out.println(" 6. Imprimir Stock");
			System.out.println(" 7. Retirar arbre");
			System.out.println(" 8. Retirar flor");
			System.out.println(" 9. Retirar decoracio");
			System.out.println("10. Imprimir Quantitat Stock");
			System.out.println("11. Imprimir Valor Total");
			System.out.println("12. Crea ticket de compra");
			System.out.println("13. Mostra llista de compres antigues");
			System.out.println("14. Total de diners guanyats");

			System.out.println(" 0. Sortir de l'aplicació.\n");
			opcio = entrada.nextByte();
			if(opcio < MINIMO || opcio > MAXIMO){
				System.out.println("Escull una opció vàlida");
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
		//TODO: crear un fitxer de configuració amb el nom de la floristeria 
		//i el fitxer de l'stock
		Scanner sc = new Scanner(System.in);
		System.out.println("Fitxer d'stock:");
		String file = sc.nextLine();	
		floristeria.llegeixStock(file);		
	}

	public static void afegirArbre(Floristeria floristeria){
		Article arbre = creaArbre();
		floristeria.addArticle(arbre);
		System.out.println(arbre.toString());				
	}

	public static void afegirFlor(Floristeria floristeria){
		Article flor = creaFlor();
		floristeria.addArticle(flor);
		System.out.println(flor.toString());				
	}

	public static void afegirDecoracio(Floristeria floristeria){
		Article decoracio = creaDecoracio();
		floristeria.addArticle(decoracio);
		System.out.println(decoracio.toString());				
	}

	public static void imprimirStock(Floristeria floristeria) {
		System.out.println(floristeria.creaStock());
	}

	public static void retirarArbre(Floristeria floristeria){
		Article arbreBuscat = creaArbre();
		Article arbreTrobat = buscaArticle(floristeria, arbreBuscat);
		if (arbreTrobat!=null) {
			floristeria.removeArticle(arbreTrobat);
			System.out.println("He esborrat l'arbre");
		}				
	}

	public static void retirarFlor(Floristeria floristeria){
		Article florBuscada = creaFlor();
		Article florTrobada = buscaArticle(floristeria, florBuscada);
		if (florTrobada!=null) {
			floristeria.removeArticle(florTrobada);
			System.out.println("He esborrat la flor");
		}				
	}

	public static void retirarDecoracio(Floristeria floristeria){
		Article decoracioBuscada = creaDecoracio();
		Article decoracioTrobada = buscaArticle(floristeria, decoracioBuscada);
		if (decoracioTrobada!=null) {
			floristeria.removeArticle(decoracioTrobada);
			System.out.println("He esborrat la decoracio");
		}				
	}

	public static void imprimirQuatitatStock(Floristeria floristeria){
		System.out.println("ARBRES:");
		System.out.println("      "+floristeria.stockArbres());
		System.out.println("FLORS:");
		System.out.println("      "+floristeria.stockFlors());
		System.out.println("DECORACIONS:");
		System.out.println("      "+floristeria.stockDecoracions());
	}

	public static void imprimirValorTotal(Floristeria floristeria){
		System.out.println("La Floristeria té un valor total de "+floristeria.getValorFloristeria()+" €");
	}

	public static void creaTicketCompra(Floristeria floristeria) {
		Ticket ticket = new Ticket(floristeria.getCodiTicket());
		boolean sortir = false;
		
		do{
			switch(menuTicket()){
				case 1:	Article arbreBuscat = creaArbre();
						Article arbreTrobat = buscaArticle(floristeria, arbreBuscat);
						if (arbreTrobat!=null) {
							ticket.addArticle(arbreTrobat);
							floristeria.removeArticle(arbreTrobat);
							System.out.println("He afegit l'arbre al ticket");
						}
						break;
				case 2: Article florBuscada = creaFlor();
						Article florTrobada = buscaArticle(floristeria, florBuscada);
						if (florTrobada!=null) {
							ticket.addArticle(florTrobada);
							floristeria.removeArticle(florTrobada);
							System.out.println("He afegit la flor al ticket");
						}	
						break;
				case 3: Article decoracioBuscada = creaDecoracio();
						Article decoracioTrobada = buscaArticle(floristeria, decoracioBuscada);
						if (decoracioTrobada!=null) {
							ticket.addArticle(decoracioTrobada);
							floristeria.removeArticle(decoracioTrobada);
							System.out.println("He afegit la decoracio al ticket");
						}	
						break;	
				case 0: sortir = true;
						break;
			}
		}while(!sortir);
		floristeria.addTicket(ticket);
	}
	
	public static byte menuTicket(){
		
		Scanner entrada = new Scanner(System.in);
		byte opcio;
		final byte MINIMO = 0;
		final byte MAXIMO = 3;

		do{
			System.out.println("\nMENú PRINCIPAL");
			System.out.println(" 1.Afegir arbre al ticket");
			System.out.println(" 2.Afegir flor al ticket");
			System.out.println(" 3.Afegir decoracio al ticket");
			
			System.out.println(" 0.Sortir del ticket\n");
			opcio = entrada.nextByte();
			if(opcio < MINIMO || opcio > MAXIMO){
				System.out.println("Escull una opció vàlida");
			}
		}while(opcio < MINIMO || opcio > MAXIMO);
		return opcio;
	}	
	
	public static Article buscaArticle(Floristeria floristeria, Article article) {
		ArrayList<Article> llista = floristeria.getArticles();
		boolean trobat = false;
		Article articleEnStock=null;
		int i=0;
		while(!trobat&&i<llista.size()) {
			if(llista.get(i).equals(article)) {
				trobat=true;
			}
			i++;			
		}		
		if (trobat) {
			articleEnStock=llista.get(i-1);
			System.out.println("He trobat l'Article");
		}else {
			System.out.println("No he trobat l'Article");
		}	
		
		return articleEnStock;
	}
	
	public static Article creaArbre() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom de l'arbre:");
		String nom = sc.nextLine();
		System.out.println("Alçada de l'arbre:");
		String alçada = sc.nextLine();
		System.out.println("Preu de l'arbre:");
		float preu = sc.nextFloat();
		Article arbre = new Arbre(nom,alçada,preu);
		return arbre;		
	}
	
	public static Article creaFlor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom de la flor:");
		String nom = sc.nextLine();
		System.out.println("Color de la flor:");
		String color = sc.nextLine();
		System.out.println("Preu de la flor:");
		float preu = sc.nextFloat();
		Article flor = new Flor(nom,color,preu);
		return flor;
	}
	
	public static Article creaDecoracio() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom de la decoracio:");
		String nom = sc.nextLine();
		System.out.println("Preu de la decoracio:");
		Material material;
		float preu = sc.nextFloat();
		byte opcio;
		do{
			System.out.println("\nMaterial de la decoracio:");
			System.out.println("1. Plàstic");
			System.out.println("2. Fusta");			
			opcio = sc.nextByte();
			if(opcio < 1 || opcio > 2){
				System.out.println("Escull una opció vàlida");
			}
		}while(opcio < 1 || opcio > 2);
		if (opcio == 1) {
			material = Material.Plastic;
		}else {
			material = Material.Fusta;
		}

		Article decoracio = new Decoracio(nom,material,preu);
		return decoracio;
	}
	
	public static void llistaDeTickets(Floristeria floristeria) {
		ArrayList<Ticket> tickets = floristeria.getTickets();
		for (Ticket ticket : tickets) {
			System.out.println(ticket.imprimirTicket());
		}
		
	}
	
	public static void totalGanancia(Floristeria floristeria) {
		ArrayList<Ticket> tickets = floristeria.getTickets();
		float total=0;
		for (Ticket ticket : tickets) {
			total+=ticket.getTotal();
		}
		System.out.println("El total de diners guanyats es de "+total+" €");
	}	
}
