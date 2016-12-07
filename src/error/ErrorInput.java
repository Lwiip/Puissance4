package error;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ErrorInput extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorInput(){
		
	}
	
	public ErrorInput(int idJoueur){
		File saveLog = new File("log.txt");
		try {
			if (saveLog.exists() == false) { // si le fichier n'existe pas on le
												// creer
				saveLog.createNewFile();
			}
			Writer output;
			output = new BufferedWriter(new FileWriter("log.txt", true)); 
			output.append("Erreur saisie Joueur " + idJoueur + "\n");
			output.close();
		} catch (IOException e) {
			System.err.println("Erreur saisie Joueur \n");
		}
	}
	
}
