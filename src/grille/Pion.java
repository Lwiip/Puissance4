package grille;

// Class Pion: position du pion x, y et Id du joueur
class Pion {
	private int x;
	private int y;
	private int idJoueur;
	
	//constructeur par défault
	public Pion(){
		this.x = 0;
		this.y = 0;
		this.idJoueur = 0;
	}
	
	public Pion(int x, int y, int idJoueur){
		this.x = x;
		this.y = y;
		this.idJoueur = idJoueur;
	}
	
	//pour la copie des pions
	public Pion(Pion another){
		this.x=another.x;
		this.y=another.y;
		this.idJoueur=another.idJoueur;
	}
	
	//Pour l'affichage dans la console. Prend en compte jusqu'a 8 joueurs
	protected String affichePion(){
		if(this.idJoueur == 1){
			return " X ";
		} else if(this.idJoueur == 2){
			return " O ";
		} else if(this.idJoueur == 3){
			return " * ";
		} else if(this.idJoueur == 4){
			return " + ";
		} else if(this.idJoueur == 5){
			return " # ";
		} else if(this.idJoueur == 6){
			return " @ ";
		} else if(this.idJoueur == 7){
			return " $ ";
		} else {
			return " . ";
		}
	}
	
	/*
	 * Getteur et Setteur
	 */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getIdJoueur() {
		return idJoueur;
	}

	public void setIdJoueur(int idJoueur) {
		this.idJoueur = idJoueur;
	}
}
