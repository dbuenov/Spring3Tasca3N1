package Programa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;

import Articles.*;

public class Main {

	public static void main(String[] args) {

		boolean sortir = false;
		Floristeria floristeria=Floristeria.getFloristeria();

		do{
			switch(menu()){
			case 1:	obreFloristeria(floristeria);
					break;
			case 2: afegirArticle(floristeria);
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
			case 0: sortir(floristeria); 
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
			System.out.println(" 1. Obre Floristeria");
			System.out.println("---------------------------------------");
			System.out.println(" 2. Afegir article");
			System.out.println(" 3. Retirar article");
			System.out.println("---------------------------------------");
			System.out.println(" 6. Imprimir Stock Total");
			
			
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
	
	//Case 1 Crea la floristeria, si ja estava creada carrega les dades antigues
	public static void obreFloristeria(Floristeria floristeria){
		Scanner sc = new Scanner(System.in);
		System.out.println("Nom de la Floristeria:");
		String nom = sc.nextLine();
		floristeria.setNom("Floristeria_"+nom.toLowerCase());
		floristeria.recuperaFloristeria();					
	}
	
	//Case 2 Afegir article (Arbre, Flor o Decoracio)
	public static void afegirArticle(Floristeria floristeria) {
		int id = floristeria.getIdArticle();
		Article article = null;
		boolean sortir = false;
		do{
			switch(menuTipus()){
				case 1:	article = creaArbre(id);
						break;
				case 2: article = creaFlor(id);
						break;
				case 3: article = creaDecoracio(id);
						break;	
				case 0: sortir = true;
						break;
			}
			floristeria.addArticle(article);
			System.out.println(article.toString());			
		}while(!sortir);		
	}	

	public static void imprimirStock(Floristeria floristeria) {
		System.out.println(floristeria.creaStock());
		System.out.println(floristeria.getTickets().get(0).toString());
	}

	public static void retirarArbre(Floristeria floristeria){
		mostraArticleTipus(floristeria);
		Scanner sc = new Scanner(System.in);
		System.out.println("Id de l'arbre a retirar:");
		int id = sc.nextInt();
		Article arbreTrobat = buscaArticle(floristeria, id);
		if (arbreTrobat!=null) {
			floristeria.removeArticle(arbreTrobat);
			System.out.println("He esborrat l'arbre");
		}				
	}

	public static void retirarFlor(Floristeria floristeria){
		mostraArticleTipus(floristeria);
		Scanner sc = new Scanner(System.in);
		System.out.println("Id de la flor a retirar:");
		int id = sc.nextInt();
		Article florTrobada = buscaArticle(floristeria, id);
		if (florTrobada!=null) {
			floristeria.removeArticle(florTrobada);
			System.out.println("He esborrat la flor");
		}				
	}

	public static void retirarDecoracio(Floristeria floristeria){
		mostraArticleTipus(floristeria);
		Scanner sc = new Scanner(System.in);
		System.out.println("Id de la decoracio a retirar:");
		int id = sc.nextInt();
		Article decoracioTrobada = buscaArticle(floristeria, id);
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
		Scanner sc = new Scanner(System.in);
		int id;
		
		do{
			switch(menuTipus()){
				case 1:	mostraArticleTipus(floristeria);
						System.out.println("Id de l'arbre a retirar:");
						id = sc.nextInt();
						Article arbreTrobat = buscaArticle(floristeria, id);
						if (arbreTrobat!=null) {
							ticket.addArticle(arbreTrobat);
							floristeria.removeArticle(arbreTrobat);
							System.out.println("He afegit l'arbre al ticket");
						}
						break;
				case 2: mostraArticleTipus(floristeria);
						System.out.println("Id de la flor a retirar:");
						id = sc.nextInt();
						Article florTrobada = buscaArticle(floristeria, id);
						if (florTrobada!=null) {
							ticket.addArticle(florTrobada);
							floristeria.removeArticle(florTrobada);
							System.out.println("He afegit la flor al ticket");
						}	
						break;
				case 3: mostraArticleTipus(floristeria);
						System.out.println("Id de la decoracio a retirar:");
						id = sc.nextInt();
						Article decoracioTrobada = buscaArticle(floristeria, id);
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
	
	public static byte menuTipus(){
		
		Scanner entrada = new Scanner(System.in);
		byte opcio;
		final byte MINIMO = 0;
		final byte MAXIMO = 3;

		do{
			System.out.println("\nMENú PRINCIPAL");
			System.out.println(" 1.Arbre");
			System.out.println(" 2.Flor");
			System.out.println(" 3.Decoracio");
			
			System.out.println(" 0.Sortir\n");
			opcio = entrada.nextByte();
			if(opcio < MINIMO || opcio > MAXIMO){
				System.out.println("Escull una opció vàlida");
			}
		}while(opcio < MINIMO || opcio > MAXIMO);
		return opcio;
	}	
	
	//donat un id d'article retorna l'article concret
	public static Article buscaArticle(Floristeria floristeria, int id) {
		ArrayList<Article> llista = floristeria.getArticles();
		boolean trobat = false;
		Article articleEnStock=null;
		int i=0;
		while(!trobat&&i<llista.size()) {
			if(llista.get(i).getId()==id) {
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
	
	public static Article creaArbre(int id) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Alçada de l'arbre:");
		String alçada = sc.nextLine();
		System.out.println("Preu de l'arbre:");
		float preu = sc.nextFloat();
		Article arbre = new Arbre(id,alçada,preu);
		return arbre;		
	}
	
	public static Article creaFlor(int id) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Color de la flor:");
		String color = sc.nextLine();
		System.out.println("Preu de la flor:");
		float preu = sc.nextFloat();
		Article flor = new Flor(id,color,preu);
		return flor;
	}
	
	public static Article creaDecoracio(int id) {
		Scanner sc = new Scanner(System.in);
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

		Article decoracio = new Decoracio(id,material,preu);
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
	
	//metode que mostra per pantalla els articles de cada tipus que hi ha en stock
	public static void mostraArticleTipus (Floristeria floristeria) {
		boolean sortir = false;
		ArrayList<Article> articles = floristeria.getArticles();
		
		do{
			switch(menuTipus()){
				case 1:	for (Article article : articles) {
							if (article instanceof Arbre) {
								System.out.println(article.toString());
							}
						}	
						break;
				case 2: for (Article article : articles) {
							if (article instanceof Flor) {
								System.out.println(article.toString());
							}
						}	
						break;
				case 3: for (Article article : articles) {
							if (article instanceof Decoracio) {
								System.out.println(article.toString());
							}
						}	
						break;	
				case 0: sortir = true;
						break;
			}
		}while(!sortir);		
	}		
	
	public static void sortir(Floristeria floristeria) {
		floristeria.guardaFloristeria();		
		System.out.println("Gràcies per utilitzar l'aplicació");
	}
}
