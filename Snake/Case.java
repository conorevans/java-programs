/**
 * @author Conor Evans
 * @version 1.0
 * @date 2018-11-28
 * 
 *       Represents a case/box (i.e. a coordinate) in a grid.
 * 
 */
public class Case {
	/** position on x-axis */
	private int abscisse;
	/** position on y-axis */
	private int ordonnee;

	/**
	 * Sets x and y value of co-ordinate
	 * 
	 * @param {int}
	 *            a - x value
	 * @param {int}
	 *            b - y value
	 */
	public Case(int a, int b) {
		try {
			if (a >= 0 && b >= 0) {
				this.abscisse = a;
				this.ordonnee = b;
			}
			else throw new IllegalArgumentException("The x and y values must be greater than 0.");
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
	}

	/** @return x-axis value */
	public int getAbscisse() {
		return this.abscisse;
	}

	/** @return y-axis value */
	public int getOrdonnee() {
		return this.ordonnee;
	}

	/** @return Case to the east of current Case */
	public Case est() {
		return new Case(this.abscisse + 1, this.ordonnee);
	}

	/** @return Case to the north of current Case */
	public Case nord() {
		return new Case(this.abscisse, this.ordonnee + 1);
	}

	/** @return Case to the west of current Case */
	public Case ouest() {
		return new Case(this.abscisse - 1, this.ordonnee);
	}

	/** @return Case to the south of current Case */
	public Case sud() {
		return new Case(this.abscisse, this.ordonnee - 1);
	}

	/**
	 * @return Case[4] representing the neighbours of the current co-ordinate in
	 *         NEWS order
	 */
	public Case[] voisines() {
		return new Case[] { this.nord(), this.est(), this.ouest(), this.sud() };
	}

	/**
	 * @param {object}
	 *            o - object which we compare to current co-ordinate
	 * @return boolean representing equality between current co-ordinate and o
	 */
	public boolean equals(Object o) {
		if (o instanceof Case) {
			if (this.abscisse == ((Case) o).getAbscisse() && this.ordonnee == ((Case) o).getOrdonnee()) {
				return true;
			}
		}
		return false;
	}

	/** @return String representing the co-ordinate as a string */
	public String toString() {
		return new String("(" + this.abscisse + ", " + this.ordonnee + ")");
	}
}
