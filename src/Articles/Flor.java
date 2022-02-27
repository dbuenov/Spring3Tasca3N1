package Articles;

public class Flor extends Article{
	
	public Flor(int id, float preu, String color) {
		this.id=id;
		this.preu=preu;
		this.color=color;
	}

	private String color;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "F,"+this.id+","+this.preu+","+this.color+"\n"; 
	}



	

}
