package puissance4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import joueur.*;
import grille.*;
import error.*;

public class Puissance4 {

	private Joueur joueur1;
	private Joueur joueur2;
	private Grille grille;

	public Puissance4() {
		System.out.println("Debut du jeu ...");
		initPlayers();
		this.grille = new Grille(6, 7);
	}

	public void start() {
		String writed = new String();
		int column = 0;
		int idJoueur = 0;
		boolean j1turn = true;

		grille.affichage();

		for (;;) {// boucle infiniiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiie
			idJoueur = checkTurn(j1turn);

			System.out.println("Joueur " + idJoueur + " > ");

			writed = readConsole();
			checkQuit(writed);
			if (checkInt(writed)) { // penser a gere les depassement de colonne
				column = Integer.parseInt(writed);
				grille.insertPion(column - 1, idJoueur);
				grille.affichage();
				if (grille.detectWin(grille.getTop(column - 1), column - 1)) {
					System.out.println("Le joueur " + idJoueur + " a gagné !");
					System.exit(1);
				}
				j1turn = !(j1turn);
			}

		}
	}

	/*
	 * 
	 * 
	 * SIMULATION
	 */

	public void simulation() {
		String writed = new String();
		int column = 0;
		int idJoueur = 0;
		boolean j1turn = true;

		grille.affichage();

		/*
		 * entree utilisateur
		 */
		int nb_entree = 5;
		String simu[] = new String[nb_entree];
		simu[0] = "2";
		simu[1] = "5";
		simu[2] = "3";
		simu[3] = "2";
		simu[4] = "2";

		for (int i = 0;; i++) {// boucle
								// infiniiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiie
			idJoueur = checkTurn(j1turn);

			System.out.println("Joueur " + idJoueur + " > ");
			System.out.println(i);
			if (i >= nb_entree) {
				// mettre un breakpoint ici
				System.out.println("exit...");
				System.exit(0);
			}

			writed = simu[i];
			System.out.println(writed);

			if (checkInt(writed)) { // penser a gere les depassement de colonne
				column = Integer.parseInt(writed);
				try {
					grille.insertPion(column - 1, idJoueur);
					grille.affichage();
					if (grille.detectWin(grille.getTop(column - 1), column - 1)) {
						System.out.println("Le joueur " + idJoueur + " a gagné !");
						System.exit(1);
					}
					j1turn = !(j1turn);
					
				} catch(OutOfGrid o){
					System.err.println("Veuillez rentrer une valeur valide !");
					i--; //pour la simulation
				}
				
				
			}

		}
	}

	/*
	 * 
	 * 
	 * 
	 * FIN SIMULATION
	 */

	private String readConsole() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String read = new String();
		try {
			read = br.readLine();
		} catch (IOException e) {
			System.err.println("Erreur d'entrée/sortie");
		}
		return read;
	}

	private void initPlayers() {
		String writed = new String();
		int idJoueur = 1; // on commence par le premier joueur

		do {
			System.out.print("Joueur " + idJoueur + " ?\n > ");
			writed = readConsole();
			if (!writed.isEmpty()) { // on s'assure que l'utilisateur n'a pas
										// fait juste entrée
				String args[] = writed.split(" ", 2); // sépare le avant et
														// apres le 1ere espace

				if (args[0].equalsIgnoreCase("humain")) {
					if (idJoueur == 1) {
						this.joueur1 = new Human(idJoueur, args[1]);
					} else {
						this.joueur2 = new Human(idJoueur, args[1]);
					}
					idJoueur++;
				} else if (args[0].equalsIgnoreCase("ia")) {
					if (idJoueur == 1) {
						this.joueur1 = new Ia(idJoueur, args[1]);
					} else {
						this.joueur2 = new Ia(idJoueur, args[1]);
					}
					idJoueur++;
				} else {
					System.out
							.print("Le type de joueur doit etre 'humain' ou 'ia'\n > ");
				}

			} else {
				System.out.print("Veuillez entrer du text\n > ");
			}

		} while (idJoueur < 3); // on s'assure que l'utilisateur ne peux pas
								// rentrer d'autres valeurs que celles
								// attendues
	}

	private boolean checkInt(String writed) {
		try {
			Integer.parseInt(writed); // teste la conversion a un int
			return true;
		} catch (NumberFormatException nfe) {
			System.err.println("Vous etes en cours de partie, veuillez quittez avec 'Sortir' ou rentrer un numero de colonne\n");
			return false;
		}
	}

	private int checkTurn(boolean j1turn) {
		if (j1turn) {
			return this.joueur1.getId();
		} else {
			return this.joueur2.getId();
		}
	}

	private void checkQuit(String writed) {
		if (writed.equalsIgnoreCase("Sortir")) {
			java.lang.System.exit(1);
		}
	}

}
