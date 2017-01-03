package error;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import log.*;

// Class erreur saisi des colonnes par les joueurs
public class OutOfGrid extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OutOfGrid(){
		System.err.println("Veuillez saisir une entrée valide ! \n");

	}
	
	// colonne non valide
	public OutOfGrid(int y){
		y=y+1;
		System.err.println("Veuillez saisir une entrée valide en " + y +"\n"); // Affiche dans la console 
		Log.append("Erreur colonne non valide " + y + "\n"); // Ajout dans le fichier log
	}
	
	// colonne non valide car colonne pleine
	public OutOfGrid(int y, int v){
		y=y+1;
		System.err.println("Veuillez saisir une entrée valide, la colonne " + y +" est pleine !\n"); // Affiche dans la console
		Log.append("Erreur colonne pleine " + y + "\n");// Ajout dans le fichier log
	}

}
