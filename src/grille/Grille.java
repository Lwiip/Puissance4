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
	
	private void initGrille(){ // Initialisation de la grille
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
	
	
	
	
//------------------------------------------------------------------------------------------------------------------	
	private int detectWinCol(int x, int y){ //colone
		int compteur=1;
		int id=this.grille[x][y].getIdJoueur();
		
		if(id != 0){
		for(int i=x+1 ; i<this.x; i++){//parcourt par colonne vers le haut
			if(this.grille[i][y].getIdJoueur()!=id){
				break;
			}
			else if( this.grille[i][y].getIdJoueur()==id){
				compteur++;
				if(compteur==4){
					return 1;
				}
			}
		}
		for(int i=x-1 ; i>=0; i--){//parcourt par colonne vers le bas
			if(this.grille[i][y].getIdJoueur()!=id){
				break;
			}
			else if( this.grille[i][y].getIdJoueur()==id){
				compteur++;
				if(compteur==4){
					return 1;
				}
			}
		}
		
		}
		return 0;
	}
	
	
	private int detectWinLine(int x, int y){//ligne
		int compteur=1;
		int id=this.grille[x][y].getIdJoueur();
		
		if(id != 0){
		for(int i=y+1 ; i<this.y; i++){//parcourt par ligne vers la droite
			if(this.grille[x][i].getIdJoueur()!=id){
				break;
			}
			else if( this.grille[x][i].getIdJoueur()==id){
				compteur++;
				if(compteur==4){
					return 1;
				}
			}
		}
		for(int i=y-1 ; i>=0; i--){//parcourt ligne vers la gauche
			if(this.grille[x][i].getIdJoueur()!=id){
				break;
			}
			else if( this.grille[x][i].getIdJoueur()==id){
				compteur++;
				if(compteur==4){
					return 1;
				}
			}
		}
		
		}
		return 0;
	}
	
	private int detectWinDiagLeft(int x, int y){//Diagonal gauche
		int compteur=1;
		int id=this.grille[x][y].getIdJoueur();
		int i=x-1;
		int j=y-1;
		if(id != 0){
			while(i>=0 && j>=0){ // parcourt diagonal vers en haut à gauche
				if(this.grille[i][j].getIdJoueur()!=id){
					break;
				}
				else if( this.grille[i][j].getIdJoueur()==id){
					compteur++;
					if(compteur==4){
						return 1;
					}
				}
				i--;
				j--;
			}
			i=x+1;
			j=y+1;
			while(i<this.x && j<this.y){ // parcourt diagonal vers en bas à droite
				if(this.grille[i][j].getIdJoueur()!=id){
					break;
				}
				else if( this.grille[i][j].getIdJoueur()==id){
					compteur++;
					if(compteur==4){
						return 1;
					}
				}
				i++;
				j++;
			}
		}
		return 0;
	}
	
	
	private int detectWinDiagRight(int x, int y){//Diagonal droite
		int compteur=1;
		int id=this.grille[x][y].getIdJoueur();
		int i=x-1;
		int j=y+1;
		if(id != 0){
			while(i>=0 && j<this.y){ // parcourt diagonal vers en haut à droite
				if(this.grille[i][j].getIdJoueur()!=id){
					break;
				}
				else if( this.grille[i][j].getIdJoueur()==id){
					compteur++;
					if(compteur==4){
						return 1;
					}
				}
				i--;
				j++;
			}
			i=x+1;
			j=y-1;
			while(i<this.x && j>=0){ // parcourt diagonal vers en bas à gauche
				if(this.grille[i][j].getIdJoueur()!=id){
					break;
				}
				else if( this.grille[i][j].getIdJoueur()==id){
					compteur++;
					if(compteur==4){
						return 1;
					}
				}
				i++;
				j--;
			}
		}
		return 0;
	}
	
	public boolean detectWin(int x,int y){
		int retour1;
		int retour2;
		int retour3;
		int retour4;
		
		retour1=detectWinCol(x,y);
		retour2=detectWinLine(x,y);
		retour3=detectWinDiagLeft(x,y);
		retour4=detectWinDiagRight(x,y);
		
		
		if(retour1==1 || retour2==1 || retour3==1 || retour4==1 ){
			return true;
		}
		return false;
	}
	//---------------------------------------------------------------------------------
	
	public int getTop(int y){
		int i = 0;
		for(i = 0; i < this.x; i++){
			if(grille[i][y].getIdJoueur() != 0){
				return i;
			}
		}
		return i;
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
