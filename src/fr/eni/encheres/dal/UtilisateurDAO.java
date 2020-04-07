/**
 * 
 */
package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Cas nominal : suite à la saisie d'un login et d'un mot de passe corrects, j'accède à la plateforme
 * Cas d'erreur : suite à la saisie d'un login inconnu ou d'un mot de passe erroné, le site affiche un message d'erreur
 * @author chamaello
 * @version enchere - v1.0
 * @date 7 avr. 2020
 **/

public interface UtilisateurDAO {
	public void verifier(Utilisateur utilisateur);
}
