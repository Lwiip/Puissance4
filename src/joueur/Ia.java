package joueur;
import java.util.Random;

public class Ia extends Joueur{
	
	public Ia(){
		this.id = 0;
		this.nom = "Not declared";
		this.human = false;
	}
	
	public Ia(int id, String nom){
		this.id = id; //gerer si jamais l'id est -1
		this.nom = nom;
		this.human = false;
	}
	
	public static int dumbIa(int maxSize){
		Random rand = new Random();
		int nombre = 1+ rand.nextInt(maxSize -1); //Entre 0 et le nombre de colonne de la grille	
		return nombre;
	}

}
