package fr.uga.miashs.inff3.evansc;

/**
 * Cette classe teste la classe Navire.
 *
 * @author Conor Evans et Bronagh Carolan
 * @version 1.0
 * @since 2018-11-09
 */
public class TestNavire {
	public static void main(String[] args) {
		
		Coordonnee c1 = new Coordonnee("A1");
		Coordonnee c2 = new Coordonnee("A4");
		Navire n1 = new Navire(c1, 3, true);
		Navire n2 = new Navire(c2, 3, true);

		testNavire(n1, n2);
		/*
		 * Debut: A1 Fin: A3 Parties Touchees :
		 * 
		 * Debut: A1 Fin: A3
		 * 
		 * Contient: true false Chevauche: false Touche: true RecoitTir: true EstTouche:
		 * true false EstCoule: false
		 */

		c1 = new Coordonnee("A1");
		c2 = new Coordonnee("A3");
		n1 = new Navire(c1, 3, true);
		n2 = new Navire(c2, 3, true);
		testNavire(n1, n2);
		/*
		 * Debut: A1 Fin: A3 Parties Touchees :
		 * 
		 * Debut: A1 Fin: A3
		 * 
		 * Contient: true false Chevauche: true Touche: false RecoitTir: true EstTouche:
		 * true false EstCoule: false
		 */

		c1 = new Coordonnee("A1");
		c2 = new Coordonnee("B1");
		n1 = new Navire(c1, 3, false);
		n2 = new Navire(c2, 3, true);
		testNavire(n1, n2);
		/*
		 * Debut: A1 Fin: A3 Parties Touchees :
		 * 
		 * Debut: A1 Fin: A3
		 * 
		 * Contient: true false Chevauche: true Touche: false RecoitTir: true EstTouche:
		 * true false EstCoule: false
		 */

		c1 = new Coordonnee("B1");
		c2 = new Coordonnee("C1");
		n1 = new Navire(c1, 3, false);
		n2 = new Navire(c2, 3, false);
		testNavire(n1, n2);
		/*
		 * Debut: A1 Fin: A3 Parties Touchees :
		 * 
		 * Debut: A1 Fin: A3
		 * 
		 * Contient: true false Chevauche: true Touche: false RecoitTir: true EstTouche:
		 * true false EstCoule: false
		 */

		c1 = new Coordonnee("A2");
		c2 = new Coordonnee("B1");
		n1 = new Navire(c1, 3, false);
		n2 = new Navire(c2, 3, true);
		testNavire(n1, n2);
		/*
		 * Debut: A1 Fin: A3 Parties Touchees :
		 * 
		 * Debut: A1 Fin: A3
		 * 
		 * Contient: true false Chevauche: true Touche: false RecoitTir: true EstTouche:
		 * true false EstCoule: false
		 */

		c1 = new Coordonnee("A1");
		c2 = new Coordonnee("G5");
		n1 = new Navire(c1, 1, false);
		n2 = new Navire(c2, 3, true);
		testNavire(n1, n2);
		/*
		 * Debut: A1 Fin: A3 Parties Touchees :
		 * 
		 * Debut: A1 Fin: A3
		 * 
		 * Contient: true false Chevauche: false Touche: false RecoitTir: true
		 * EstTouche: true true EstCoule: true
		 */
		
		
	}

	/**
	 * Cette methode teste les constructeurs et les fonctions de la classe Navire :
	 * toString(), getDebut(), getFin(), contient(Coordonnee C), chevauche(Navire
	 * N), touche(Navire N), recoitTir(Coordonnee C), estTouche(Coordonnee C),
	 * estCoule()
	 * 
	 * @param Navire n1,n2 = deux navires
	 */

	public static void testNavire(Navire n1, Navire n2) {

		System.out.println(n1.toString() + "\n" + "\nDebut: " + n1.getDebut().toString() + "\nFin: "
				+ n1.getFin().toString() + "\n");
		System.out.println("Contient: " + n1.contient(n1.getDebut()) + " " + n1.contient(new Coordonnee("G5")));
		System.out.println("Chevauche: " + n1.chevauche(n2));
		System.out.println("Touche: " + n1.touche(n2));
		System.out.println("RecoitTir: " + n1.recoitTir(n1.getDebut()));
		System.out.println("EstTouche: " + n1.estTouche(n1.getDebut()) + " " + n1.estTouche(n1.getFin()));
		System.out.println("EstCoule: " + n1.estCoule());

	}

}
