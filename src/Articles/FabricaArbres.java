package Articles;

import java.util.Scanner;

public class FabricaArbres extends FabricaArticles{

	@Override
	public Article creaArticle(int id) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Alçada de l'arbre:");
		String alçada = sc.nextLine();
		System.out.println("Preu de l'arbre:");
		float preu = sc.nextFloat();
		Arbre arbre = new Arbre(id,preu,alçada);
		return arbre;
	}

}
