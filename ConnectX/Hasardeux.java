/**
 * @author Evans
 * @date 2018-12-18
 *
 */
import java.util.Random;

/**
 * Subclass of Joueur whose only method is to choose a random column to play
 */
public class Hasardeux extends Joueur {

	/**
	 * @return int random column in the grid
	 */
	public int choixColonne(JeuPuissanceZ grille) {
		Random r = new Random();
		int colonne = r.nextInt(grille.getGrille().length);
		// if column is not full
		if (grille.getNbPionsParColonne()[colonne] != grille.getGrille()[0].length) {
			return colonne;
		} else {
			return choixColonne(grille);
		}
	}

}
