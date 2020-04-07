package fr.eni.encheres.bo;

/**
 * Classe objet
 */
public class Utilisateur {
    private String identifiant;
    private String motdepasse;
    
    public Utilisateur(String identifiant, String motdepasse) {
    	this.identifiant = identifiant;
    	this.motdepasse = motdepasse;
    }

	/**
	 * Getter pour identifiant.
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * Setter pour identifiant.
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * Getter pour motdepasse.
	 * @return the motdepasse
	 */
	public String getMotdepasse() {
		return motdepasse;
	}

	/**
	 * Setter pour motdepasse.
	 * @param motdepasse the motdepasse to set
	 */
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Utilisateur [identifiant=");
		builder.append(identifiant);
		builder.append(", motdepasse=");
		builder.append(motdepasse);
		builder.append("]");
		return builder.toString();
	}
    
    
}


