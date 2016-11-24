package grille;

public class Grille {
	private int x;
	private int y;
	private Pion grille[][];
	
	public Grille(){
		this.x = 0;
		this.y = 0;
		this.grille = new Pion[x][y];
		initGrille();
	}
	
	public Grille(int x, int y){
		this.x = x;
		this.y = y;
		this.grille = new Pion[x][y];
		initGrille();
	}
	
	private void initGrille(){
		for(int i = 0; i < this.x; i++){
			for(int j = 0; j < this.y; j++){
				this.grille[i][j] = new Pion();
			}
		}
	}
	
	public void affichage(){
		//Affiche les numeros de colonnes
		for(int i = 1; i <= this.y; i++){
			System.out.print(" " + i + " ");
		}
		System.out.print("\n");
		
		//Affiche la grille
		for(int i = 0; i < this.x; i++){
			for(int j = 0; j < this.y; j++){
				System.out.print(this.grille[i][j].affichePion());
			}
			System.out.print("\n");
		}
	}
	
	public void insertPion(int y, int idJoueur){ //gerer le cas ou on sort de la grille, et plus rarement pas bon id
		for(int i = 0; i < this.x; i++){
			if (this.grille[i][y].getIdJoueur() != 0){ 	//si on on ntrouve un autre pion
				this.grille[i-1][y].setIdJoueur(idJoueur);
				return;
			} else if(i == (this.x - 1)){ //si on est en bout de ligne
				this.grille[i][y].setIdJoueur(idJoueur);
				return;
			}
		}
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
