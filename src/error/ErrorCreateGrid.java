package error;
import log.Log;

public class ErrorCreateGrid extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ErrorCreateGrid(){
		
	}
	
	public ErrorCreateGrid(int x,int y){
		System.err.println("Erreur création de la grille vérifier que x*y est pair et que y>=4" + "\n");
		Log.append("Erreur création de la grille vérifier que x*y est pair et que y>=4" + "\n");
	}

}