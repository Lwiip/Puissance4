package interface_graph;

import error.InvalidJoueur;
import grille.Grille;

import javax.swing.*;
import java.awt.*;

public class InterfaceGraph {
	private JButton boutons[];
	private JLabel pions[][];
	
	private Panneau pan = new Panneau();
	private JFrame fenetre;

	private int largeur;
	private int hauteur;
	private Grille grille;

	public InterfaceGraph(int x, int y, Grille gri) {

		this.grille = gri;
		this.largeur = y;
		this.hauteur = x;
		
		pan.setLargeur(y);
		pan.setHauteur(x);
		pan.setGrille(gri);

		this.fenetre = new JFrame();

		// Définit un titre pour notre fenêtre
		fenetre.setTitle("Puissance 4");
		// Définit sa taille : 400 pixels de large et 100 pixels de haut
		fenetre.setSize(this.largeur * 50, (this.hauteur + 1) * 50);
		// Nous demandons maintenant à notre objet de se positionner au centre
		fenetre.setLocationRelativeTo(null);
		// Termine le processus lorsqu'on clique sur la croix rouge
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Empeche la fenetre d'etre redimentionnée
		fenetre.setResizable(false);

		// Met la couleur de fond a bleu
		pan.setBackground(Color.BLUE);
		// ajout des pions
		fenetre.setContentPane(pan);

		// Ajout du bouton colonnes
		GridLayout gl = new GridLayout(1, largeur);
		gl.setHgap(3); // espacement entre les boutons
		gl.setVgap(3);
		fenetre.setLayout(gl);
		// On ajoute le bouton au content pane de la JFram
		boutons = new JButton[largeur]; // nb de colonnes
		for (JButton elem : boutons) {
			elem = new JButton();
			elem.setOpaque(false);
			elem.setContentAreaFilled(false);
			// elem.setBorderPainted(false);
			
			
			
			fenetre.getContentPane().add(elem);
		}


		// Et enfin, la rendre visible
		fenetre.setVisible(true);

	}

	public void update(){
		this.fenetre.repaint();
	}

	public static void main(String[] args) {

		InterfaceGraph inter = new InterfaceGraph(7, 6, new Grille(7, 6));

	}
}