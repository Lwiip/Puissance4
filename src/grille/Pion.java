package grille;

class Pion {
	private int x;
	private int y;
	private int idJoueur;
	
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
	
	protected String affichePion(){
		if(this.idJoueur == 1){
			return " X ";
		} else if(this.idJoueur == 2){
			return " O ";
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
