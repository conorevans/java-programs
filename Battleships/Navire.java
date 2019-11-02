package fr.uga.miashs.inff3.carolanb;

/**
 * Class represents a ship in a battleship grid
 *
 * @author Conor Evans and Bronagh Carolan
 * @version 1.0
 * @since 2018-10-19
 */

public class Navire {
	/* 'Start' coordinate of the ship */
	private Coordonnee debut;
	/* 'End' coordinate of the ship */
	private Coordonnee fin;
	/* Array of coordinates representing the coordinates of the ship at which it has received a hit */
	private Coordonnee[] partiesTouchees;
	/* Integer representing the number of hits a ship has received */
	private int nbTouchees;

	/**
	 * Initializer
	 * 
	 * @param Coordonnee debut
	 * @param int longueur - length of ship
	 * @param estVertical - true if ship is to be placed vertically
	 */

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		this.debut = debut;
		partiesTouchees = new Coordonnee[longueur];
		nbTouchees = 0;
		if (estVertical) {
			fin = new Coordonnee((debut.getLigne() + longueur) - 1, debut.getColonne());
		} else {
			fin = new Coordonnee(debut.getLigne(), debut.getColonne() + longueur - 1);
		}
	}

	/**
	 * Returns a string reprenstation of the ship
	 * 
	 * @return String
	 */

	public String toString() {
		if(this==null)
			return "null";
		return "Debut: " + debut.toString() + "\nFin: " + fin.toString()
	}

	/**
	 * Start of ship getter
	 * 
	 * @return Coordinate
	 */

	public Coordonnee getDebut() {
		return debut;
	}

	/**
	 * End of ship getter
	 * 
	 * @return Coordonnee
	 */

	public Coordonnee getFin() {
		return fin;
	}

	/**
	 * Returns whether the called-upon ship contains the coordinate passed as a paramter
	 * 
	 * @param Coordonnee c
	 * @return boolean - true if ship contains given coordinate
	 */
	public boolean contient(Coordonnee c) {
		// if a ship is vertical, its start and end coordinate will be equal
		// check if the given coordinate is in the same column
		if (debut.getColonne() == fin.getColonne() && c.getColonne() == debut.getColonne()) {
			// if the coordinate is on a lower row than start or higher row than end
			// it is not contained
			if (c.getLigne() < debut.getLigne() || c.getLigne() > fin.getLigne())
				return false;
			return true;
		} 
		// repeat for horizontal
		else if (debut.getLigne() == fin.getLigne() && o.getLigne() == debut.getLigne()) {
			if (o.getColonne() < debut.getColonne() || o.getColonne() > fin.getColonne())
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Check if there is a non-void intersection between two given ships (via their start/end coordinates)
	 * 
	 * @param int d1 - start of ship one
	 * @param int f1 - one of ship one
	 * @param int d2 - start of ship two
	 * @param int f2 - end of ship two
	 * 
	 * @return boolean - true if none void intersection
	 */
	private static boolean intersectionNonVide(int d1, int f1, int d2, int f2) {
		if (d1 == d2 || f1 == f2 || f1 == d2 || f2 == d1)
			return true;
		else if (d1 < d2) {
			if (f2 < f1 || (f1 > d2 && f1 < f2))
				return true;
			return false;
		} else {
			if (f2 >= f1 || f2 < f1)
				return true;
			return false;
		}
	}

	/**
	 * Cette m�thode retourne si le navire sur lequel on appelle la m�thode
	 * chevauche le navire pass� en param�tres.
	 * 
	 * @param Navire
	 * @return boolean
	 */

	public boolean chevauche(Navire n) {
		return intersectionNonVide(debut.getLigne(), fin.getLigne(), n.debut.getLigne(), n.fin.getLigne())
				&& intersectionNonVide(debut.getColonne(), fin.getColonne(), n.debut.getColonne(), n.fin.getColonne());
	}

	/**
	 * Returns whether the called-upon ship touches the ship passed as a parameter
	 * 
	 * @param Navire n
	 * @return boolean - true if it touches
	 */
	public boolean touche(Navire n) {
		if (intersectionNonVide(debut.getLigne(), fin.getLigne(), n.debut.getLigne(), n.fin.getLigne())) {
			if (n.debut.getColonne() == fin.getColonne() + 1 || n.fin.getColonne() == debut.getColonne() - 1)
				return true;
			return false;
		} else if (intersectionNonVide(debut.getColonne(), fin.getColonne(), n.debut.getColonne(),
				n.fin.getColonne())) {
			if (n.debut.getLigne() == fin.getLigne() + 1 || n.fin.getLigne() == debut.getLigne() - 1)
				return true;
			return false;
		} else
			return false;
	}

	/**
	 * Returns whether the called-upon ship is hit at the coordinate passed as a parameter
	 * 
	 * @param Coordonne c
	 * @return boolean - true if ship is hit
	 */
	public boolean estTouche(Coordonnee c) {
		for (int i = 0; i < partiesTouchees.length; i++) {
			if (partiesTouchees[i] != null && partiesTouchees[i].equals(c))
				return true;
		}
		return false;
	}

	/**
	 * Method registers a hit at a given coordinate
	 * 
	 * @param Coordonnee c
	 * @return boolean - true if hit registered
	 */
	public boolean recoitTir(Coordonnee c) {
		if (contient(c) && !estTouche(c)) {
			for(int i = 0; i < partiesTouchees.length; i++){
				if(partiesTouchees[i] == null){
					partiesTouchees[i] = c;
					break;
				}
			}
			nbTouchees++;
			return true;
		}
		return false;
	}

	/**
	 * Returns whether the called-upon ship is sunk
	 * 
	 * @return boolean - true if sunk
	 */
	public boolean estCoule() {
		for (int i = 0; i < partiesTouchees.length; i++) {
			if (partiesTouchees[i] == null)
				return false;
		}
		return true;
	}
}
