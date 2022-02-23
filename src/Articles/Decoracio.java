package Articles;

import java.util.Objects;

public class Decoracio extends Article{
	
	private Material material;
	
	public Decoracio(String nom, Material material, float preu) {
		this.nom = nom;
		this.material = material;
		this.preu = preu;
	}

	public Material getMaterial() {
		return material;
	}
	
	@Override
	public String toString() {
		return "Decoracio,"+this.nom+","+this.material+","+this.preu+"\n"; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(material);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean iguals=false;
		if(this.getClass().equals(obj.getClass())) {
			Decoracio decoracio = (Decoracio)obj; 
			iguals= this.nom.equals(decoracio.nom)&&this.preu==decoracio.preu&&this.material==decoracio.material;	
		}
		return iguals;
			
	}
}
