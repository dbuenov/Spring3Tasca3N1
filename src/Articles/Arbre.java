package Articles;

import java.util.Objects;

public class Arbre extends Article{
	
	private String alçada;
		
	public Arbre(String nom, String alçada, float preu) {
		this.nom = nom;
		this.alçada = alçada;
		this.preu = preu;
	}

	public String getAlçada() {
		return alçada;
	}

	@Override
	public String toString() {
		return "Arbre,"+this.nom+","+this.alçada+","+this.preu+"\n"; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(alçada);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean iguals=false;
		if(this.getClass().equals(obj.getClass())) {
			Arbre arbre = (Arbre)obj; 
			iguals= this.nom.equals(arbre.nom)&&this.preu==arbre.preu&&this.alçada.equals(arbre.alçada);	
		}
		return iguals;
			
	}

	
}
