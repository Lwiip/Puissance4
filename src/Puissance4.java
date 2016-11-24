import grille.*;
import joueur.*;

public class Puissance4 {

	public static void main(String[] args) {
		Grille grille = new Grille(6,7);
		grille.affichage();
		grille.insertPion(1, 2);
		grille.affichage();
		grille.insertPion(5, 1);
		grille.affichage();
		grille.insertPion(5, 2);
		grille.affichage();
	}

}
