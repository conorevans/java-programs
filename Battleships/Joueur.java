package fr.uga.miashs.inff3.evansc;


/**
 * Abstract class representing a player in battleship
 * @author jerome.david@univ-grenoble-alpes.fr
 *
 */
public abstract class Joueur {
	
	public final static int TOUCHE=1;
	public final static int COULE=2;
	public final static int A_L_EAU=3;
	public final static int GAMEOVER = 4;
	
	private String prenom;
	
	/**
	 * L'adversaire du joueur courant. Cet attribut ne sera pas initialis� dans le constructeur 
	 * mais dans la m�thode <code>jouerAvec(Joueur a)</code>
	 */
	private Joueur adversaire;
	
	/**
	 * La taille de la grille du joueur
	 */
	private int tailleGrille;
	
	/**
	 * Initialise un nouveau joueur
	 * @param t la taille de la grille
	 * @param n le prenom du joueur
	 */
	public Joueur(int t, String n) {
		this.prenom=n;
		tailleGrille=t;
	}
	
	/**
	 * Initialise un joueur sans nom
	 * @param g la taille de la grille
	 */
	public Joueur(int t) {
		this(t,"Inconnu");
	}
	
	/**
	 * retourne la taille de grille du joueur
	 * @return la taille de la grille du joueur
	 */
	public int getTailleGrille() {
		return tailleGrille;
	}
	
	/**
	 * retourne le pr�nom du joueur
	 * @return le prenom du joueur
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * Cette m�thode intialise l'attribut adversaire de l'instance courante avec le Joueur pass� en param�tre.
	 * Elle fait de m�me pour l'adversaire : l'adversaire de a va �tre le joueur courant.
	 * Finalement elle appelle les m�thode <code>initPartie(int tailleGrilleAdversaire);</code> pour le joueur courant et pour l'adversaire
	 * @param a l'aderversaire contre qui le joueur va jouer
	 */
	public void jouerAvec(Joueur a) {
		
        if (this.adversaire != null) {
            throw new RuntimeException("You are already playing with " + adversaire.getPrenom());
        } else if (a.adversaire != null) {
            throw new RuntimeException("You're opponent is already playing");
        } else if (a.getTailleGrille() != this.getTailleGrille()) {
            throw new RuntimeException("You do not have the same grid size...");
        }
        this.adversaire = a;
        a.adversaire = this;

        Joueur p1 = this;
        Joueur p2 = adversaire;
        int res = 0;
        while (res != GAMEOVER) {
            Coordonnee c = p1.choisirAttaque();
            res = p2.defendre(c);
            p1.retourAttaque(c, res);
            p2.retourDefense(c, res);
            Joueur x = p1;
            p1 = p2;
            p2 = x;
        }
	}
	
	/**
	 * M�thode appel�e � chaque retour d'attaque port�e par ce joueur sur son adversaire
	 * @param c La coordonn�e attaqu�e sur la grille de l'adversaire
	 * @param etat Etat de l'attaque port�e qui peut �tre �gal au constantes <code>Joueur.TOUCHE</code>, <code>Joueur.COULE</code>, ou <code>Joueur.A_L_EAU</code>
	 */
	protected abstract void retourAttaque(Coordonnee c, int etat);
	
	/**
	 * M�thode appel�e � chaque retour d'attaque port� par l'adversaire sur ce Joueur
	 * @param c La coordonn�e attaqu�e par l'adversaire
	 * @param etat Etat de l'attaque re�ue qui peut �tre �gal au constantes <code>Joueur.TOUCHE</code>, <code>Joueur.COULE</code>, ou <code>Joueur.A_L_EAU</code>
	 */
	protected abstract void retourDefense(Coordonnee c, int etat);
	
	/**
	 * M�thode appel�e lorsque c'est au tour de ce joueur de jouer. 
	 * Elle doit retourner la coordonn�e choisie pour l'attaque
	 * @return la coordonnee � attaquer sur la grille de l'adversaire
	 */
	public abstract Coordonnee choisirAttaque();
	
	/**
	 * M�thode de d�fense. Cette m�thode est appel�e lorsqu'une attaque de l'adversaire est re�ue.
	 * En focntion de l'impact de l'attaque, elle retourne un entier qui peut prendre les valeurs suivantes :
	 * Joueur.TOUCHE, Joueur.COULE, Joueur.A_L_EAU, Joueur.GAME_OVER
	 * @param c la cordonn�e attaqu�e
	 * @return le resultat de cette attaque: Joueur.TOUCHE, Joueur.COULE, Joueur.A_L_EAU ou Joueur.GAME_OVER
	 */
	public abstract int defendre(Coordonnee c);
}
