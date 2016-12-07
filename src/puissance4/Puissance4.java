package puissance4;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import joueur.*;
import log.*;
import grille.*;
import error.*;

public class Puissance4 {

	private Joueur joueur1;
	private Joueur joueur2;
	private Grille grille;
	private int nbPartie;
	private int idJoueur;

	// private Log log;

	public Puissance4() {
		Log.clearLog();
		System.out.println("Debut du jeu ...");
		initPlayers();
		saveName();
		this.grille = new Grille(6, 7);
		this.nbPartie = 0;
	}

	public void start() {
		String writed = new String();
		int token = 0; // pour gérer l'affichage de "Manche commence" dans le
						// log
		int column = 1;
		boolean joueur_have_played = false;

		boolean j1turn = true;

		grille.affichage();

		for (;;) {// boucle infiniiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiie
			idJoueur = checkTurn(j1turn);
			joueur_have_played = false;

			System.out.println("Joueur " + idJoueur + " > ");
			try {
				// si le joueur est un humain
				if (this.joueur1.getId() == idJoueur && this.joueur1.isHuman()
						|| this.joueur2.getId() == idJoueur
						&& this.joueur2.isHuman()) {
					writed = readConsole();
					checkQuit(writed);
					if (checkInt(writed)) { 
						column = Integer.parseInt(writed);
						grille.insertPion(column - 1, idJoueur);
						saveGame(idJoueur, column, token);
						token++;
						joueur_have_played = true;
					}
					else{
						new ErrorInput(writed);
					}

				} else { // si le joueur est un IA
					Ia tmpIa = (Ia) getJoueur(idJoueur);
					column = tmpIa.play(this.grille.getY(), grille, idJoueur);

					grille.insertPion(column - 1, idJoueur);
					saveGame(idJoueur, column, token);
					token++;
					joueur_have_played = true;

				}

				if (joueur_have_played) {
					j1turn = !(j1turn);
					grille.affichage();

					if (grille.detectWin(grille.getTop(column - 1), column - 1)) {
						System.out.println("Le joueur " + idJoueur
								+ " a gagné !");
						winJoueur(idJoueur);
						saveResult(idJoueur);
						j1turn=false;
						token = 0;
						wipe();
					}
				}

			} catch (OutOfGrid o) {
				if (grille.checkGridFull()) {
					System.out.println("Match nul !");
					saveResult(this.joueur1.getId(), this.joueur2.getId()); // affiche
																			// égalitée
																			// dans
																			// le
																			// log
					token = 0;
					System.exit(1);
				}
			}

			if (grille.checkGridFull()) {
				System.out.println("Match nul !");
				saveResult(this.joueur1.getId(), this.joueur2.getId()); // affiche
																		// égalitée
																		// dans
																		// le
																		// log
				token = 0;
				wipe();
			}
		}
	}

	private void wipe() {
		String retour;
		do {
			System.out.println("Voulez vous continuer ? (Y/N)");
			retour = readConsole();
		} while (!(retour.equalsIgnoreCase("y"))
				&& !(retour.equalsIgnoreCase("n")));

		if (retour.equalsIgnoreCase("n")) {// si on ne continue pas, on
											// sauvegarde le score et exit
			saveScore();
			saveFinal();
			System.exit(0);
		}

		nbPartie++;
		grille.wipe();
		if (nbPartie % 2 == 1) {
			idJoueur = joueur2.getId();
		} else {
			idJoueur = joueur1.getId();
		}
		
		grille.affichage();

	}

	// -----------------------------------------------------------------------------------------------------------
	// méthode pour remplir le fichier log.txt
	private void saveScore() {
		File scoreFile = new File("score.v");
		try {
			if (scoreFile.exists() == false) { // si le fichier n'existe pas on
												// le creer
				scoreFile.createNewFile();
			}
			PrintWriter out = new PrintWriter(scoreFile);
			out.append("Resultats :\n" + "Joueur 1 " + this.joueur1.getNom()
					+ " : " + this.joueur1.getScore() + "\n" + "Joueur 2 "
					+ this.joueur2.getNom() + " : " + this.joueur2.getScore()
					+ "\n");
			out.close();
		} catch (IOException e) {
			System.err.println("Erreur sauvegarde des scores !");
		}
	}

	private void saveName() {
		if (this.joueur1.isHuman()) {
			Log.append("Joueur" + this.joueur1.getId() + " est humain "
					+ this.joueur1.getNom() + "\n");

		} else {
			Log.append("Joueur" + this.joueur1.getId() + " est une ia "
					+ this.joueur1.getNom() + "\n");

		}
		if (this.joueur2.isHuman()) {
			Log.append("Joueur" + this.joueur2.getId() + " est humain "
					+ this.joueur2.getNom() + "\n");

		} else {
			Log.append("Joueur" + this.joueur2.getId() + " est une ia "
					+ this.joueur2.getNom() + "\n");

		}
	}

	private void saveGame(int idJoueur, int column, int token) {
		if (token == 0) {
			Log.append("Manche commence" + "\n");
		}
		Log.append("Joueur " + idJoueur + " joue " + column + "\n");
	}

	private void saveResult(int idJoueur) {
		Log.append("Joueur " + idJoueur + " gagne" + "\n" + "score "
				+ this.joueur1.getScore() + " - " + this.joueur2.getScore()
				+ "\n");
	}

	private void saveResult(int idJoueur, int idJoueur2) {
		Log.append("égalité" + "\n" + "score " + this.joueur1.getScore()
				+ " - " + this.joueur2.getScore() + "\n" + "\n" + "\n");
	}

	private void saveFinal() {
		Log.append("Partie finie \n");
	}

	// -----------------------------------------------------------------------------------------------------

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
				} else if (args[0].equalsIgnoreCase("ia:random")) {
					if (idJoueur == 1) {
						this.joueur1 = new Ia(idJoueur, args[1], "random");
					} else {
						this.joueur2 = new Ia(idJoueur, args[1], "random");
					}
					idJoueur++;
				} else if (args[0].equalsIgnoreCase("ia:monkey")) {
					if (idJoueur == 1) {
						this.joueur1 = new Ia(idJoueur, args[1], "clever");
					} else {
						this.joueur2 = new Ia(idJoueur, args[1], "clever");
					}
					idJoueur++;
				} else {

					System.out
							.print("Le type de joueur doit etre 'humain' ou 'ia'\n > ");
					new ErrorInput(idJoueur);
				}

			} else {
				System.out.print("Veuillez entrer du text\n > ");
				new ErrorInput(idJoueur);
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
			System.err
					.println("Vous etes en cours de partie, veuillez quittez avec 'Sortir' ou rentrer un numero de colonne\n");
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

	private Joueur getJoueur(int id) {
		if (this.joueur1.getId() == id) {
			return this.joueur1;
		} else if (this.joueur2.getId() == id) {
			return this.joueur2;
		} else {
			throw new InvalidJoueur(id);
		}
	}

	private void winJoueur(int id) {
		getJoueur(id).incScore();
	}

	private void checkQuit(String writed) {
		if (writed.equalsIgnoreCase("Sortir")) {
			java.lang.System.exit(1);
		}
	}

}
