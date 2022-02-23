package Programa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Articles.*;

public class Floristeria {
	
	private String nom;
	private ArrayList<Article> articles;
	private ArrayList<Ticket> tickets;
	private int codiTicket;
	private int stockArbres;
	private int stockFlors;
	private int stockDecoracions;
	private float valorFloristeria;
	
	//faig servir singleton per asegurar-me de no crear més d'una floristeria
	private static Floristeria floristeria;
		
	private Floristeria() {
		this.articles = new ArrayList<Article>();
		this.tickets = new ArrayList<Ticket>();
		codiTicket=0;
		stockArbres=0;
		stockFlors=0;
		stockDecoracions=0;		
		this.valorFloristeria=0;
	}
	
	public static Floristeria getFloristeria() {
		if(floristeria == null) {
			floristeria = new Floristeria();			
		}
		return floristeria;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom=nom;
	}

	//quan afeigeix un article sumo un a l'stock
	public void addArticle(Article article) {
		articles.add(article);
		this.valorFloristeria += article.getPreu();
		if (article instanceof Arbre) {
			stockArbres++;
		}else if (article instanceof Flor) {
			stockFlors++;
		}else if (article instanceof Decoracio) {
			stockDecoracions++;
		}
	}
	
	//quan esborro un article resto un a l'stock	
	public void removeArticle(Article article) {
		this.articles.remove(article);
		this.valorFloristeria -= article.getPreu();
		if (article instanceof Arbre) {
			stockArbres--;
		}else if (article instanceof Flor) {
			stockFlors--;
		}else if (article instanceof Decoracio) {
			stockDecoracions--;
		}	
	} 
	
	public int stockArbres() {
		return stockArbres;
	}
	
	public int stockFlors() {
		return stockFlors;
	}
	
	public int stockDecoracions() {
		return stockDecoracions;	
	}
	
	public float getValorFloristeria() {
		return valorFloristeria;
	}

	public ArrayList<Article> getArticles(){
		return articles;
	}
		
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public int getCodiTicket() {
		int codiActual = codiTicket;
		codiTicket++;
		return codiActual;
	}
	
	
	//metode que crea un String amb tot l'stock
	public String creaStock() {
		String stock="";
		for (Article article : articles) {
			stock += article.toString();
		}
		return stock;		
	}
	
	/*
	 * metode que crea un fitxer amb el contingut de l'stock
	 * el fitxer está separat per comes "," i cada linea es un producte	
	*/
	
	public void escriuStock(String fitxer) {
		
		String stock=creaStock();
		File file = new File(fitxer);
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			if (stock != null && stock.length() > 0) {
				bufferedWriter.write(stock);

			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		finally {

			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}

			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}

		}
	}
	
	//metode que llegeix un fitxer amb stock i el guarda en memoria per treballar
	public void llegeixStock(String fitxer) {

		File file = new File(fitxer);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			fileReader = new FileReader(file, StandardCharsets.UTF_8);
			bufferedReader = new BufferedReader(fileReader);
			String linea = bufferedReader.readLine();
			
			while (linea != null) {
				Article article = null;
				
				StringTokenizer separador = new StringTokenizer(linea,",");
				String tipus = separador.nextToken();
				String nom = separador.nextToken();
				String caracteristica = separador.nextToken();
				float preu = Float.parseFloat(separador.nextToken());
				
				//miro el tipus d'Article i el creo amb el seu contructor
				
				if (tipus.equalsIgnoreCase("arbre")) {
					article = new Arbre(nom,caracteristica,preu);
				}else if (tipus.equalsIgnoreCase("flor")) {
					article = new Flor(nom,caracteristica,preu);				
				}else if (tipus.equalsIgnoreCase("decoracio")) {
					Material material;
					if (caracteristica.equals("FUSTA")) {
						material = Material.Fusta;
					}else {
						material = Material.Plastic;
					}
					article = new Decoracio(nom,material,preu);
				}
				addArticle(article);
				linea = bufferedReader.readLine();				
			}
			System.out.println("Carregat l'stock");

		} catch (IOException ex) {
			System.err.println(ex.getMessage());

		} finally {

			if (bufferedReader != null) {
				try {

					bufferedReader.close();
				} catch (IOException ex) {

					System.err.println(ex.getMessage());
				}
			}

			if (fileReader != null) {
				try {

					fileReader.close();
				} catch (IOException ex) {

					System.err.println(ex.getMessage());
				}
			}
		}
	}
}
		

