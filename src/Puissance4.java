import grille.*;
import joueur.*;

public class Puissance4 {

	public static void main(String[] args) {
		Grille grille = new Grille(6,7);
		grille.insertPion(0, 1);
		grille.insertPion(1, 2);
		grille.insertPion(1, 1);
		grille.insertPion(2, 2);
		grille.insertPion(2, 2);
		grille.insertPion(2, 1);
		grille.insertPion(3, 2);
		grille.insertPion(3, 2);
		grille.insertPion(3, 2);
		grille.insertPion(3, 1);
	
	
		
		grille.affichage();
		
		System.out.println(grille.detectWin(2,3));
	}

}
