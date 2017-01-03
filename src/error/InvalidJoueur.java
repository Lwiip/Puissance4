package error;

//Classe Erreur Joueur id inconnu
public class InvalidJoueur extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidJoueur(){
		
	}
	
	public InvalidJoueur(int id){
		System.err.println("Utilisateur " + id + " inconnu !"); // Affiche dans la console 
		
		
	}
}
