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
		System.err.println("Veuillez saisir une entrée valide ! \n");

	}
	
	
	public OutOfGrid(int y){
		y=y+1;
		System.err.println("Veuillez saisir une entrée valide en " + y +"\n");
		File saveLog = new File("log.txt");
		try {
			if (saveLog.exists() == false) { // si le fichier n'existe pas on le
												// creer
				saveLog.createNewFile();
			}
			Writer output;
			output = new BufferedWriter(new FileWriter("log.txt", true)); 
			output.append("Erreur colonne non valide " + y + "\n");
			output.close();
		} catch (IOException e) {
			System.err.println("Erreur sauvegarde erreur colonne non valide \n");
		}
	}
	
	public OutOfGrid(int y, int v){
		y=y+1;
		System.err.println("Veuillez saisir une entrée valide, la colonne " + y +" est pleine !\n");
		try {
			
			Writer output;
			output = new BufferedWriter(new FileWriter("log.txt", true)); 
			output.append("Erreur colonne pleine " + y + "\n");
			output.close();
		} catch (IOException e) {
			System.err.println("Erreur sauvegarde erreur colonne pleine \n" );
		}
	}

}
