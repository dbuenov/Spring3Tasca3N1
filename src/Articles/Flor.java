package Articles;

import java.util.Objects;

public class Flor extends Article{
	
	private String color;
		
	public Flor(String nom, String color, float preu) {
		this.nom = nom;
		this.color = color;
		this.preu = preu;
	}

	public String getColor() {
		return color;
	}	
	
	@Override
	public String toString() {
		return "Flor,"+this.nom+","+this.color+","+this.preu+"\n"; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(color);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean iguals=false;
		if(this.getClass().equals(obj.getClass())) {
			Flor flor = (Flor)obj; 
			iguals= this.nom.equals(flor.nom)&&this.preu==flor.preu&&this.color.equals(flor.color);	
		}
		return iguals;
			
	}
	
	
}
