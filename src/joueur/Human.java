package joueur;

//class humaine avec un id, un nom, un booléen pour dire qu'on est humain et un score
public class Human extends Joueur{
	
	//constructeur par défault
	public Human(){
		this.id = 0;
		this.nom = "Not declared";
		this.human = true;
		this.score = 0;
	}
	
	public Human(int id, String nom){
		this.id = id; 
		this.nom = nom;
		this.human = true;
		this.score = 0;
	}
}
