package error;

public class OutOfGrid extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OutOfGrid(){
		System.err.println("Veuillez saisir une entrée valide !");
	}

}
