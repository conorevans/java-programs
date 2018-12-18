import static org.junit.Assert.*;

import org.junit.Test;

public class JeuPuissanceZTest {

	@Test
	public void testConstructors() {
		Joueur j1 = new Humain("A");
		Joueur j2 = new Humain("B");
		JeuPuissanceZ jeu = new JeuPuissanceZ(5, 5, 3, j1, j2);
		assertEquals("Testing constructor A", 5, jeu.getGrille()[0].length);
		assertEquals("Testing constructor A", true, jeu.getJ1().equals(j1));
		assertEquals("Testing constructor A", true, jeu.getJ2().equals(j2));
		assertEquals("Testing constructor A", 3, jeu.getNbAlignes());
		assertEquals("Testing constructor A", 5, jeu.getNbPionsParColonne().length);
		assertEquals("Testing constructor A", 0, jeu.getNbCoupsJoues());
		assertEquals("Testing constructor A", 25, jeu.getNbMaxCoups());

		jeu = new JeuPuissanceZ(j1, j2);
		assertEquals("Testing constructor B", 6, jeu.getGrille()[0].length);
		assertEquals("Testing constructor B", true, jeu.getJ1().equals(j1));
		assertEquals("Testing constructor B", true, jeu.getJ2().equals(j2));
		assertEquals("Testing constructor B", 4, jeu.getNbAlignes());
		assertEquals("Testing constructor B", 7, jeu.getNbPionsParColonne().length);
		assertEquals("Testing constructor B", 0, jeu.getNbCoupsJoues());
		assertEquals("Testing constructor B", 42, jeu.getNbMaxCoups());
	}

	@Test
	public void testContientPion() {
		Joueur j1 = new Humain("A");
		Joueur j2 = new Humain("B");
		JeuPuissanceZ jeu = new JeuPuissanceZ(j1, j2);
		assertEquals("Testing contientPion()", false, jeu.contientPion(0, 0));
		assertEquals("Testing contientPion()", false, jeu.contientPion(-1, 0));
		jeu.jouer(0);
		assertEquals("Testing contientPion()", true, jeu.contientPion(0, 0));
		assertEquals("Testing contientPion()", false, jeu.contientPion(0, 1));
		jeu.jouer(0);
		assertEquals("Testing contientPion()", true, jeu.contientPion(0, 1));
	}

	@Test
	public void testJouer() {
		Joueur j1 = new Humain("A");
		Joueur j2 = new Humain("B");
		JeuPuissanceZ jeu = new JeuPuissanceZ(j1, j2);
		assertEquals("Testing jouer()", 0, jeu.getNbPionsParColonne()[0]);
		assertEquals("Testing jouer()", 0, jeu.getNbCoupsJoues());
		jeu.jouer(0);
		assertEquals("Testing jouer()", 1, jeu.getNbPionsParColonne()[0]);
		assertEquals("Testing jouer()", 1, jeu.getNbCoupsJoues());

		assertEquals("Testing jouer()", false, jeu.jouer(-1));
	}

	@Test
	public void testCoupsPossibles() {
		Joueur j1 = new Humain("A");
		Joueur j2 = new Humain("B");
		JeuPuissanceZ jeu = new JeuPuissanceZ(j1, j2);
		assertEquals("Testing coupsPossibles()", 7, jeu.coupsPossibles().length);
		jeu.jouer(0);
		jeu.jouer(0);
		jeu.jouer(0);
		jeu.jouer(0);
		jeu.jouer(0);
		jeu.jouer(0);
		// Column 0 has been filled, size of possible columns array should have
		// decreased by 1.
		assertEquals("Testing coupsPossibles()", 6, jeu.coupsPossibles().length);
	}

	@Test
	public void testNbAlignesEtGagne() {
		Joueur j1 = new Humain("A");
		Joueur j2 = new Humain("B");
		JeuPuissanceZ jeu = new JeuPuissanceZ(j1, j2);
		assertEquals("Testing nbAlignes()", 0, jeu.nbAlignes(0, 1, 0));
		jeu.jouer(0);
		assertEquals("Testing nbAlignes()", 1, jeu.nbAlignes(0, 1, 0));
		jeu.jouer(1);
		assertEquals("Testing nbAlignes()", 2, jeu.nbAlignes(0, 1, 0));
		jeu.jouer(2);
		assertEquals("Testing gagne()", false, jeu.gagne(0));
		jeu.jouer(3);
		assertEquals("Testing nbAlignes()", 4, jeu.nbAlignes(0, 1, 0));

		// test that a horizontal success works left-right and right-left
		assertEquals("Testing gagne()", true, jeu.gagne(0));
		assertEquals("Testing gagne()", true, jeu.gagne(3));

		jeu = new JeuPuissanceZ(j1, j2);
		assertEquals("Testing nbAlignes()", 0, jeu.nbAlignes(0, 0, -1));
		jeu.jouer(0);
		assertEquals("Testing nbAlignes()", 1, jeu.nbAlignes(0, 0, -1));
		jeu.jouer(0);
		assertEquals("Testing nbAlignes()", 2, jeu.nbAlignes(0, 0, -1));
		jeu.jouer(0);
		assertEquals("Testing gagne()", false, jeu.gagne(0));
		jeu.jouer(0);
		assertEquals("Testing nbAlignes()", 4, jeu.nbAlignes(0, 0, -1));

		// test that a vertical success works
		assertEquals("Testing gagne()", true, jeu.gagne(0));

		jeu = new JeuPuissanceZ(j1, j2);
		assertEquals("Testing nbAlignes()", 0, jeu.nbAlignes(0, 1, 1));
		jeu.jouer(0);
		assertEquals("Testing nbAlignes()", 1, jeu.nbAlignes(0, 1, 1));
		jeu.setJoueurCourant(j2);
		jeu.jouer(1);
		jeu.jouer(2);
		jeu.jouer(2);
		jeu.jouer(3);
		jeu.jouer(3);
		jeu.jouer(3);
		jeu.setJoueurCourant(j1);
		jeu.jouer(1);
		assertEquals("Testing nbAlignes()", 2, jeu.nbAlignes(0, 1, 1));
		jeu.jouer(2);
		jeu.jouer(3);
		assertEquals("Testing nbAlignes()", 4, jeu.nbAlignes(0, 1, 1));

		// testing diagonal success works NE/SW and vice versa.
		assertEquals("Testing gagne()", true, jeu.gagne(0));
		assertEquals("Testing gagne()", true, jeu.gagne(3));

		jeu = new JeuPuissanceZ(j1, j2);
		assertEquals("Testing nbAlignes()", 0, jeu.nbAlignes(3, -1, 1));
		jeu.jouer(3);
		assertEquals("Testing nbAlignes()", 1, jeu.nbAlignes(3, -1, 1));
		jeu.setJoueurCourant(j2);
		jeu.jouer(2);
		jeu.jouer(1);
		jeu.jouer(1);
		jeu.jouer(0);
		jeu.jouer(0);
		jeu.jouer(0);
		jeu.setJoueurCourant(j1);
		jeu.jouer(2);
		assertEquals("Testing nbAlignes()", 2, jeu.nbAlignes(3, -1, 1));
		jeu.jouer(1);
		jeu.jouer(0);
		assertEquals("Testing nbAlignes()", 4, jeu.nbAlignes(3, -1, 1));

		// testing diagonal success works NW/SE and vice versa.
		assertEquals("Testing gagne()", true, jeu.gagne(0));
		assertEquals("Testing gagne()", true, jeu.gagne(3));

		jeu = new JeuPuissanceZ(j1, j2);
		jeu.jouer(0);
		jeu.jouer(1);
		jeu.jouer(3);
		assertEquals("Testing nbAligne()", 2, jeu.nbAlignes(0, 1, 0));
		assertEquals("Testing nbAligne()", 1, jeu.nbAlignes(3, -1, 0));
		assertEquals("Testing gagne()", false, jeu.gagne(0));
		assertEquals("Testing gagne()", false, jeu.gagne(3));

		// testing for x in a row where the current token is placed in between
		// two horizontal groups of tokens
		// which, all three combined, are equal to or greater than the number of
		// aligned pieces we need
		// eg xx_x -> xxxx = 4, xx_xx -> xxxxx = 5 (as valid a win as four),
		// etc.
		// all cases work, only xx_x shown in this instance
		jeu.jouer(2);
		assertTrue("Testing nbAligne()", jeu.nbAlignes(0, 1, 0) > 3);
		assertTrue("Testing nbAligne()", jeu.nbAlignes(3, -1, 0) > 3);
		assertEquals("Testing gagne()", true, jeu.gagne(0));
		assertEquals("Testing gagne()", true, jeu.gagne(3));

		jeu = new JeuPuissanceZ(j1, j2);
		jeu.jouer(0);
		jeu.jouer(0);
		jeu.jouer(0);
		jeu.jouer(1);
		jeu.jouer(1);
		jeu.jouer(2);
		jeu.setJoueurCourant(j2);
		jeu.jouer(0);
		jeu.jouer(1);
		jeu.jouer(3);

		assertEquals("Testing nbAligne()", 2, jeu.nbAlignes(0, 1, -1));
		assertEquals("Testing nbAligne()", 1, jeu.nbAlignes(3, -1, 1));
		assertEquals("Testing gagne()", false, jeu.gagne(0));
		assertEquals("Testing gagne()", false, jeu.gagne(3));

		// testing for x in a row where the current token is placed in between
		// two diagonal groups of tokens
		// which, all three combined, are equal to or greater than the number of
		// aligned pieces we need
		// both diagonals work, NW/SE shown.

		jeu.jouer(2);
		assertTrue("Testing nbAligne()", jeu.nbAlignes(0, 1, -1) > 3);
		assertTrue("Testing nbAligne()", jeu.nbAlignes(3, -1, 1) > 3);
		assertEquals("Testing gagne()", true, jeu.gagne(0));
		assertEquals("Testing gagne()", true, jeu.gagne(3));

	}

	@Test
	public void testHasardeux() {

		Joueur j1 = new Hasardeux();
		Joueur j2 = new Hasardeux();

		JeuPuissanceZ jeu = new JeuPuissanceZ(j1, j2);
		assertEquals("Testing hasardeux()", 0, jeu.getNbCoupsJoues());

		jeu.jouer(j1.choixColonne(jeu));
		assertEquals("Testing hasardeux()", 1, jeu.getNbCoupsJoues());

		jeu.jouer(j2.choixColonne(jeu));
		assertEquals("Testing hasardeux()", 2, jeu.getNbCoupsJoues());

	}

}
