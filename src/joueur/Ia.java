package joueur;

class Ia extends Joueur{
	
	public Ia(){
		this.id = 0;
		this.nom = "Not declared";
		this.human = false;
	}
	
	public Ia(int id, String nom){
		this.id = id; //gerer si jamais l'id est -1
		this.nom = nom;
		this.human = true;
	}

}
