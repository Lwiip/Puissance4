package grille;

import error.OutOfGrid;
import error.ErrorCreateGrid;

// Classe Grille position x,y et Pion sur chaques cases
public class Grille {
	private int x;
	private int y;
	private Pion grille[][];

	//constructeur par défault
	public Grille() {
		this.x = 0;
		this.y = 0;
		this.grille = new Pion[x][y];
		initGrille();
	}

	//constructeur de la grille avec x:lignes et y:colonnes
	public Grille(int x, int y) {
		try {
			// Obligation pour la grille que ligne*colonne est pair et au moins 4 colonnes
			if (x * y % 2 != 0 || y < 4) {
				throw new ErrorCreateGrid(x, y);
			}
		} catch (ErrorCreateGrid e) {
			System.exit(0);
		}

		this.x = x;
		this.y = y;
		this.grille = new Pion[x][y];
		initGrille();

	}

	// pour la copie de grille (utile pour les ia)
	public Grille(Grille another) {
		this.x = another.x;
		this.y = another.y;
		this.grille = new Pion[this.x][this.y];
		copyGrille(another);
	}

	private void initGrille() { // Initialisation de la grille
		for (int i = 0; i < this.x; i++) {
			for (int j = 0; j < this.y; j++) {
				this.grille[i][j] = new Pion();
			}
		}
	}

	private void copyGrille(Grille another) { // copie de la grille
		for (int i = 0; i < this.x; i++) {
			for (int j = 0; j < this.y; j++) {
				this.grille[i][j] = new Pion(another.grille[i][j]);
			}
		}
	}

	//pour remettre la grille à zéro, par exemple à la fin d'une partie
	public void wipe() {
		for (int i = 0; i < this.x; i++) {
			for (int j = 0; j < this.y; j++) {
				this.grille[i][j].setIdJoueur(0); //remet à zéro la case
				;
			}
		}
	}

	public void affichage() {
		// Affiche les numeros de colonnes
		for (int i = 1; i <= this.y; i++) {
			System.out.print(" " + i + " ");
		}
		System.out.print("\n");
		// Affiche la grille
		for (int i = 0; i < this.x; i++) {
			for (int j = 0; j < this.y; j++) {
				System.out.print(this.grille[i][j].affichePion());
			}
			System.out.print("\n");
		}
	}

	// insertion d'un pion, gere le cas ou on sort de la grille, et plus rarement pas bon id
	public void insertPion(int y, int idJoueur) throws OutOfGrid { 
		int i = 0;
		try {

			for (i = 0; i < this.x; i++) {
				if (this.grille[i][y].getIdJoueur() != 0) { // si on on trouve un autre pion
					this.grille[i - 1][y].setIdJoueur(idJoueur); // on rajoute au dessus du pion existant
					return;
				} else if (i == (this.x - 1)) { // si on est en bout de ligne
					this.grille[i][y].setIdJoueur(idJoueur); 
					return;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) { // gestion des erreurs
			if (y < 0 || y > this.y - 1) { //mauvaise colonne
				throw new OutOfGrid(y);
			} else if (getTop(y) <= 0) {
				throw new OutOfGrid(y, 1); // gere la colonne pleine
			}
		}
	}

	// supression du point
	public void deletePion(int y) throws OutOfGrid { 
		try {
			for (int i = 0; i < this.x; i++) {
				if (this.grille[i][y].getIdJoueur() != 0) { // si il y a bien un pion
					this.grille[i][y].setIdJoueur(0); // on le met la case zéro
					return;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) { //gère le cas en dehors de la grille
			throw new OutOfGrid();
		}
	}
	
	// pour obtenir l'id de joueur sur la case correspondante
	public int getIdJoueur(int x, int y){
		if (x > this.x || y > this.y){ //gestion des ereurs
			new OutOfGrid();
		}
		return this.grille[x][y].getIdJoueur();
	}

	//pour savoir si la grille est pleine (pour les égalités)
	public boolean checkGridFull() {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (this.grille[i][j].getIdJoueur() == 0) { // si on a une case vide
					return false;
				}
			}
		}
		return true;
	}

	//pour savoir si la colone est pleine
	public boolean checkColumnFull(int y) {
		for (int i = 0; i < x; i++) {
			if (this.grille[i][y].getIdJoueur() == 0) { // si on a une case vide
				return false;
			}
		}
		return true;
	}

	//-----------------------------------------------------------------------------------------------------------------------
	//Détection de la victoire pour les colones
	private int detectWinCol(int x, int y) { // colone
		int compteur = 1;
		int id = this.grille[x][y].getIdJoueur();

		if (id != 0) {
			for (int i = x + 1; i < this.x; i++) {// parcourt par colonne vers le haut
				if (this.grille[i][y].getIdJoueur() != id) {
					break;
				} else if (this.grille[i][y].getIdJoueur() == id) {
					compteur++;
					if (compteur == 4) { // Si on a gagné
						return 1;
					}
				}
			}
			for (int i = x - 1; i >= 0; i--) {// parcourt par colonne vers le bas
				if (this.grille[i][y].getIdJoueur() != id) {
					break;
				} else if (this.grille[i][y].getIdJoueur() == id) {
					compteur++;
					if (compteur == 4) { // Si on a gagné
						return 1;
					}
				}
			}

		}
		return 0;
	}

	//Détection de la victoire pour les lignes
	private int detectWinLine(int x, int y) {
		int compteur = 1;
		int id = this.grille[x][y].getIdJoueur();

		if (id != 0) {
			for (int i = y + 1; i < this.y; i++) {// parcourt par ligne vers la droite
				if (this.grille[x][i].getIdJoueur() != id) {
					break;
				} else if (this.grille[x][i].getIdJoueur() == id) {
					compteur++;
					if (compteur == 4) {
						return 1;
					}
				}
			}
			for (int i = y - 1; i >= 0; i--) {// parcourt ligne vers la gauche
				if (this.grille[x][i].getIdJoueur() != id) {
					break;
				} else if (this.grille[x][i].getIdJoueur() == id) {
					compteur++;
					if (compteur == 4) {
						return 1;
					}
				}
			}

		}
		return 0;
	}

	//Détection de la victoire pour la diagonal gauche
	private int detectWinDiagLeft(int x, int y) {
		int compteur = 1;
		int id = this.grille[x][y].getIdJoueur();
		int i = x - 1;
		int j = y - 1;
		if (id != 0) {
			while (i >= 0 && j >= 0) { // parcourt diagonal vers en haut à gauche
				if (this.grille[i][j].getIdJoueur() != id) {
					break;
				} else if (this.grille[i][j].getIdJoueur() == id) {
					compteur++;
					if (compteur == 4) {
						return 1;
					}
				}
				i--;
				j--;
			}
			i = x + 1;
			j = y + 1;
			while (i < this.x && j < this.y) { // parcourt diagonal vers en bas à droite
				if (this.grille[i][j].getIdJoueur() != id) {
					break;
				} else if (this.grille[i][j].getIdJoueur() == id) {
					compteur++;
					if (compteur == 4) {
						return 1;
					}
				}
				i++;
				j++;
			}
		}
		return 0;
	}

	//Détection de la victoire pour la diagonal droite
	private int detectWinDiagRight(int x, int y) {// Diagonal droite
		int compteur = 1;
		int id = this.grille[x][y].getIdJoueur();
		int i = x - 1;
		int j = y + 1;
		if (id != 0) {
			while (i >= 0 && j < this.y) { // parcourt diagonal vers en haut à droite
				if (this.grille[i][j].getIdJoueur() != id) {
					break;
				} else if (this.grille[i][j].getIdJoueur() == id) {
					compteur++;
					if (compteur == 4) {
						return 1;
					}
				}
				i--;
				j++;
			}
			i = x + 1;
			j = y - 1;
			while (i < this.x && j >= 0) { // parcourt diagonal vers en bas à gauche
				if (this.grille[i][j].getIdJoueur() != id) {
					break;
				} else if (this.grille[i][j].getIdJoueur() == id) {
					compteur++;
					if (compteur == 4) {
						return 1;
					}
				}
				i++;
				j--;
			}
		}
		return 0;
	}

	//Agrégation des résultats pour détection de la victoire pour ligne, colone, diagonal gauche, diagonal droite
	public boolean detectWin(int x, int y) {
		int retour1;
		int retour2;
		int retour3;
		int retour4;

		retour1 = detectWinCol(x, y);
		retour2 = detectWinLine(x, y);
		retour3 = detectWinDiagLeft(x, y);
		retour4 = detectWinDiagRight(x, y);

		if (retour1 == 1 || retour2 == 1 || retour3 == 1 || retour4 == 1) {
			return true;
		}
		return false;
	}

	// -------------------------------------------------------------------------------------------------------------------------------

	//pour otenir l'indice du plus haut poin dans la colonne y
	public int getTop(int y) {
		int i = 0;
		for (i = 0; i < this.x; i++) {
			if (grille[i][y].getIdJoueur() != 0) {
				return i;
			}
		}
		return i - 1;
	}

	/*
	 * Getter et Setter
	 */
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
