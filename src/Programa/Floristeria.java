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
	
	private String nom;
	private ArrayList<Article> articles;
	private ArrayList<Ticket> tickets;
	private int codiTicket;
	private int idArticle;
	private int stockArbres;
	private int stockFlors;
	private int stockDecoracions;
	private float valorFloristeria;
	private float valorVentes;
	
	//inicialitzar i treballar
	private String ticketsAntics;
	
	
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
	}
	
	//faig servir singleton per asegurar-me de no crear més d'una floristeria
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

	//quan afeigeix un article sumo un a l'stock i un als id's
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
	
	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
		this.codiTicket++;
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
		return codiTicket;
	}
	
	
	//metode que crea un String amb tot l'stock
	public String creaStock() {
		String stock="";
		for (Article article : articles) {
			stock += article.toString();
		}
		return stock;		
	}
	
	//metode que crea un String amb el tickets
	public String creaTickets() {
		String vendes="";
		for (Ticket ticket : tickets) {
			vendes+=ticket.toString();
		}	
		return vendes;
	}
	
	/*
	 * metode que crea un fitxer amb el contingut de l'stock
	 * el fitxer está separat per comes "," i cada linea es un producte	
	*/
	
	public void escriuStock() {
		
		String fitxer = "Floristeria_"+this.nom+".txt";
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
	
	public void escriuTickets() {
		
		String fitxer = "Tickets_"+this.nom+".txt";
		String ticketsFloristeria=creaTickets();

		File file = new File(fitxer);
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			if (ticketsFloristeria != null && ticketsFloristeria.length() > 0) {
				bufferedWriter.write(ticketsFloristeria);

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
				String caracteristica = separador.nextToken();
				float preu = Float.parseFloat(separador.nextToken());
				
				//miro el tipus d'Article i el creo amb el seu contructor
				
				if (tipus.equalsIgnoreCase("arbre")) {
					article = new Arbre(id,caracteristica,preu);
				}else if (tipus.equalsIgnoreCase("flor")) {
					article = new Flor(id,caracteristica,preu);				
				}else if (tipus.equalsIgnoreCase("decoracio")) {
					Material material;
					if (caracteristica.equals("FUSTA")) {
						material = Material.Fusta;
					}else {
						material = Material.Plastic;
					}
					article = new Decoracio(id,material,preu);
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
	
	//TODO
	//falta fer aquest metode que pasa un txt a un string
	public void llegeixTickets() {

		File file = new File("Tickets_"+this.nom+".txt");
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String ticketsFloristeria=null;

		try {
			fileReader = new FileReader(file, StandardCharsets.UTF_8);
			bufferedReader = new BufferedReader(fileReader);

			String linea = bufferedReader.readLine();
			while (linea != null) {
				ticketsFloristeria+=linea;
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
	
	public void recuperaFloristeria() {					
		Properties propietats = new Properties();
		FileInputStream dades;
		try {
			dades = new FileInputStream("Backup_"+nom+".data");
			propietats.load(dades);
			this.codiTicket=Integer.parseInt(propietats.getProperty("codiTicket"));
			this.valorVentes=Float.parseFloat(propietats.getProperty("valorVentes"));
			dades.close();
			llegeixStock();						
		} catch (FileNotFoundException e) {
			System.out.println("No trobo el fitxer de backup");
		}catch (IOException e2) {
			System.out.println("No puc llegir el fitxer de backup");
		}						
	}
			
	public void guardaFloristeria() {
		Properties propietats = new Properties();
		String fitxer = "Backup_"+nom+".data";
		File file = new File(fitxer);
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		
		propietats.setProperty("codiTicket",String.valueOf(codiTicket));
		propietats.setProperty("valorVentes",String.valueOf(valorVentes));		
				
		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			propietats.store(bufferedWriter,"propietats");
			escriuStock();
			escriuTickets();
			
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
		

