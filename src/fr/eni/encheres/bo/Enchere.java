/**
 * 
 */
package fr.eni.encheres.bo;

import java.sql.Time;
import java.util.Date;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class Enchere {
	
	private Article article;
	private Date dateEnchere;
	private int montantEnchere;
	private Time heureDebutEnchere; 
	private Time heureFinEnchere; 
	private Utilisateur utilisateur;
	
	/**
	 * Getter pour article.
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}
	/**
	 * Setter pour article.
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}
	/**
	 * Getter pour dateEnchere.
	 * @return the dateEnchere
	 */
	public Date getDateEnchere() {
		return dateEnchere;
	}
	/**
	 * Setter pour dateEnchere.
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	/**
	 * Getter pour montantEnchere.
	 * @return the montantEnchere
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}
	/**
	 * Setter pour montantEnchere.
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	/**
	 * Getter pour heureDebutEnchere.
	 * @return the heureDebutEnchere
	 */
	public Time getHeureDebutEnchere() {
		return heureDebutEnchere;
	}
	/**
	 * Setter pour heureDebutEnchere.
	 * @param heureDebutEnchere the heureDebutEnchere to set
	 */
	public void setHeureDebutEnchere(Time heureDebutEnchere) {
		this.heureDebutEnchere = heureDebutEnchere;
	}
	/**
	 * Getter pour heureFinEnchere.
	 * @return the heureFinEnchere
	 */
	public Time getHeureFinEnchere() {
		return heureFinEnchere;
	}
	/**
	 * Setter pour heureFinEnchere.
	 * @param heureFinEnchere the heureFinEnchere to set
	 */
	public void setHeureFinEnchere(Time heureFinEnchere) {
		this.heureFinEnchere = heureFinEnchere;
	}
	
	
	/**
	 * Getter pour utilisateur.
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	/**
	 * Setter pour utilisateur.
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Enchere [article=" + article + ", dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere
				+ ", heureDebutEnchere=" + heureDebutEnchere + ", heureFinEnchere=" + heureFinEnchere + "]";
	}
	
	
	
	
}
