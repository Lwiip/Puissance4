package joueur;

// Class joueur avec id, nom, boléen si humain ou pas, et score
public abstract class Joueur {
	protected int id;
	protected String nom;
	protected boolean human;
	protected int score;
	
	
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
	
	public int getScore() {
		return score;
	}

	public void incScore() {
		this.score++;
	}
}
