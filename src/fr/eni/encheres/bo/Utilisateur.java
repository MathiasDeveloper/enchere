package fr.eni.encheres.bo;

import java.util.List;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class Utilisateur {
    private int idUtilisateur;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String rue;
    private String codePostal;
    private String ville;
    private String motDePasse;
    private int credit;
    private boolean administrateur;
    private List<Enchere> encheres;
    private List<Article> articles;
    
	/**
	 * Constructeur
	 */
	public Utilisateur() {
		super();
	}
	
	/**
	 * Constructeur
	 * @param pseudo
	 * @param email
	 * @param motDePasse
	 */
	public Utilisateur(String identifiant, String motdepasse, boolean isEmail) {
		if(isEmail == true ) {
			this.email = identifiant;
		}else {
			this.pseudo = identifiant;
		}	
		this.motDePasse = motdepasse;
	}



	/**
	 * Getter pour noUtilisateur.
	 * @return the noUtilisateur
	 */
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	/**
	 * Setter pour noUtilisateur.
	 * @param noUtilisateur the noUtilisateur to set
	 */
	public void setIdUtilisateur(int noUtilisateur) {
		this.idUtilisateur = noUtilisateur;
	}
	/**
	 * Getter pour pseudo.
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	/**
	 * Setter pour pseudo.
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	/**
	 * Getter pour nom.
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Setter pour nom.
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * Getter pour prenom.
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * Setter pour prenom.
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * Getter pour email.
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Setter pour email.
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Getter pour telephone.
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * Setter pour telephone.
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * Getter pour rue.
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}
	/**
	 * Setter pour rue.
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	/**
	 * Getter pour codePostal.
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}
	/**
	 * Setter pour codePostal.
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	/**
	 * Getter pour ville.
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * Setter pour ville.
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * Getter pour motDePasse.
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}
	/**
	 * Setter pour motDePasse.
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	/**
	 * Getter pour credit.
	 * @return the credit
	 */
	public int getCredit() {
		return credit;
	}
	/**
	 * Setter pour credit.
	 * @param credit the credit to set
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}
	/**
	 * Getter pour administrateur.
	 * @return the administrateur
	 */
	public boolean isAdministrateur() {
		return administrateur;
	}
	/**
	 * Setter pour administrateur.
	 * @param administrateur the administrateur to set
	 */
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}
	/**
	 * Getter pour encheres.
	 * @return the encheres
	 */
	public List<Enchere> getEncheres() {
		return encheres;
	}
	/**
	 * Setter pour encheres.
	 * @param encheres the encheres to set
	 */
	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}
	/**
	 * Getter pour articles.
	 * @return the articles
	 */
	public List<Article> getArticles() {
		return articles;
	}
	/**
	 * Setter pour articles.
	 * @param articles the articles to set
	 */
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal="
				+ codePostal + ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit
				+ ", administrateur=" + administrateur + ", encheres=" + encheres + ", articles=" + articles + "]";
	}
    
}
