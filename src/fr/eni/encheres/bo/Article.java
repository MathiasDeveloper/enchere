/**
 * 
 */
package fr.eni.encheres.bo;

import java.sql.Date;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class Article {

	/**
	 * Id Article
	 * @int
	 */
	private int idArticle;

	/**
	 * Id Article
	 * @string
	 */
	private String nomArticle;

	/**
	 * description
	 *
	 * @string
	 */
	private String description;

	/**
	 * dateDebutEnchere
	 *
	 * @date
	 */
	private Date dateDebutEnchere;

	/**
	 * dateFinEnchere
	 *
	 * @date
	 */
	private Date dateFinEnchere;

	/**
	 * prixInitial
	 *
	 * @int
	 */
	private int prixInitial;

	/**
	 * prixVente
	 *
	 * @int
	 */
	private int prixVente;

	/**
	 * etatVente
	 *
	 * @boolean
	 */
	private boolean etatVente;


	private Categorie categorie;

	private Utilisateur utilisateur;


	/**
	 * Récupération de l'article
	 *
	 * @return idArticle
	 */
	public int getIdArticle() {
		return idArticle;
	}

	/**
	 * Ajout de l'idArticle
	 *
	 * @param idArticle => int IdArticle
	 */
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	/**
	 * Récupération du nom de l'article
	 *
	 * @return nomArticle => String nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}

	/**
	 * Ajout du nom de l'article
	 *
	 * @param nomArticle => String nomArticle
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	/**
	 * Récupère la description de l'article
	 *
	 * @return description => String description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Ajoute la description de l'article
	 *
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Récupère la date de début de l'enchere
	 *
	 * @return dateDebutEnchere
	 */
	public Date getDateDebutEnchere() {
		return dateDebutEnchere;
	}

	/**
	 * Ajoute la date du début de l'enchere
	 *
	 * @param dateDebutEnchere
	 */
	public void setDateDebutEnchere(Date dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}

	/**
	 * Récupère la date de fin de l'enchere
	 *
	 * @return dateFinEnchere
	 */
	public Date getDateFinEnchere() {
		return dateFinEnchere;
	}

	/**
	 * Ajoute la date de fin de l'enchere
	 *
	 * @param dateFinEnchere
	 */
	public void setDateFinEnchere(Date dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}

	/**
	 * Récupère le prix initial de l'article
	 *
	 * @return prixInitial
	 */
	public int getPrixInitial() {
		return prixInitial;
	}

	/**
	 * Ajoute le prix initial de l'article
	 *
	 * @param prixInitial
	 */
	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	/**
	 * Récupère le prix de vente
	 *
	 * @return prixVente
	 */
	public int getPrixVente() {
		return prixVente;
	}

	/**
	 * Ajoute le prix de vente
	 *
	 * @param prixVente
	 */
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	/**
	 * Détermine si l'article est vendu ou non
	 * @return
	 */
	public boolean isEtatVente() {
		return etatVente;
	}

	/**
	 * Ajout de l'etat de la vente
	 *
	 * @param etatVente => true ou false
	 */
	public void setEtatVente(boolean etatVente) {
		this.etatVente = etatVente;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "Article{" +
				"idArticle=" + idArticle +
				", nomArticle='" + nomArticle + '\'' +
				", description='" + description + '\'' +
				", dateDebutEnchere=" + dateDebutEnchere +
				", dateFinEnchere=" + dateFinEnchere +
				", prixInitial=" + prixInitial +
				", prixVente=" + prixVente +
				", etatVente=" + etatVente +
				'}';
	}
}