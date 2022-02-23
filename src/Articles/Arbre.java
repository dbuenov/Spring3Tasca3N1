package Articles;

import java.util.Objects;

public class Arbre extends Article{
	
	private String al�ada;
		
	public Arbre(String nom, String al�ada, float preu) {
		this.nom = nom;
		this.al�ada = al�ada;
		this.preu = preu;
	}

	public String getAl�ada() {
		return al�ada;
	}

	@Override
	public String toString() {
		return "Arbre,"+this.nom+","+this.al�ada+","+this.preu+"\n"; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(al�ada);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean iguals=false;
		if(this.getClass().equals(obj.getClass())) {
			Arbre arbre = (Arbre)obj; 
			iguals= this.nom.equals(arbre.nom)&&this.preu==arbre.preu&&this.al�ada.equals(arbre.al�ada);	
		}
		return iguals;
			
	}

	
}
