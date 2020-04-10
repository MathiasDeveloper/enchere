/**
 * 
 */
package fr.eni.encheres.bo;

import java.sql.Time;
import java.sql.Date;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class Enchere {

	/**
	 * Article
	 */
	private Article article;
	/**
	 * @date
	 */
	private Date dateEnchere;

	/**
	 * @int
	 */
	private int montantEnchere;

	/**
	 * @time
	 */
	private Time heureDebutEnchere;

	/**
	 * @time
	 */
	private Time heureFinEnchere;


	private Utilisateur utilisateur;

	/**
	 * Getter Article
	 *
	 * @return Article
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * Setter Article
	 *
	 * @param article
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

	/**
	 * get dateEnchere
	 *
	 * @return dateEnchere
	 */
	public Date getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * Setter dateEnchere
	 *
	 * @param dateEnchere
	 */
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/**
	 * getter montantEnchere
	 *
	 * @return montantEnchere
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}

	/**
	 * Setter montantEnchere
	 *
	 * @param montantEnchere
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	/**
	 * getter heureDebutEnchere
	 * @return heureDebutEnchere
	 */
	public Time getHeureDebutEnchere() {
		return heureDebutEnchere;
	}

	/**
	 * setter heureDebutEnchere
	 * @param heureDebutEnchere
	 */
	public void setHeureDebutEnchere(Time heureDebutEnchere) {
		this.heureDebutEnchere = heureDebutEnchere;
	}

	/**
	 * getter heureFinEnchere
	 * @return heureFinEnchere
	 */
	public Time getHeureFinEnchere() {
		return heureFinEnchere;
	}

	/**
	 * setter heureFinEnchere
	 * @param heureFinEnchere
	 */
	public void setHeureFinEnchere(Time heureFinEnchere) {
		this.heureFinEnchere = heureFinEnchere;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "Enchere{" +
				"article=" + article +
				", dateEnchere=" + dateEnchere +
				", montantEnchere=" + montantEnchere +
				", heureDebutEnchere=" + heureDebutEnchere +
				", heureFinEnchere=" + heureFinEnchere +
				'}';
	}
}
