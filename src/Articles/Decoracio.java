package Articles;

import java.util.Objects;

public class Decoracio extends Article{
	
	private Material material;
	
	public Decoracio(int id, Material material, float preu) {
		this.id = id;
		this.material = material;
		this.preu = preu;
	}

	public Material getMaterial() {
		return material;
	}
	
	@Override
	public String toString() {
		return "Decoracio,"+this.id+","+this.material+","+this.preu+"\n"; 
	}
}
