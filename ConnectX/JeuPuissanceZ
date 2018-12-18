/**
 * @author Evans
 * @date 2018-12-18
 *
 */
import java.util.Arrays;

/**
 * Class which represents a ConnectX (a la Connect 4, but extendable) board game
 */
public class JeuPuissanceZ {
	/** Players */
	private final Joueur j1;
	private final Joueur j2;
	/** Grid */
	private boolean[][] grille;
	/** Number of pieces per Column */
	private final int[] nbPionsParColonne;
	/** Current player */
	private Joueur joueurCourant;
	/** Number of pieces to align in order to win */
	private final int nbAlignes;
	/** Number of player moves */
	private int nbCoupsJoues;
	/** Max number of moves */
	private final int nbMaxCoups;

	/**
	 * Constructor which sets the grid given the number of columns, lines, the
	 * target number of aligned pieces, and the two players.
	 * 
	 * @param nbColonnes
	 * @param nbLignes
	 * @param nbAlignes
	 * @param j1
	 * @param j2
	 */
	public JeuPuissanceZ(int nbColonnes, int nbLignes, int nbAlignes, Joueur j1, Joueur j2) {
		this.j1 = j1;
		this.j2 = j2;
		grille = new boolean[nbColonnes][nbLignes];
		nbPionsParColonne = new int[nbColonnes];
		joueurCourant = j1;
		this.nbAlignes = nbAlignes;
		this.nbCoupsJoues = 0;
		nbMaxCoups = nbColonnes * nbLignes;
	}

	/**
	 * Constructor which sets the grid given just the two players
	 * 
	 * @param j1
	 * @param j2
	 */
	public JeuPuissanceZ(Joueur j1, Joueur j2) {
		this.j1 = j1;
		this.j2 = j2;
		grille = new boolean[7][6];
		nbPionsParColonne = new int[7];
		joueurCourant = j1;
		nbAlignes = 4;
		nbCoupsJoues = 0;
		nbMaxCoups = 42;
	}

	/**
	 * Grille getter, for usage in tests
	 * 
	 * @return grille
	 */
	protected boolean[][] getGrille() {
		return grille;
	}

	/**
	 * j1 getter, for usage in tests
	 * 
	 * @return j1
	 */
	protected Joueur getJ1() {
		return j1;
	}

	/**
	 * j2 getter, for usage in tests
	 * 
	 * @return j2
	 */
	protected Joueur getJ2() {
		return j2;
	}

	/**
	 * nbPionsParColonne getter, for usage in tests
	 * 
	 * @return nbPionsParColonne
	 */
	protected int[] getNbPionsParColonne() {
		return nbPionsParColonne;
	}

	/**
	 * nbAlignes getter, for usage in tests
	 * 
	 * @return nbAlignes
	 */
	protected int getNbAlignes() {
		return nbAlignes;
	}

	/**
	 * nbCoupsJoues getter, for usage in tests
	 * 
	 * @return nbCoupsJoues
	 */
	protected int getNbCoupsJoues() {
		return nbCoupsJoues;
	}

	/**
	 * nbMaxCoups getter, for usage in tests
	 * 
	 * @return nbMaxCoups
	 */
	protected int getNbMaxCoups() {
		return nbMaxCoups;
	}

	/**
	 * Returns whether the given coordinate is occupied by one of the two
	 * players pieces.
	 * 
	 * @param colonne
	 * @param ligne
	 * @return boolean
	 */
	protected boolean contientPion(int colonne, int ligne) {
		if (colonne < 0 || colonne > grille.length || ligne < 0 || ligne > grille[0].length) {
			return false;
		} else {
			if (nbPionsParColonne[colonne] == 0) {
				return false;
			} else {
				if (ligne < nbPionsParColonne[colonne]) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	/**
	 * Sets the value in the given column at its first empty row (i.e. where the
	 * piece falls to)
	 * 
	 * @param colonne
	 * @param ligne
	 * @return boolean whether the given column is able to receive a piece or
	 *         not
	 */
	protected boolean jouer(int colonne) {
		if (nbCoupsJoues == nbMaxCoups || colonne < 0 || colonne > grille.length
				|| nbPionsParColonne[colonne] == grille.length) {
			return false;
		} else {
			if (joueurCourant.equals(j1)) {
				grille[colonne][nbPionsParColonne[colonne]] = true;
			}
			nbPionsParColonne[colonne]++;
			nbCoupsJoues++;
			return true;
		}
	}

	/**
	 * Returns an array of the non-full columns
	 * 
	 * @return int[]
	 */
	protected int[] coupsPossibles() {
		int[] coupsPossibles = new int[grille.length];
		int nbColonnesNonPleines = 0;

		for (int i = 0; i < grille.length; i++) {
			if (!(nbPionsParColonne[i] == grille[0].length)) {
				coupsPossibles[nbColonnesNonPleines] = i;
				nbColonnesNonPleines++;
			}
		}
		return Arrays.copyOf(coupsPossibles, nbColonnesNonPleines);
	}

	/**
	 * Returns the number of aligned pieces of the player whose piece is highest
	 * in the column (i.e. most recently played) in a given delta, i.e. which
	 * direction we want to move.
	 * 
	 * @param colonneDebut
	 * @param deltaC
	 * @param deltaL
	 * @return nbAlignes
	 */
	protected int nbAlignes(int colonneDebut, int deltaC, int deltaL) {
		if (nbPionsParColonne[colonneDebut] == 0) {
			return 0;
		} else {
			int alignes = 1;
			int ligne = nbPionsParColonne[colonneDebut] - 1;
			while (contientPion(colonneDebut + deltaC, ligne + deltaL)
					&& grille[colonneDebut][ligne] == grille[colonneDebut + deltaC][ligne + deltaL]) {
				alignes++;
				colonneDebut += deltaC;
				ligne += deltaL;
			}
			return alignes;

		}
	}

	/**
	 * joueurCourant setter, for testing
	 * 
	 * @param joueurCourant
	 */
	protected void setJoueurCourant(Joueur joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	/**
	 * Returns whether there is a winning alignment stemming from the given
	 * column
	 * 
	 * @param colonne
	 * @return boolean whether the game has been won or not
	 */
	protected boolean gagne(int colonne) {
		if (nbAlignes(colonne, -1, 0) == nbAlignes || nbAlignes(colonne, -1, -1) == nbAlignes
				|| nbAlignes(colonne, -1, 1) == nbAlignes || nbAlignes(colonne, 0, -1) == nbAlignes
				|| nbAlignes(colonne, 1, 1) == nbAlignes || nbAlignes(colonne, 1, 0) == nbAlignes
				|| nbAlignes(colonne, 1, -1) == nbAlignes) {
			return true;
		}

		else if (nbAlignes(colonne, -1, -1) + nbAlignes(colonne, 1, 1) > nbAlignes
				|| nbAlignes(colonne, -1, 1) + nbAlignes(colonne, 1, -1) > nbAlignes
				|| nbAlignes(colonne, -1, 0) + nbAlignes(colonne, 1, 0) > nbAlignes) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns a String representation of the grid
	 * 
	 * @return String
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < grille[0].length; i++) {
			s += " " + i + " ";
		}
		s += "\n";

		for (int i = grille[1].length; i > 0; i--) {
			for (int j = 0; j < grille[0].length; j++) {
				s += "|";
				if (grille[j][i] == false) {
					if (nbPionsParColonne[j] > i) {
						s += "2";
					} else {
						s += " ";
					}
				} else {
					s += "1";
				}
				s += "|";
			}
		}
		return s;
	}

}
