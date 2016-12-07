package error;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class OutOfGrid extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OutOfGrid(){
		System.err.println("Veuillez saisir une entrée valide !");
		
		File saveLog = new File("log.txt");
		try {
			if (saveLog.exists() == false) { // si le fichier n'existe pas on le
												// creer
				saveLog.createNewFile();
			}
			Writer output;
			output = new BufferedWriter(new FileWriter("log.txt", true)); 
			output.append("Erreur colonne non valide");
			output.close();
		} catch (IOException e) {
			System.err.println("Erreur sauvegarde saveFinal");
		}
	}

}
