package Articles;

import java.util.Objects;

public class Flor extends Article{
	
	private String color;
		
	public Flor(int id, String color, float preu) {
		this.id = id;
		this.color = color;
		this.preu = preu;
	}

	public String getColor() {
		return color;
	}	
	
	@Override
	public String toString() {
		return "Flor,"+this.id+","+this.color+","+this.preu+"\n"; 
	}

}
