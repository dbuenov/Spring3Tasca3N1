package Articles;

import java.util.Objects;

public abstract class Article {
	
	protected float preu;
	protected String nom;

	public float getPreu() {
		return preu;
	}
	
	public String getNom() {
		return nom;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom, preu);
	}

	@Override
	public boolean equals(Object obj) {
		Article article = (Article)obj; 
		return this.nom.equals(article.nom)&&this.preu==article.preu;				 
	}	
	
	
}
