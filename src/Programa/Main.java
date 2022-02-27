package Programa;

import java.util.ArrayList;
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
			case 3: triaArticleRetirar(floristeria);
			break;
			case 4: imprimirStock(floristeria);
			break;
			case 5: imprimirQuatitatStock(floristeria);
			break;
			case 6: imprimirValorTotal(floristeria);
			break;
			case 7: creaTicketCompra(floristeria);
			break;	
			case 8: llistaDeTickets(floristeria);
			break;	
			case 9: totalGanancia(floristeria);
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
		final byte MAXIMO = 9;
		
		do{
			System.out.println("\nMENú PRINCIPAL");
			System.out.println(" 1. Obre Floristeria");
			System.out.println("---------------------------------------");
			System.out.println(" 2. Afegir article");
			System.out.println(" 3. Retirar article");
			System.out.println("---------------------------------------");
			System.out.println(" 4. Imprimir Stock Total");
			System.out.println(" 5. Imprimir Quantitat Stock");
			System.out.println(" 6. Imprimir Valor Total");
			System.out.println("---------------------------------------");
			System.out.println(" 7. Crea ticket de compra");
			System.out.println(" 8. Mostra llista de compres antigues");
			System.out.println(" 9. Total de diners guanyats");

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
		floristeria.setNom(nom.toLowerCase());
		floristeria.recuperaFloristeria();					
	}

	//Case 2 Afegir article (Arbre, Flor o Decoracio)
	public static void afegirArticle(Floristeria floristeria) {
		int id;
		Article article = null;
		boolean sortir = false;
		do{
			System.out.println("Tria tipus d'article a afegir");
			switch(menuTipus()){
			case 1:	id = floristeria.getIdArticle();
					article = new FabricaArbres().creaArticle(id);
					floristeria.addArticle(article);
					System.out.println(article.toString());	
			break;
			case 2: id = floristeria.getIdArticle();
					article = new FabricaFlors().creaArticle(id);
					floristeria.addArticle(article);
					System.out.println(article.toString());	
			break;
			case 3: id = floristeria.getIdArticle();
					article = new FabricaDecoracions().creaArticle(id);
					floristeria.addArticle(article);
					System.out.println(article.toString());	
			break;	
			case 0: sortir = true;
			break;
					
			}
			
								
		}while(!sortir);		
	}	

	//Case 3 Retirar article (Arbre, Flor o Decoracio)	
	public static void triaArticleRetirar(Floristeria floristeria) {
		boolean sortir = false;
		Scanner sc = new Scanner(System.in);
		
		do{
			System.out.println("Tria tipus d'article a retirar");
			switch(menuTipus()){
			case 1:	System.out.println(floristeria.mostraArbres());
					retirarArticle(floristeria);
			break;
			case 2: System.out.println(floristeria.mostraFlors());
					retirarArticle(floristeria);
			break;
			case 3: System.out.println(floristeria.mostraDecoracions());
					retirarArticle(floristeria);
			break;	
			case 0: sortir = true;
			break;
			}
		}while(!sortir);
	}
	
	public static void retirarArticle(Floristeria floristeria){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Id de l'article a retirar:");
		int id = sc.nextInt();
		Article articleTrobat = buscaArticle(floristeria, id);
		if (articleTrobat!=null) {
			floristeria.removeArticle(articleTrobat);
			System.out.println("He esborrat l'article");
		}				
	}

	//Case 4 Imprimir l'stock total
	public static void imprimirStock(Floristeria floristeria) {
		System.out.println(floristeria.creaStock());		
	}
	
	//Case 5 Imprimir Quantitat d'stock per tipus d'Article

	public static void imprimirQuatitatStock(Floristeria floristeria){
		System.out.println("ARBRES:");
		System.out.println("      "+floristeria.stockArbres());
		System.out.println("FLORS:");
		System.out.println("      "+floristeria.stockFlors());
		System.out.println("DECORACIONS:");
		System.out.println("      "+floristeria.stockDecoracions());
	}

	// Case 6 Imprimeix per pantalla el valor total del stock de la floristeria

	public static void imprimirValorTotal(Floristeria floristeria){
		System.out.println("La Floristeria té un valor total de "+floristeria.getValorFloristeria()+" €");
	}

	// Case 7 Crea un ticket de compra amb articles	

	public static void creaTicketCompra(Floristeria floristeria) {
		Ticket ticket = new Ticket(floristeria.getCodiTicket());
		boolean sortir = false;
		Scanner sc = new Scanner(System.in);
		
		do{
			System.out.println("Tria tipus d'article a afegir al ticket");
			
			switch(menuTipus()){
			case 1:	System.out.println(floristeria.mostraArbres());
					afegirArticleTicket(floristeria, ticket);
			break;
			case 2: System.out.println(floristeria.mostraFlors());
					afegirArticleTicket(floristeria, ticket);
			break;
			case 3: System.out.println(floristeria.mostraDecoracions());
					afegirArticleTicket(floristeria, ticket);
			break;	
			case 0: sortir = true;
			break;
			}
		}while(!sortir);
		floristeria.addTicket(ticket);
	}
	
	public static void afegirArticleTicket(Floristeria floristeria, Ticket ticket) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Id de l'article per afegir al ticket:");
		int id = sc.nextInt();
		Article article = buscaArticle(floristeria, id);
		if (article!=null) {
			ticket.addArticle(article);
			floristeria.removeArticle(article);
			System.out.println("He afegit l'article al ticket");
		}
	}

	//Case 8 Mostra la llista de Tickets de la Floristeria

	public static void llistaDeTickets(Floristeria floristeria) {
		System.out.println(floristeria.getTicketsAntics());
		ArrayList<Ticket> tickets = floristeria.getTickets();
		for (Ticket ticket : tickets) {
			System.out.println(ticket.toString());
			
		}

	}

	// Case 9 Mostra la ganància total de la Floristeria

	public static void totalGanancia(Floristeria floristeria) {
		ArrayList<Ticket> tickets = floristeria.getTickets();
		float total=floristeria.getValorVentes();
		for (Ticket ticket : tickets) {
			total+=ticket.getTotal();
		}		
		
		System.out.println("El total de diners guanyats es de "+total+" €");
	}	

	//Case 0 Surt de l'aplicació y guarda una copia de les dades

	public static void sortir(Floristeria floristeria) {
		floristeria.guardaFloristeria();		
		System.out.println("Gràcies per utilitzar l'aplicació");
	}


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

	// Mostra un menu per triar el tipus d'Article

	public static byte menuTipus(){

		Scanner entrada = new Scanner(System.in);
		byte opcio;
		final byte MINIMO = 0;
		final byte MAXIMO = 3;

		do{
			System.out.println("\nMENú ARTICLES");
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
		
}