package fr.uga.miashs.inff3.evansc;

/**
 * This class represents a co-ordinate in a battleship grid
 *
 * @author Conor Evans et Bronagh Carolan
 * @version 1.0
 * @since 2018-10-19
 */
public class Coordonnee {

  /* row and column */
	private int ligne;
	private int colonne;

	/**
	 * Coordinate initializer
	 * 
	 * @param String x: string representation of a co-ordinate
	 * @return Coordonnee - coordinate
	 */
	public Coordonnee(String x) {
		colonne = x.charAt(0) - 'A';
		ligne = Integer.parseInt(x.substring(1)) - 1;
	}

	/**
	 * Coordinate initializer
	 * 
	 * @param int ligne: row
	 * @param int colonne: column
	 */
	public Coordonnee(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}

	/**
	 * Row getter
	 * 
	 * @return int ligne
	 */
	public int getLigne() {
		return ligne;
	}

	/**
	 * Column getter 
	 *
	 * @return int colonne
	 */
	public int getColonne() {
		return colonne;
	}

	/**
	 * Returns string representation of coordinate
	 * 
	 * @return String
	 */
	public String toString() {
		return "" + (char) (colonne + 'A') + (ligne + 1);
	}

	/**
	 * Returns whether the coordinate on which the method is called equals
	 * the coordinate passed as a parameter
	 * 
	 * @param Coordonnee o
	 * @return boolean
	 */
	public boolean equals(Coordonnee c) {
		return (ligne == c.getLigne() && colonne == c.getColonne());
	}

	/**
	 * Returns whether the coordinate on which the method is called is a neighbour
	 * of the coordinate passed as a parameter
	 * 
	 * @param Coordonnee o
	 * @return boolean
	 */
	public boolean voisine(Coordonnee c) {
		return (ligne == c.getLigne() && (colonne == c.getColonne() + 1) || colonne == c.getColonne() - 1)
				|| (colonne == c.getColonne() && ((ligne == c.getLigne() + 1) || ligne == c.getLigne() - 1));
	}

	/**
	 * Returns whether the coordinate on which the method is called is greater than
	 * the coordinate passed as a parameter
	 * @param Coordonnee c
	 * @return int : less < 0, equal = 0, greater > 0
	 */
	public int compareTo(Coordonnee o) {
		int compareColonne = colonne - o.colonne;
		if (compareColonne == 0) {
			return ligne - o.ligne;
		} else
			return compareColonne;
	}

}