package Articles;

public class Arbre extends Article{
	
	private String al�ada;
		
	public Arbre(int id, String al�ada, float preu) {
		this.al�ada = al�ada;
		this.preu = preu;
		this.id=id;
		
	}

	public String getAl�ada() {
		return al�ada;
	}

	@Override
	public String toString() {
		return "Arbre,"+this.id+","+this.al�ada+","+this.preu+"\n"; 
	}

}
