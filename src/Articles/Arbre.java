package Articles;

public class Arbre extends Article{
	
	public Arbre(int id, float preu, String al�ada) {
		this.id=id;
		this.preu=preu;
		this.al�ada=al�ada;
	}

	private String al�ada;
	
			
	public String getAl�ada() {
		return al�ada;
	}

	@Override
	public String toString() {
		return "A,"+this.id+","+this.preu+","+this.al�ada+"\n"; 
	}

}
