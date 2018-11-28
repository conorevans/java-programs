/**
 * @author Conor Evans
 * @version 1.0
 * @date 2018-11-28
 * 
 * Represents a case/box (i.e. a coordinate) in a grid Note that the French
 * manner of gridding, or at least that which was demanded of us, is as such: 
 * 0 1 2 
 * 1 
 * 2
 * 
 * I.e. it is as if the board faces us, rather than facing away from us. Thus,
 * north is 'below', south is 'above'.
 *
 * 
 */
public class Case {
	/** position on x-axis */
	private int abscisse;
	/** position on y-axis */
	private int ordonnee;

	/** Sets x and y value of co-ordinate */
	public Case(int a, int b) {
		this.abscisse = a;
		this.ordonnee = b;
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
		return new Case(this.abscisse - 1, this.ordonnee);
	}

	/** @return Case to the north of current Case */
	public Case nord() {
		return new Case(this.abscisse, this.ordonnee - 1);
	}

	/** @return Case to the west of current Case */
	public Case ouest() {
		return new Case(this.abscisse + 1, this.ordonnee);
	}

	/** @return Case to the south of current Case */
	public Case sud() {
		return new Case(this.abscisse, this.ordonnee + 1);
	}

	/** @return Case[4] representing the neighbours of the current co-ordinate in NEWS order  */
	public Case[] voisines() {
		return new Case[] {this.nord(), this.est(), this.ouest(), this.sud() };
	}

	/** 
	 * @param o : object which we compare to current co-ordinate
	 * @return boolean representing equality between current co-ordinate and o  
	 * */
	public boolean equals(Object o) {
		if (o instanceof Case) {
			if (this.abscisse == ((Case) o).getAbscisse() && this.ordonnee == ((Case) o).getOrdonnee()) {
				return true;
			}
		}
		return false;
	}

	/** @return String representing the co-ordinate as a string*/
	public String toString() {
		return new String("(" + this.abscisse + ", " + this.ordonnee + ")");
	}
}
