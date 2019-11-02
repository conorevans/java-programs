package fr.uga.miashs.inff3.evansc;

// imports
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// represents an instance of a Battleship
public class BatailleNavale {
	
	/** initialize Battleship game window
	  final string titreFenetre - title for the window
		final GrilleGraphique grilleTir - grid of shots
		final GrilleGraphie grilleJeu - grid representing the game
	*/

	public static void initFenetre(final String titreFenetre, final GrilleGraphique grilleTir, final GrilleGraphique grilleJeu) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	JFrame fenetre = new JFrame(titreFenetre);
        		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		fenetre.getContentPane().setLayout(new GridLayout(1, 2));
        		grilleTir.setBorder(BorderFactory.createTitledBorder("Grille de tirs"));
        		grilleJeu.setBorder(BorderFactory.createTitledBorder("Grille de jeu"));
        		grilleJeu.setClicActive(false);
        		fenetre.getContentPane().add(grilleTir);
        		fenetre.getContentPane().add(grilleJeu);
        		
        		fenetre.pack();
        		fenetre.setVisible(true);
            }
        });
	}
	
	public static void main(String[] args) {
		JoueurGraphique j1 = initJoueur("Bronagh");
		JoueurGraphique j2 = initJoueur("Conor");
		j1.jouerAvec(j2);
	}

  /** InitializePlayer method
	   String nomJoueur - name of player
		 @return JoueurGraphique - Grid Player
	*/
	public static JoueurGraphique initJoueur(String nomJoueur) {
		GrilleGraphique grilleAttaque = new	 GrilleGraphique(10);
		GrilleNavaleGraphique grilleJoueur = new GrilleNavaleGraphique(10);
		grilleJoueur.placementAuto(new int[]{3,2,5,3,2});
		initFenetre(nomJoueur, grilleAttaque,grilleJoueur.getGrilleGraphique());
		return new JoueurGraphique(grilleJoueur,grilleAttaque);
	}

}
