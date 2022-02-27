package Articles;

import java.util.Scanner;

public class FabricaFlors extends FabricaArticles{

	@Override
	public Article creaArticle(int id) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Color de la flor:");
		String color = sc.nextLine();
		System.out.println("Preu de la flor:");
		float preu = sc.nextFloat();
		Article flor = new Flor(id,preu,color);
		return flor;
	}

}
