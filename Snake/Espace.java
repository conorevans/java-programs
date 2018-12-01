
/**
 * @author Conor Evans 
 * @version 1.0
 * @date 2018-11-28
 * 
 * Represents a grid of Cases (co-ordinates) for the Chenille (caterpillar)
 * to move around in.
 *  
 */
import java.util.Arrays;
import java.util.Random;

public class Espace {

	/** width */
	private int largeur;
	/** height */
	private int hauteur;
	/** array of caterpillars to be placed on our grid */
	private Chenille[] chenilles;
	/** number of caterpillars on our grid */
	private int nbChenilles;

	/**
	 * Default constructor which makes a 10x10 square grid with 5 potential
	 * caterpillars, none as of yet instanciated.
	 */
	public Espace() {
		this.largeur = 10;
		this.hauteur = 10;
		this.chenilles = new Chenille[5];
		this.nbChenilles = 0;
	}

	/**
	 * Constructor which makes a i x i square grid with 5 potential
	 * caterpillars, none as of yet instantiated.
	 * 
	 * @param {int} i - height/width
	 */
	public Espace(int i) {
		this.largeur = i;
		this.hauteur = i;
		this.chenilles = new Chenille[5];
		this.nbChenilles = 0;
	}

	/**
	 * Constructor which makes a i x j square grid with 5 potential
	 * caterpillars, none as of yet instantiated.
	 * 
	 * @param {int} i - width
	 * @param {int} j - height
	 */
	public Espace(int i, int j) {
		this.largeur = i;
		this.hauteur = j;
		this.chenilles = new Chenille[5];
		this.nbChenilles = 0;
	}

	/** Adds caterpillar to the grid
	 * @param {Chenille} c
	 */
	public void addChenille(Chenille c) {
		//if our initial array of caterpillars is full and we wish to add another caterpillar
		//we augment the array.
		if (this.chenilles.length == this.nbChenilles) {
			this.chenilles = Arrays.copyOf(this.chenilles, this.chenilles.length + 5);
			this.chenilles[this.chenilles.length] = c;
			this.nbChenilles++;
		} else {
			this.chenilles[this.nbChenilles] = c;
			this.nbChenilles++;
		}
	}

	/** Verifies if c is a valid co-ordinate in this grid
	 * @param {Case} c
	 * @return boolean
	 */
	public boolean contient(Case c) {
		return !(c.getAbscisse() > this.largeur || c.getAbscisse() < 0 || c.getOrdonnee() < 0 || c.getOrdonnee() > this.hauteur);
	}

	/** Verifies if current co-ordinate is already occupied
	 * @param {Case} c
	 * @return boolean
	 */
	public boolean caseOccupee(Case c) {
		for (int i = 0; i < this.nbChenilles; i++) {
			if (this.chenilles[i].estSur(c)) {
				return true;
			}
		}
		return false;
	}
	
	/** Returns a random coordinate in the grid
	 * @return {Case}
	 */
	public Case caseAuHasard() {
		Random r = new Random();
		return new Case(r.nextInt(largeur), r.nextInt(hauteur));
	}
	
	/** Returns a random unoccupied coordinate in the grid
	 * @return {Case}
	 */
	public Case caseLibreAuHasard() {
		Case has = caseAuHasard();
		if (!caseOccupee(has)) {
			return has;
		}
		return caseLibreAuHasard();
	}

	/**
	 * Represents the grid as a String
	 * return {String}
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < this.hauteur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				Case c = new Case(j, i);
				//set base grid
				if (!caseOccupee(c)) {
					s += ". ";
				} else {
					for (int k = 0; k < this.nbChenilles; k++) {
						if (this.chenilles[k].estSur(c)) {
							Case[] chenille = this.chenilles[k].getCases();
							//capital to represent head of the caterpillar
							if (c.equals(chenille[0])) {
								s += (char) (k + 'A') + " ";
							} else {
								s += (char) (k + 'a') + " ";
							}
						}
					}

				}
			}
			s += "\n";
		}
		return s;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public Chenille[] getChenilles() {
		return chenilles;
	}

	public int getNbChenilles() {
		return nbChenilles;
	}
}
