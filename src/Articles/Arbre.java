package Articles;

public class Arbre extends Article{
	
	public Arbre(int id, float preu, String alçada) {
		this.id=id;
		this.preu=preu;
		this.alçada=alçada;
	}

	private String alçada;
	
			
	public String getAlçada() {
		return alçada;
	}

	@Override
	public String toString() {
		return "A,"+this.id+","+this.preu+","+this.alçada+"\n"; 
	}

}
