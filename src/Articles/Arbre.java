package Articles;

public class Arbre extends Article{
	
	private String alçada;
		
	public Arbre(int id, String alçada, float preu) {
		this.alçada = alçada;
		this.preu = preu;
		this.id=id;
		
	}

	public String getAlçada() {
		return alçada;
	}

	@Override
	public String toString() {
		return "Arbre,"+this.id+","+this.alçada+","+this.preu+"\n"; 
	}

}
