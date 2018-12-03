
/**
 * @author Conor Evans
 * @version 1.0
 * @date 2018-11-28
 * 
 * Represents a Chenille (caterpillar) to move around in a grid (like Snake - the famous mobile application game)
 *  
 */

import java.util.Arrays;
import java.util.Random;

public class Chenille {

	/** Array of co-ordinates the Caterpillar is occupying */
	private Case[] cases;
	/** The grid */
	private Espace espace;
	/** Size of caterpillar */
	private int taille;
	/**
	 * Boolean which keeps track of whether a caterpillar has reached a point
	 * where it can no longer advance - i.e. no neighbours are free in avance()
	 */
	private boolean peutAvancer;

	/**
	 * Most specific constructor which specifies the {Espace} we want the
	 * caterpillar to be a part of, the {Case} we want its head (index 0) to
	 * start at, and the max size {i} we want the caterpillar to be able to
	 * extend to.
	 * 
	 * @param {Espace}
	 *            e
	 * @param {Case}
	 *            c
	 * @param {int}
	 *            i
	 */
	public Chenille(Espace e, Case c, int i) {
		// if an invalid co-ordinate is given, throw an exception
		if (!e.contient(c)) {
			throw new IllegalArgumentException("That co-ordinate is not part of the grid.");
		} else if (i < 1) {
			throw new IllegalArgumentException("The minimum size of a caterpillar is 1.");
		} else {
			this.cases = new Case[i];
			this.cases[0] = c;
			this.taille = 1;
			this.espace = e;
			this.peutAvancer = true;
		}
	}

	/**
	 * Constructor which calls upon the above constructor, with no defined
	 * {Case}. Instead, this is replaced by a random non-occupied {Case} in the
	 * {Espace}
	 * 
	 * @param {Espace}
	 *            e
	 * @param {int}
	 *            i
	 */
	public Chenille(Espace e, int i) {
		this(e, e.caseLibreAuHasard(), i);
	}

	/**
	 * Constructor which calls upon the above constructor, with no defined
	 * {Case} or {int}. Instead, this is replaced by a random non-occupied
	 * {Case} in the {Espace}. The size is set to a default 10.
	 * 
	 * @param {Espace}
	 *            e
	 */
	public Chenille(Espace e) {
		this(e, 10);
	}

	/**
	 * Method which returns whether a Caterpillar is occupying the passed in
	 * {Case}
	 * 
	 * @param {Case}
	 *            c
	 * @return boolean
	 */
	public boolean estSur(Case c) {
		if(this.espace.contient(c)){
			for (int i = 0; i < this.taille; i++) {
				if (this.cases[i].equals(c)) {
					return true;
				}
			}
			return false;
		}
		else return false;
	}

	/**
	 * Method which returns whether a Case[] containing the cases the
	 * Caterpillar is occupying
	 * 
	 * @return {Case[]}
	 */
	public Case[] getOccupiedCases() {
		return Arrays.copyOf(this.cases, this.taille);
	}

	/**
	 * Method which advances the Caterpillar to an unoccupied neighbour {Case},
	 * if one exists.
	 */
	public void avance() {
		// if the caterpillar has not already reached max size
		if (this.cases.length != this.taille) {
			// get copy of the neighbour coordinates of the head of the
			// caterpillar
			Case[] voisines = this.cases[0].voisines();

			boolean caseTrouvee = false;
			Random r = new Random();
			int nullElements = 0;

			while (!caseTrouvee && this.peutAvancer == true) {
				int i = r.nextInt(4);
				if (voisines[i] != null) {
					if (this.espace.contient(voisines[i]) && !this.espace.caseOccupee(voisines[i])) {
						// if the neighbour is part of the Space and unoccupied
						// we shift each {Case} one to the right, to free up
						// case[0] for the unoccupied neighbour {Case}
						for (int j = taille - 1; j >= 0; j--) {
							this.cases[j + 1] = this.cases[j];
						}
						// set unoccupied neighbour to be our first index, i.e.
						// the head.
						this.cases[0] = voisines[i];
						// augment size
						this.taille++;
						// we have found a move
						caseTrouvee = true;
					}
					// in the case of a neighbour space either not being part of
					// the grid, or being occupied
					// we set the space in the neighbours array to null, so we
					// don't run the co-ordinate again
					// (which could well occur as random int could pick
					// voisines[1] 100 times in a row)
					// we increment our nullElements counter by 1
					else {
						voisines[i] = null;
						nullElements++;
					}
				}
				// if there 4 null elements, i.e. the caterpillar has no empty
				// neighbours
				else if (nullElements == 4) {
					// it can no longer advance
					this.peutAvancer = false;
				}
			}
		}
	}

	/**
	 * Representation of the caterpillar as a String.
	 * 
	 * @return {String}
	 */
	public String toString() {
		String s = "Chenille(" + this.taille + "/" + this.cases.length + "):[";
		for (int i = 0; i < this.taille; i++) {
			s += this.cases[i].toString() + ",";
		}
		return s.substring(0, s.length() - 1) + "]";
	}

	public Espace getEspace() {
		return this.espace;
	}

	public int getTaille() {
		return this.taille;
	}

	public Case[] getCases() {
		return this.cases;
	}

	public boolean peutAvancer() {
		return this.peutAvancer;
	}
}
