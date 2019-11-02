package fr.uga.miashs.inff3.evansc;

/**
 * Test class
 *
 * @author Conor Evans et Bronagh Carolan
 * @version 1.0
 * @since 2018-11-09
 */
public class TestCoordonnee {
	public static void main(String[] args) {
		 testCoordonnee("A1",0,0);
		/*
		 * Reponses egalite: true voisines: false compareTo: 0
		 */
		 testCoordonnee("A1",0,1);
		/*
		 * Reponses egalite: false voisines: true compareTo: <0
		 */

		 testCoordonnee("B1",0,0);
		/*
		 * Reponses egalite: false voisines: true compareTo: >0
		 */
	}

	/**
	 * Cette methode teste les constructeurs et les fonctions de la classe Coordonnee :
	 * toString(), getLigne(), getColonne(), equals(Coordonnee C), voisine(Coordonnee C), compareTo(Coordonnee C)
	 * 
	 * @param String s = coordonn�es en forme de cha�ne
	 * @param int x = ligne
	 * @param int y = colonne
	 */
	public static void testCoordonnee(String s, int x, int y) {

		Coordonnee c = new Coordonnee(s);
		System.out.println("Constructeur 1: " + c.toString());
		Coordonnee c2 = new Coordonnee(x, y);
		System.out.println("Constructeur 2: " + c2.toString());

		System.out.println("GetLigne: " + c.getLigne());
		System.out.println("GetColonne: " + c.getColonne());

		System.out.println("Egalit�: " + c.equals(c2));
		System.out.println("Voisines: " + c.voisine(c2));
		System.out.println("CompareTo: " + c.compareTo(c2) + "\n");

	}
}
