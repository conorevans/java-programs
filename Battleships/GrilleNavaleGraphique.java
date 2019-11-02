package fr.uga.miashs.inff3.evansc;

import java.awt.Color;

/**
* Graphical representation of a battleship grid
*/
public class GrilleNavaleGraphique extends GrilleNavale{
	
	GrilleGraphique grilleGraphique;
	
	/* Initializer */
	public GrilleNavaleGraphique(int taille){
		super(taille);
		grilleGraphique = new GrilleGraphique(taille);
	}

  /* Getter */
	public GrilleGraphique getGrilleGraphique(){
		return grilleGraphique;
	}
	
	/* Add a ship */
	public boolean ajouterNavire(Navire n){
		if(super.ajouterNavire(n)){
			// colour squares green
			grilleGraphique.colorie(n.getDebut(), n.getFin(), Color.GREEN);
			return true;
		}
		return false;
	}
	
	public boolean recoitTir(Coordonnee c) {
		// if a hit is registered
		if(super.recoitTir(c)){
			// if the hit is water
			if(estALEau(c)){
				// colour blue
				grilleGraphique.colorie(c, Color.BLUE);
			} 
			// if the hit touches ship
			else if(estTouche(c)){
				// colour red
				grilleGraphique.colorie(c, Color.RED);
			}
			return true;
		}
		return false;
	}
}
