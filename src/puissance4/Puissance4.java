package puissance4;

import interface_graph.InterfaceGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import joueur.*;
import log.*;
import grille.*;
import error.*;

public class Puissance4 {

	private Joueur joueur[];
	// private Joueur joueur1;
	// private Joueur joueur2;
	private Grille grille;
	private int nbPartie;
	private int idJoueur;
	private int scoreWin;
	private int nbPlayer;
	private InterfaceGraph interf;

	public Puissance4() {

		Log.clearLog();
		System.out.println("Debut du jeu ...");

		GetPropertyValues properties = new GetPropertyValues();
		int x = 0;
		int y = 0;
		try {
			properties.getPropValues();
			x = properties.getX();
			y = properties.getY();
			scoreWin = properties.getScoreWin();
			nbPlayer = properties.getNbPlayer();
		} catch (IOException e) {
			System.err.println("Erreur lecture config");
		}
		joueur = new Joueur[nbPlayer];
		initPlayers(nbPlayer);
		saveName();
		this.grille = new Grille(x, y);
		this.interf = new InterfaceGraph(x, y, this.grille);
		this.nbPartie = 0;
	}

	public void start() {
		String writed = new String();

		int token = 0; // pour gérer l'affichage de "Manche commence" dans le
						// log
		int column = 1;
		boolean joueur_have_played = false;

		grille.affichage();

		int joueurStart = 1;
		idJoueur = joueurStart - 1;

		for (;;) {// boucle infiniiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiie
//			System.setIn(sauv);
			// idJoueur = checkTurn(j1turn);
			joueur_have_played = false;

			this.interf.update();

			System.out.println("Joueur " + (idJoueur + 1) + " > ");
			try {
				// si le joueur est un humain

				if (this.joueur[idJoueur].isHuman()) {
					// this.writed = readConsole();
					InputStream test = System.in;
					
					InputStreamReader fileInputStream=new InputStreamReader(System.in);
				    BufferedReader bufferedReader=new BufferedReader(fileInputStream);
					
				    try {
						while (System.in.available() <= 0 && interf.getClic() == -1){
							//boucle d'attente
						}
						
						if (System.in.available() > 0){
					    	writed = bufferedReader.readLine();
					    }
						
						if (interf.getClic() != -1){
							writed = "" + interf.getClic() + "";
							interf.setClic(-1); //reinit le clic
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				    
				    
				    
				    
//					Scanner scanner = new Scanner(System.in);
					

					

					checkQuit(writed);
					if (checkInt(writed)) {
						column = Integer.parseInt(writed);
						grille.insertPion(column - 1, idJoueur + 1);
						saveGame(idJoueur + 1, column, token);
						token++;
						joueur_have_played = true;
					} else {
						new ErrorInput(writed);
					}

				} else { // si le joueur est un IA
					Ia tmpIa = (Ia) getJoueur(idJoueur + 1);
					column = tmpIa.play(this.grille.getY(), grille,
							idJoueur + 1);

					grille.insertPion(column - 1, idJoueur + 1);
					saveGame(idJoueur + 1, column, token);
					token++;
					joueur_have_played = true;

				}

				if (!joueur_have_played) {
					idJoueur--;
				}

				if (joueur_have_played) {
					grille.affichage();

					if (grille.detectWin(grille.getTop(column - 1), column - 1)) {
						System.out.println("Le joueur " + (idJoueur + 1)
								+ " a gagné !");
						winJoueur(idJoueur + 1);
						saveResult(idJoueur + 1, "pas egaglite");
						joueurStart = ((joueurStart) % nbPlayer) + 1; // on
																		// change
																		// le
																		// joueur
																		// qui
																		// commence
						idJoueur = joueurStart - 1;
						token = 0;
						wipe();
					}
				}

			} catch (OutOfGrid o) {
				if (grille.checkGridFull()) {
					System.out.println("Match nul !");
					saveResult(0, "egalite"); // affiche égalitée dans le log
					joueurStart = ((joueurStart) % nbPlayer) + 1; // on
																	// change
																	// le
																	// joueur
																	// qui
																	// commence
					idJoueur = joueurStart - 1;
					token = 0;
					System.exit(1);
				}
			}

			if (grille.checkGridFull()) {
				System.out.println("Match nul !");
				saveResult(0, "egalite"); // affiche égalitée dans le log
				joueurStart = ((joueurStart) % nbPlayer) + 1; // on change
																// le joueur
																// qui
																// commence
				idJoueur = joueurStart - 1;
				token = 0;
				wipe();
			}

			if (token != 0) { // si la manche n'est pas terminée
				idJoueur = (idJoueur + 1) % nbPlayer;
			}
		}
	}

	private void wipe() {
		String retour;

		for (int i = 0; i < nbPlayer; i++) {
			if (this.joueur[i].getScore() == scoreWin) {
				saveFinal();
				System.out
						.println("La partie est terminée, le joueur gagnant est "
								+ this.joueur[i].getNom());
				System.exit(0);
			}
		}

		do {
			System.out.println("Voulez vous continuer ? (Y/N)");
			retour = readConsole();
		} while (!(retour.equalsIgnoreCase("y"))
				&& !(retour.equalsIgnoreCase("n")));

		if (retour.equalsIgnoreCase("n")) {// si on ne continue pas, on
											// sauvegarde le score et exit
											// saveScore();
			saveFinal();
			System.exit(0);
		}

		nbPartie++;
		grille.wipe();
		grille.affichage();

	}

	// -----------------------------------------------------------------------------------------------------------
	// méthode pour remplir le fichier log.txt
	// private void saveScore() {
	// Log.append("Resultats :\n" + "Joueur 1 " + this.joueur1.getNom()
	// + " : " + this.joueur1.getScore() + "\n" + "Joueur 2 "
	// + this.joueur2.getNom() + " : " + this.joueur2.getScore()
	// + "\n");
	// }

	private void saveName() {
		for (int i = 0; i < nbPlayer; i++) {

			if (this.joueur[i].isHuman()) {
				Log.append("Joueur " + this.joueur[i].getId() + " est humain "
						+ this.joueur[i].getNom() + "\n");

			} else {
				Log.append("Joueur " + this.joueur[i].getId() + " est une ia "
						+ this.joueur[i].getNom() + "\n");

			}
		}
	}

	private void saveGame(int idJoueur, int column, int token) {
		if (token == 0) {
			Log.append("Manche commence" + "\n");
		}
		Log.append("Joueur " + idJoueur + " joue " + column + "\n");
	}

	private void saveResult(int idJoueur, String option) {
		if (option.equals("egalite")) {
			Log.append("Egalite" + "\n");
			score();
		} else {
			Log.append("Joueur " + idJoueur + " gagne" + "\n");
			score();
		}
	}

	private void score() {
		for (int i = 0; i < this.nbPlayer - 1; i++) {
			Log.append(this.joueur[i].getScore() + " - ");
		}
		Log.append(this.joueur[this.nbPlayer - 1].getScore() + "\n");
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

	private void initPlayers(int nbJoueur) {
		String writed = new String();

		for (int i = 0; i < nbJoueur; i++) {

			System.out.print("Joueur " + (i + 1) + " ?\n > ");
			writed = readConsole();
			if (!writed.isEmpty()) { // on s'assure que l'utilisateur n'a
										// pas
										// fait juste entrée
				String args[] = writed.split(" ", 2); // sépare le avant et
														// apres le 1ere
														// espace

				if (args[0].equalsIgnoreCase("humain")) {
					this.joueur[i] = new Human(i + 1, args[1]);

				} else if (args[0].equalsIgnoreCase("ia:random")) {
					this.joueur[i] = new Ia(i + 1, args[1], "random");

				} else if (args[0].equalsIgnoreCase("ia:monkey")) {
					this.joueur[i] = new Ia(i + 1, args[1], "clever");
				} else {
					System.out
							.print("Le type de joueur doit etre 'humain' ou 'ia'\n > ");
					new ErrorInput(idJoueur);
				}

			} else {
				System.out.print("Veuillez entrer du text\n > ");
				new ErrorInput(idJoueur);
				i--;
			}

		}
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

	private Joueur getJoueur(int id) {
		for (int i = 0; i < nbPlayer; i++) {
			if (this.joueur[i].getId() == id) {
				return this.joueur[i];
			}
		}
		throw new InvalidJoueur(id);
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
