package joueur;

import java.util.Random;

import error.OutOfGrid;
import grille.*;

//class ia avec un id, un nom, un booléen pour dire qu'on est pas humain, un score et une string pour différencier les 2 types d'ia
public class Ia extends Joueur {
	private String intel;

	//constructeur par défault
	public Ia() {
		this.id = 0;
		this.nom = "Not declared";
		this.human = false;
		this.intel = "not declared";
		this.score = 0;
	}

	public Ia(int id, String nom, String ia) {
		this.id = id;
		this.nom = nom;
		this.human = false;
		this.score = 0;
		this.intel = ia;
	}

	public int play(int maxSize, Grille grille, int idJoueur) {
		if (this.intel == "random") { // si ia random => dumbIa
			return dumbIa(maxSize, grille);
		} else { //Sinon ia intelligente => cleverIa
			return cleverIa(maxSize, grille, idJoueur);
		}
	}

	// ia random
	private static int dumbIa(int maxSize, Grille grille) {
		int nombre;
		do {
			Random rand = new Random(); 
			nombre = 1 + rand.nextInt(maxSize);// nombre aléatoire entre 0 et le nombre de colonne de la grille
		} while (grille.checkColumnFull(nombre - 1));
		return nombre;
	}

	// ia intelligente, si elle détecte que l'adversaire va gagner et le bloc, si elle détecte qu'elle va gagner elle gagne, sinon random
	private static int cleverIa(int maxSize, Grille grille, int idJoueur) {
		int idJoueurAdv;
		if (idJoueur == 1) {
			idJoueurAdv = 2;
		} else {
			idJoueurAdv = 1;
		}
		try {
			for (int i = 1; i <= maxSize; i++) { // parcours des colonnes
				Grille grille2 = new Grille(grille); // Copy de la grille
				if (!grille2.checkColumnFull(i - 1)) { // Si la colonne n'est pas pleine
					grille2.insertPion(i - 1, idJoueurAdv); // insertion du pion adverse dans la colonne

					if (grille2.detectWin(grille2.getTop(i - 1), i - 1)) { // Si on détecte que l'adversaire va gagner
						return i; // on le bloque et on retourne le numéro de la colonne
					} else {
						grille2.deletePion(i - 1); 
						grille2.insertPion(i - 1, idJoueur); // ajout du pion ia dans la colonne

						if (grille2.detectWin(grille2.getTop(i - 1), i - 1)) { //détection de la victoire pour l'ia
							return i; // si elle va gagner on retourne la colonne qui va faire gagner l'ia
						}
					}
				}
			}
		} catch (OutOfGrid o) { 
		}
		return dumbIa(maxSize, grille); // Si on va pas perdre et on peut pas gagner, on renvoie random
	}
}
