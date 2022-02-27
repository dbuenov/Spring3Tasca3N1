package Articles;

import java.util.Scanner;

public class FabricaDecoracions extends FabricaArticles{

	@Override
	public Article creaArticle(int id) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Preu de la decoracio:");
		Material material;
		float preu = sc.nextFloat();
		byte opcio;
		do{
			System.out.println("\nMaterial de la decoracio:");
			System.out.println("1. Plàstic");
			System.out.println("2. Fusta");			
			opcio = sc.nextByte();
			if(opcio < 1 || opcio > 2){
				System.out.println("Escull una opció vàlida");
			}
		}while(opcio < 1 || opcio > 2);
		if (opcio == 1) {
			material = Material.Plastic;
		}else {
			material = Material.Fusta;
		}

		Article decoracio = new Decoracio(id,preu,material);
		return decoracio;
	}

}
