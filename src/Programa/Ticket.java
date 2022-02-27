package Programa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Articles.*;

public class Ticket {
	
	private ArrayList<Article> articles;
	private int codi;
	private float total;
	private int numeroArticles;
		
	public Ticket(int codi) {
		this.codi= codi;
		this.articles = new ArrayList<Article>();
		total = 0;
		numeroArticles = 0;
	}
	/*
	public Ticket(int codi, ArrayList<Article> articles, float total, int numeroArticles) {
		
	}
	*/

	public ArrayList<Article> getArticles() {
		return articles;
	}

	public int getCodi() {
		return codi;
	}
	
	public float getTotal() {
		return total;
	}
	
	public void addArticle(Article article) {
		this.articles.add(article);
		total += article.getPreu();		
		numeroArticles ++;
	}
	
	public void removeArticle(Article article) {
		this.articles.remove(article);
		total -= article.getPreu();
		numeroArticles --;
	}
	
	@Override
	public String toString() {
		String ticket="Ticket Numero: "+this.codi+"\n";
		for (Article article : articles) {
			ticket += article.getId()+" "+article.getPreu()+"\n";
		}
		ticket += "Numero d'articles: "+numeroArticles+"\n";
		ticket += "Total: "+total+" �";
		return ticket;
	}	
	
}
