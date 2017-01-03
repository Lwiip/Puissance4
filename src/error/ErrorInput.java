package error;


import log.Log;

//Classe Erreur pour les entrées saisi des joueurs
public class ErrorInput extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorInput(){
		
	}
	
	public ErrorInput(int idJoueur){
		Log.append("Erreur saisie Joueur " + idJoueur + "\n"); //Ajout dans le fichier log 
	}
	
	public ErrorInput(String writed){
		Log.append("Erreur colone non valide " +writed+ "\n");//Ajout dans le fichier log 
		
	}
}
