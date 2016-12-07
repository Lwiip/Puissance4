package error;


import log.Log;

public class ErrorInput extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorInput(){
		
	}
	
	public ErrorInput(int idJoueur){
		Log.append("Erreur saisie Joueur " + idJoueur + "\n");
	}
	
	public ErrorInput(String writed){
		Log.append("Erreur colone non valide " +writed+ "\n");
		
	}
}
