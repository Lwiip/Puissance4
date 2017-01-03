package log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class Log {

	public static void clearLog(){ // fonction qui nettoie le log en le suppr
		try{

    		File file = new File("log.txt");

    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}

    	}catch(Exception e){

    		e.printStackTrace();

    	}
	}
	
	public static void append(String logText) { //ajoute le text au log
		Writer output;
		try {
			output = new BufferedWriter(new FileWriter("log.txt", true));
			output.append(logText);
			output.close();
		} catch (IOException e) {
			System.err.println("Erreur ecriture du log \n");
		}
	}
}
