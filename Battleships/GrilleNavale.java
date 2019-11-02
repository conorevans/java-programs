package fr.uga.miashs.inff3.evansc;


/**
 * This class represents a grid for a Battleship game
 *
 * @author Conor Evans and Bronagh Carolan
 * @version 1.0
 * @since 2018-11-16
 */

import java.util.Arrays;
import java.util.Random;

public class GrilleNavale {
	/* Array of Ships */
	private Navire[] navires;
	/* Ship Count */
	private int nbNavires;
	/* Grid size (length = width) */
	private int tailleGrille;
	/* Area of hits taken */
	private Coordonnee[] tirsRecus;
	/* Hits taken count */
	private int nbTirsRecus;

	/**
	 * Initializes a grid of a given size
	 * 
	 * @param taille - size
	 */
	public GrilleNavale(int taille) {
		navires = new Navire[0];
		tirsRecus = new Coordonnee[0];
		tailleGrille = taille;
		nbNavires = 0;
		nbTirsRecus = 0;
	}

	/**
	 * Adds a ship to the grid. The ship is not added if its dimensions imply
	 * it would leave the grid or if it touches/straddles another a ship already
	 * present in the grid. The array of ships is automatically increated by length + 5
	 * if needed.
	 * 
	 * @param Navire n
	 * @return boolean - true if ship added
	 */
	public boolean ajouterNavire(Navire n) {
		
		// check if ship is out of bounds
		if (n.getFin().getColonne() >= tailleGrille || n.getFin().getLigne() >= tailleGrille) {
			return false;
		}

		// check if ship would hit or straddle an existing ship
		for (int i = 0; i < nbNavires; i++) {
			if (navires[i].touche(n) || navires[i].chevauche(n)) {
				return false;
			}
		}

		// if array of shops is full, increase size by 5 
		if (navires.length == nbNavires) {
			navires = Arrays.copyOf(navires, navires.length + 5);
		}

		// add ship and increment ships count
		navires[nbNavires] = n;
		nbNavires++;
		return true;
	}

	/**
	 * Random placement of x ships
	 * 
	 * @param taillesNavires - Array of Ships to place on the grid
	 */
	public void placementAuto(int[] taillesNavires) {
		Random r = new Random();
		for (int i = 0; i < taillesNavires.length; i++) {

			// decide whether the ship will be placed vertically or horizontally.
			boolean estVertical = false;
			if (r.nextInt(2) == 1) {
				estVertical = true;
			}

			// get random coordinate
			int r1 = r.nextInt(tailleGrille);
			int r2 = r.nextInt(tailleGrille);
			Coordonnee c = new Coordonnee(r1, r2);

      // if adding in one dimension fails
			if (ajouterNavire(new Navire(c, taillesNavires[i], estVertical)) == false) {
				// if adding in the other dimension fails
				if(ajouterNavire(new Navire(c, taillesNavires[i], !estVertical)) == false) {
					// try inverting the coordinate e.g. (y,x) instead of (x,y)
					if(ajouterNavire(new Navire(new Coordonnee(r2, r1), taillesNavires[i], !estVertical)) == false) {
						if(ajouterNavire(new Navire(new Coordonnee(r2, r1), taillesNavires[i], estVertical)) == false) {
							// if neither dimension at either coordinate produces a possible addition
							// decrement as we will need to add the ship in some manner
							i--;
						}
					}		
				}
			}
		}
	}

	/**
	 * Check whether the given coordinate is part of the grid
	 * 
	 * @param Coordonnee c - non null
	 * @return boolean - true if the coordinate is part of the grid
	 */
	private boolean estDansGrille(Coordonnee c) {
		if (c.getLigne() > tailleGrille-1 || c.getLigne() > tailleGrille-1
				|| c.getColonne() > tailleGrille-1 || c.getColonne() > tailleGrille-1) {
			return false;
		}
		return true;
	}

	/**
	 * Check whether the given coordinate has received a hit
	 * 
	 * @param Coordinate c - non null
	 * @return boolean - true if the coordinate has been hit
	 */
	private boolean estDansTirsRecus(Coordonnee c) {
		for (int i =0; i < nbTirsRecus; i++) {
			if (c.equals(tirsRecus[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Add a hit to our array of hits. In order for a hit on the given 
	 * coordinate to be registered, the coordinate must be on the grid and not
	 * have already registered a hit.
	 * 
	 * @param Coordinate c
	 * @return boolean - true if shot is successfully registered
	 */
	private boolean ajouterDansTirsRecus(Coordonnee c) {
		if (!estDansGrille(c) || estDansTirsRecus(c)){
			return false;
		}

		// extend our array of hits registered if at max length
		if (tirsRecus.length == nbTirsRecus) {
			tirsRecus = Arrays.copyOf(tirsRecus, tirsRecus.length + 5);
		}

		// cycle through array and add coordinate when possible 
		for (int i = nbTirsRecus; i < tirsRecus.length; i++) {
			if (tirsRecus[i] == null) {
				tirsRecus[i] = c;
				nbTirsRecus++;
				return true;
			}
		}
		return false;
	}

	/**
	 * Manages the registration of a shot on the grid. If the shot is added to registered
	 * shots, the ships of the grid are then tested to see whether they have been hit.
	 * 
	 * @param Coordinate c
	 * @return boolean - true if the shot has hit a ship of the grid
	 */
	public boolean recoitTir(Coordonnee c) {
		boolean tirToucheNavire = false;
		if(ajouterDansTirsRecus(c) == true) {
			for(int i = 0; i < nbNavires; i++) {
				if(navires[i].contient(c)) {
					navires[i].recoitTir(c);
					tirToucheNavire = true;
				}
			}
		}
		return tirToucheNavire;
	}

	/**
	 * Tests if any ship has been hit at the coordinate passed as a parameter
	 * 
	 * @param Coordinate c
	 * @return boolean - true if a ship has been hit at the coordinate
	 */
	public boolean estTouche(Coordonnee c) {
		for(int i = 0; i < nbNavires; i++) {
			if(navires[i].estTouche(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if a coordinate is 'at water' i.e. has had a hit registered but the hit didn't touch
	 * a ship
	 * 
	 * @param Coordinate c 
	 * @return true - if coordinate is at water
	 */
	public boolean estALEau(Coordonnee c) {
		if(estDansTirsRecus(c) && !estTouche(c)) {
			return true;
		}
		return false;
	}

	/**
	 * See if all the ships in the grid are sunk
	 * 
	 * @return true if all sunk
	 */
	public boolean perdu() {
		for(int i = 0; i < nbNavires; i++) {
			if(!navires[i].estCoule()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Get size of grid
	 * 
	 * @return int - size of the grid
	 */
	public int getTaille() {
		return tailleGrille;
	}

	/**
	 * See if a ship on the given coordinate is sunk
	 * 
	 * @param Coordinate c
	 * @return true - if a ship on the given coordinate is sunk
	 */
	public boolean estCoule(Coordonnee c) {
		for(int i =0; i < nbNavires; i++) {
			if(navires[i].contient(c) && navires[i].estCoule()) {
				return true;
			}
		}
		return false;
	}
}