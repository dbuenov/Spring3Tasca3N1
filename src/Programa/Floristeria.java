package Programa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

import Articles.*;

public class Floristeria {

	private String nom;                     // nom de la floristeria
	private ArrayList<Article> articles;    // articles en stock
	private ArrayList<Ticket> tickets;      // tickets en temps d'execució
	private int codiTicket;                 // proper numero de ticket
	private int idArticle;                  // proper id d'article
	private int stockArbres;                // número d'arbres en stock
	private int stockFlors; 				// número de flors en stock
	private int stockDecoracions; 			// número de decoracions en stock
	private float valorFloristeria;         // valor de tots els articles en stock
	private float valorVentes;				// suma de totes les ventes fetes
	private String ticketsAntics;			// llistat de tickets antics


	private static Floristeria floristeria;

	private Floristeria() {
		articles = new ArrayList<Article>();
		tickets = new ArrayList<Ticket>();
		codiTicket=1;
		idArticle=1;
		stockArbres=0;
		stockFlors=0;
		stockDecoracions=0;		
		valorFloristeria=0;
		valorVentes=0;	
		ticketsAntics="";
	}

	// faig servir singleton per asegurar-me de no crear més d'una floristeria

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

	public int getIdArticle() {
		return idArticle;
	}

	public String getTicketsAntics() {
		return ticketsAntics;
	}

	public float getValorVentes() {
		return valorVentes;
	}


	public int getStockArbres() {
		return stockArbres;
	}

	public int getStockFlors() {
		return stockFlors;
	}

	public int getStockDecoracions() {
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
		return codiTicket;
	}


	//STOCK

	// quan afeigeix un article sumo un a l'stock i un als id's

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
		idArticle++;

	}

	//quan esborro un article resto un a l'stock, al id no faig res

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

	//metode que crea un String amb tot l'stock

	public String creaStock() {
		String stock="";
		for (Article article : articles) {
			stock += article.toString();
		}
		return stock;		
	}	

	public String mostraArbres() {
		String arbres="";
		for (Article article : articles) {
			if (article instanceof Arbre) {
				arbres+=article.toString()+"\n";
			}	
		}
		return arbres;
	}
	public String mostraFlors() {
		String flors="";
		for (Article article : articles) {
			if (article instanceof Flor) {
				flors+=article.toString()+"\n";
			}	
		}
		return flors;
	}
	public String mostraDecoracions() {
		String decoracions="";
		for (Article article : articles) {
			if (article instanceof Decoracio) {
				decoracions+=article.toString()+"\n";
			}	
		}
		return decoracions;
	}

	/*
	 * metode que crea un fitxer amb el contingut de l'stock
	 * el fitxer está separat per comes "," i cada linea es un producte	
	 */

	public void escriuStock() {
		String fitxer = "Floristeria_"+this.nom.toLowerCase()+".txt";
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

	public void llegeixStock() {
		File file = new File("Floristeria_"+nom.toLowerCase()+".txt");
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
				int id = Integer.parseInt(separador.nextToken());
				float preu = Float.parseFloat(separador.nextToken());
				String caracteristica = separador.nextToken();

				//miro el tipus d'Article i el creo amb el seu contructor

				if (tipus.equals("A")) {
					article = new Arbre(id,preu,caracteristica);
				}else if (tipus.equals("F")) {
					article = new Flor(id,preu,caracteristica);				
				}else if (tipus.equals("D")) {
					Material material;
					if (caracteristica.equals("FUSTA")) {
						material = Material.Fusta;
					}else {
						material = Material.Plastic;
					}
					article = new Decoracio(id,preu,material);
				}
				addArticle(article);
				linea = bufferedReader.readLine();				
			}
			System.out.println("Carregat l'stock");
		} catch (IOException ex) {
			System.out.println("No existeix la floristeria, es crea nova");
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

	//TICKETS

	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
		this.codiTicket++;
	}

	//metode que crea un String amb el tickets
	public String creaTickets() {
		String vendes="";
		for (Ticket ticket : tickets) {
			vendes+=ticket.toString()+"\n";
		}	
		return vendes;
	}

	//crea un fitxer de text amb el tickets antics

	public void escriuTickets() {
		String fitxer = "Tickets_"+this.nom.toLowerCase()+".txt";
		ticketsAntics+=creaTickets();

		//actualitzo el valor de les ventes fetes
		for (Ticket ticket: tickets) {
			valorVentes+=ticket.getTotal();
		}

		File file = new File(fitxer);
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			if (ticketsAntics != null && ticketsAntics.length() > 0) {
				bufferedWriter.write(ticketsAntics);
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

	//metode que pasa el fitxer de tickets antics a un string

	public void llegeixTickets() {
		File file = new File("Tickets_"+this.nom.toLowerCase()+".txt");
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String linea = bufferedReader.readLine();
			while (linea != null) {
				this.ticketsAntics+=linea+"\n";					
				linea = bufferedReader.readLine();
			}
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

	// LOAD i SAVE Floristeria

	// Si existeixen dades antigues, les carrega en memoria	

	public void recuperaFloristeria() {					
		Properties propietats = new Properties();
		FileInputStream dades;
		try {
			llegeixStock();						
			llegeixTickets();			
			dades = new FileInputStream("Backup_"+nom.toLowerCase()+".data");
			propietats.load(dades);
			this.idArticle=Integer.parseInt(propietats.getProperty("idArticle"));
			this.codiTicket=Integer.parseInt(propietats.getProperty("codiTicket"));
			this.valorVentes=Float.parseFloat(propietats.getProperty("valorVentes"));
			dades.close();

		} catch (FileNotFoundException e) {
			System.err.println("No trobo el fitxer de backup");
		}catch (IOException e2) {
			System.out.println("No puc llegir el fitxer de backup");
		}						
	}

	//guarda la informació en fitxers quan es tanca l'aplicació 

	public void guardaFloristeria() {
		Properties propietats = new Properties();
		String fitxer = "Backup_"+nom.toLowerCase()+".data";
		File file = new File(fitxer);
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		escriuStock();
		escriuTickets();

		propietats.setProperty("idArticle",String.valueOf(idArticle));		
		propietats.setProperty("codiTicket",String.valueOf(codiTicket));
		propietats.setProperty("valorVentes",String.valueOf(valorVentes));		

		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			propietats.store(bufferedWriter,"propietats");
			System.out.println("Creo un backup amb totes les dades");

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
}


