package interface_graph;

import error.InvalidJoueur;
import grille.Grille;

import javax.swing.*;
import java.awt.*;

public class InterfaceGraph {
	private JButton boutons[];
	JPanel pan = new JPanel();
	
	private int largeur;
	private int hauteur;
	private Grille grille;

	public InterfaceGraph(int larg, int haut, Grille gri) {

		this.grille = gri;
		this.largeur = larg;
		this.hauteur = haut;
		
		JFrame fenetre = new JFrame();

		// Définit un titre pour notre fenêtre
		fenetre.setTitle("Puissance 4");
		// Définit sa taille : 400 pixels de large et 100 pixels de haut
		fenetre.setSize(this.largeur * 50, this.hauteur * 50);
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
		boutons = new JButton[largeur]; //nb de colonnes
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
	
	public void paintComponent(Graphics g) {
		g.fillOval( 50, 50, 30, 30);
		for(int i = 0; i < this.largeur; i++ ){
			for (int j = 0; j < this.hauteur; j++){
				g.setColor(getColor(i, j));
				g.fillOval(i * 50, j * 50, 30, 30);
			}
		}
	}
	
	private Color getColor(int x, int y){
		if (this.grille.getIdJoueur(x, y) == 0){
			return Color.WHITE;
		} else if (this.grille.getIdJoueur(x, y) == 1){
			return Color.BLUE;
		} else if (this.grille.getIdJoueur(x, y) == 2){
			return Color.RED;
		} else {
			new InvalidJoueur(this.grille.getIdJoueur(x, y));
			return Color.BLACK;
		}
	}
	
	public static void main(String[] args){

	    InterfaceGraph inter = new InterfaceGraph(6, 7, new Grille(6,7));

	  }   
}