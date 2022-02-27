package Articles;


public class Decoracio extends Article{
	
	public Decoracio(int id, float preu, Material material) {
		this.id=id;
		this.preu=preu;
		this.material=material;
	}

	private Material material;
	
	public Material getMaterial() {
		return material;
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "D,"+this.id+","+this.preu+","+this.material+"\n"; 
	}
}
