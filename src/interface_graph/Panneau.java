package interface_graph;

import error.InvalidJoueur;
import grille.Grille;

import java.awt.*;

import javax.swing.*;

public class Panneau extends JPanel {

	private int largeur;
	private int hauteur;
	
	private Grille grille;
	
	public void paintComponent(Graphics g) {
		for (int i = 0; i < this.hauteur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				g.setColor(getColor(i, j));
				g.fillOval(j * 50, i * 50, 46, 46);
			}
		}
	}

	private Color getColor(int x, int y) {
		if (this.grille.getIdJoueur(x, y) == 0) {
			return Color.WHITE;
		} else if (this.grille.getIdJoueur(x, y) == 1) {
			return Color.BLUE;
		} else if (this.grille.getIdJoueur(x, y) == 2) {
			return Color.RED;
		} else {
			new InvalidJoueur(this.grille.getIdJoueur(x, y));
			return Color.BLACK;
		}
	}
	

	public int getLargeur() {
		return largeur;
	}


	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}


	public int getHauteur() {
		return hauteur;
	}


	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}


	public Grille getGrille() {
		return grille;
	}


	public void setGrille(Grille grille) {
		this.grille = grille;
	}	
	
	
	
}