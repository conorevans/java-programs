package fr.uga.miashs.inff3.evansc;

/**
* Cette classe teste la classe GrilleNavale.
*
* @author Conor Evans et Bronagh Carolan
* @version 1.0
* @since 2018-11-16
*/


public class TestGrilleNavale {

	public static void main(String[] args) {
		
		GrilleNavale g = new GrilleNavale(10);
		int[] i = new int[]{5,4,3,3};
		testPlacementAuto(g, i);
		
		testAjouteNavire();
		testRecoitTir();
		testEstTouche();
		testEstAlEau();
		testPerdu();
		testGetTaille();
		testEstCoule();
	}

	/**
	 * Cette methode test la methode ajouteNavire(Navire n)
	 * 
	 * @return void
	 */
	public static void testAjouteNavire() {
		System.out.println("\nTest AjouteNavire");
		/*
		 * Un test avec 2 navires qui ne touchent pas et qui ne chevauchent pas
		 * 
		 * Sortie:  Test 1 true true
		 * 
		 */
		GrilleNavale g = new GrilleNavale(10);
		Coordonnee c1 = new Coordonnee(0, 2);
		Coordonnee c2 = new Coordonnee(3, 3);
		Navire n1 = new Navire(c1,3,true);
		Navire n2 = new Navire(c2,2,true);
		
		System.out.println("Test 1: " + g.ajouteNavire(n1) + " ");
		System.out.print(g.ajouteNavire(n2));
		
		/* Un test avec 2 navires qui touchent et qui ne chevauchent pas
		 * 
		 * Sortie:  Test 2 true false
		 */
		 g = new GrilleNavale(10);
		 c1 = new Coordonnee(0, 2);
		 c2 = new Coordonnee(2, 3);
		 n1 = new Navire(c1,3,true);
		 n2 = new Navire(c2,2,true);
		 System.out.println("\nTest 2: " + g.ajouteNavire(n1) + " ");
		 System.out.print(g.ajouteNavire(n2));
		 
		 /*
		 * Un test avec 2 navires qui chevauchent
		 * 
		 * Sortie:  Test 3 true false
		 */
		 g = new GrilleNavale(10);
		 c1 = new Coordonnee(0, 2);
		 c2 = new Coordonnee(2, 2);
		 n1 = new Navire(c1,3,true);
		 n2 = new Navire(c2,2,true);
		 System.out.println("\nTest 3: " + g.ajouteNavire(n1) + " ");
		 System.out.print(g.ajouteNavire(n2));
			 
		 /*
		  * Un test avec 2 navires qui sortent des limites de la grille
		  * 
		  * Sortie:  Test 4 false false
		  */
		 g = new GrilleNavale(10);
		 c1 = new Coordonnee(11, 2);
		 c2 = new Coordonnee(12, 2);
		 n1 = new Navire(c1,3,true);
		 n2 = new Navire(c2,2,true);
		 System.out.println("\nTest 4: " + g.ajouteNavire(n1) + " ");
		 System.out.print(g.ajouteNavire(n2));

		

	}
	/**
	 * Cette methode test la methode placementAuto(int[] tailles)
	 * 
	 * @return void
	 */
	public static void testPlacementAuto(GrilleNavale g, int[] tailles) {
		System.out.println("\nTest PlacementAuto");
		
		g.placementAuto(tailles);
		
		

	}
	/**
	 * Cette methode test la methode recoitTir(Coordonnee c)
	 * 
	 * @return void
	 */
	public static void testRecoitTir() {
		System.out.println("Test RecoitTir");
		GrilleNavale g = new GrilleNavale(10);
		Coordonnee c1 = new Coordonnee(0, 2);
		Coordonnee c2 = new Coordonnee(3, 3);
		Navire n1 = new Navire(c1,3,true);
		Navire n2 = new Navire(c2,2,true);
		g.ajouteNavire(n1);
		g.ajouteNavire(n2);
		/*
		 * Un test avec une Coordonnee qui est dans un Navire
		 * Sortie:  Test 1 true
		*/
		System.out.println("Test 1: " + g.recoitTir(c1));
		
		/*
		 * Un test avec une Coordonnee qui n'est pas dans un Navire
		 * Sortie:  Test 2 false
		*/
		Coordonnee c = new Coordonnee(0, 4);		
		System.out.println("Test 2: " + g.recoitTir(c));
		
		/*
		 * Un test avec une Coordonnee qui n'est pas dans les limites de la Grille
		 * Sortie:  Test 3 false
		*/
		System.out.println("Test 3: " + g.recoitTir(new Coordonnee(0, 14)));
		
		/*
		 * Un test avec une Coordonnee qui est deja dans "tirsRecu"
		 * Sortie:  Test 4 false
		*/
		System.out.println("Test 4: " + g.recoitTir(c1));
	}
	/**
	 * Cette methode teste la methode estTouche(Coordonnee c)
	 * 
	 * @return void
	*/
	public static void testEstTouche(){
		System.out.println("Test EstTouche");
		GrilleNavale g = new GrilleNavale(10);
		Coordonnee c1 = new Coordonnee(0, 2);
		Coordonnee c2 = new Coordonnee(3, 3);
		Navire n1 = new Navire(c1,3,true);
		Navire n2 = new Navire(c2,2,true);
		g.ajouteNavire(n1);
		g.ajouteNavire(n2);
		
		g.recoitTir(c1);
		g.recoitTir(new Coordonnee(5, 9));
		
		/*
		 * Un test avec une Coordonnee qui est dans un Navire et qui est touch�
		 * Sortie:  Test 1 true
		*/
		System.out.println("Test 1: " + g.estTouche(c1));
		
		/*
		 * Un test avec une Coordonnee qui est dans un Navire et qui n'est pas touch�
		 * Sortie:  Test 2 false
		*/
		System.out.println("Test 2: " + g.estTouche(c2));
		/*
		 * Un test avec une Coordonnee qui n'est pas dans un Navire et qui est touch�
		 * Sortie:  Test 3 false
		*/
		System.out.println("Test 3: " + g.estTouche(new Coordonnee(5, 9)));
		
	}
	
	/**
	 * Cette methode test la methode estALEau(Coordonnee c)
	 * 
	 * @return void
	 */
	public static void testEstAlEau() {
		System.out.println("Test EstAlEau");
		GrilleNavale g = new GrilleNavale(10);
		Coordonnee c1 = new Coordonnee(0, 2);
		Coordonnee c2 = new Coordonnee(3, 3);
		Navire n1 = new Navire(c1,3,true);
		Navire n2 = new Navire(c2,2,true);
		g.ajouteNavire(n1);
		g.ajouteNavire(n2);
		g.recoitTir(c1);
		g.recoitTir(new Coordonnee(5,9));
		/*
		 * Un test avec une Coordonnee qui est dans un Navire et qui est touch�
		 * Sortie:  Test 1 false
		*/
		System.out.println("Test 1: " + g.estALEau(c1));
		
		/*
		 * Un test avec une Coordonnee qui n'est pas dans un Navire et qui est touch�
		 * Sortie:  Test 2 true
		*/
		System.out.println("Test 2: " + g.estALEau(new Coordonnee(5,9)));
		
		/*
		 * Un test avec une Coordonnee qui n'est pas dans un Navire et qui n'est pas touch�
		 * Sortie:  Test 3 false
		*/
		System.out.println("Test 3: " + g.estALEau(new Coordonnee(5,10)));
		
		/*
		 * Un test avec une Coordonnee qui est dans un Navire et qui n'est pas touch�
		 * Sortie:  Test 4 false
		*/
		System.out.println("Test 4: " + g.estALEau(c1));
		
	}
	/**
	 * Cette m�thode test la m�thode perdu()
	 * 
	 * @return void
	 */
	public static void testPerdu() {
		System.out.println("Test Perdu");
		GrilleNavale g = new GrilleNavale(10);
		Coordonnee c1 = new Coordonnee(0, 2);
		Coordonnee c2 = new Coordonnee(3, 3);
		Navire n1 = new Navire(c1,2,true);
		Navire n2 = new Navire(c2,2,true);
		g.ajouteNavire(n1);
		g.ajouteNavire(n2);
		g.recoitTir(c1);
		g.recoitTir(c2);
		
		/*
		 * Tous les navires de la grille ne sont pas coul�s
		 * Sortie:  Test 1 false
		*/
		System.out.println("Test 1: " + g.perdu());
		
		/*
		 * Tous les navires de la grille sont coul�s
		 * Sortie:  Test 2 true
		*/
		g.recoitTir(n1.getFin());
		g.recoitTir(n2.getFin());
		System.out.println("Test 2: " + g.perdu());
		
	}
	/**
	 * Cette m�thode test la m�thode getTaille()
	 * 
	 * @return void
	 */
	public static void testGetTaille()
	{
		System.out.println("Test getTaille");
		GrilleNavale g = new GrilleNavale(10);
		/*
		 * Sortie:  Test 1 10
		*/
		System.out.println("Test 1: " + g.getTaille());
		
		g = new GrilleNavale(0);
		/*
		 * Sortie:  Test 2 0
		*/
		System.out.println("Test 2: " + g.getTaille());
	}
	/**
	 * Cette m�thode test la m�thode estCoule(Coordonnee c)
	 * 
	 * @return void
	 */
	public static void testEstCoule(){
		System.out.println("Test estCoule");
		GrilleNavale g = new GrilleNavale(10);
		Coordonnee c1 = new Coordonnee(0, 2);
		Coordonnee c2 = new Coordonnee(3, 3);
		Navire n1 = new Navire(c1,2,true);
		Navire n2 = new Navire(c2,2,true);
		g.ajouteNavire(n1);
		g.ajouteNavire(n2);
		g.recoitTir(c1);
		g.recoitTir(c2);
		
		/*
		 * Un test avec une Coordonnee qui est dans un Navire mais le Navire n'est pas coul�
		 * Sortie:  Test 1 false
		*/
		System.out.println("Test 1: " + g.estCoule(c1));
		/*
		 * Un test avec une Coordonnee qui n'est pas dans un Navire 
		 * Sortie:  Test 2 false
		*/
		System.out.println("Test 2: " + g.estCoule(new Coordonnee(6, 9)));
		/*
		 * Un test avec une Coordonnee qui n'est pas la grille
		 * Sortie:  Test 3 false
		*/
		System.out.println("Test 3: " + g.estCoule(new Coordonnee(11, 9)));
		
		/*
		 * Un test avec une Coordonnee qui est dans un Navire et le Navire est coul�
		 * Sortie:  Test 4 true
		*/
		g.recoitTir(n1.getFin());
		System.out.println("Test 4: " + g.estCoule(c1));
	}
	
	
}