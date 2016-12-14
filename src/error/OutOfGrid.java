package error;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import log.*;

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
		Log.append("Erreur colonne non valide " + y + "\n");
	}
	
	public OutOfGrid(int y, int v){
		y=y+1;
		System.err.println("Veuillez saisir une entrée valide, la colonne " + y +" est pleine !\n");
		Log.append("Erreur colonne pleine " + y + "\n");
	}

}
