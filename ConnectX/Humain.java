import java.util.Arrays;
import java.util.Scanner;

public class Humain extends Joueur {
	private String nom;
	private Scanner sc = new Scanner(System.in);

	public Humain(String nom) {
		this.nom = nom;
	}

	public int choixColonne(JeuPuissanceZ grille) {
		int choix = -1;
		int[] pos = grille.coupsPossibles();
		do {
			System.out.println(grille);
			System.out.println("Donnez votre choix de colonne parmi " + Arrays.toString(pos) + " : ");
			choix = Integer.parseInt(sc.nextLine());
		} while (Arrays.binarySearch(pos, choix) < 0);
		
		return choix;
	}
	
	public String toString() {
		return nom;
	}

}
