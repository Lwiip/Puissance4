package joueur;

import java.util.Random;

import error.OutOfGrid;
import grille.*;

public class Ia extends Joueur {

	public Ia() {
		this.id = 0;
		this.nom = "Not declared";
		this.human = false;
		this.score = 0;
	}

	public Ia(int id, String nom) {
		this.id = id; // gerer si jamais l'id est -1
		this.nom = nom;
		this.human = false;
		this.score = 0;
	}

	public static int dumbIa(int maxSize, Grille grille) {
		int nombre;
		do {
			Random rand = new Random();
			nombre = 1 + rand.nextInt(maxSize);// Entre 0 et le nombre de
												// colonne de la grille
		} while (grille.checkColumnFull(nombre - 1));

		return nombre;
	}

	public static int cleverIa(int maxSize, Grille grille, int idJoueur) {

		int idJoueurAdv;
		if (idJoueur == 1) {
			idJoueurAdv = 2;
		} else {
			idJoueurAdv = 1;
		}
		try {
			for (int i = 1; i <= maxSize; i++) {
				Grille grille2 = new Grille(grille);
				if (!grille2.checkColumnFull(i - 1)) {
					grille2.insertPion(i - 1, idJoueurAdv);

					if (grille2.detectWin(grille2.getTop(i - 1), i - 1)) {
						return i;
					} else {

						grille2.deletePion(i - 1);
						grille2.insertPion(i - 1, idJoueur);

						if (grille2.detectWin(grille2.getTop(i - 1), i - 1)) {
							return i;
						}
					}
				}
			}
		} catch (OutOfGrid o) {
		}
		return dumbIa(maxSize,grille);
	}
}
