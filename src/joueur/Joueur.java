package joueur;

public abstract class Joueur {
	protected int id;
	protected String nom;
	protected boolean human;
	
//	public Joueur(){
//		this.id = 0;
//		this.nom = "Not declared";
//		this.human = false;
//	}
//	
//	public Joueur(int id, String nom, boolean isHuman){
//		this.id = id; //gerer si jamais l'id est -1
//		this.nom = nom;
//		this.human = isHuman;
//	}
	
	/*
	 * Getter et Setter
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public boolean isHuman() {
		return human;
	}

	public void setHuman(boolean human) {
		this.human = human;
	}
}
