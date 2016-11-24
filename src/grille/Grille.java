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
