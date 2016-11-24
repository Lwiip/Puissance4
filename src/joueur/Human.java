package joueur;

class Human extends Joueur{
	
	
	public Human(){
		this.id = 0;
		this.nom = "Not declared";
		this.human = true;
	}
	
	public Human(int id, String nom){
		this.id = id; //gerer si jamais l'id est -1
		this.nom = nom;
		this.human = true;
	}
	
	//faire une lecture de la console (peut etre en sous packahe)
}
