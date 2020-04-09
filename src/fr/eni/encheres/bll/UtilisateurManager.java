/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.ArrayList;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDAOImpl;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class UtilisateurManager implements Manager<Utilisateur>{


	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#create()
	 */
	@Override
	public void create(Utilisateur utilisateur) {
		
	}

	/**
	 * {@inheritDoc}
	 * @throws BuisnessException 
	 * @see fr.eni.encheres.bll.Manager#update()
	 */
	@Override
	public void update(Utilisateur utilisateur) throws BuisnessException {
		UtilisateurDAOImpl utilisateurDAOImpl = new UtilisateurDAOImpl();
		utilisateurDAOImpl.update(utilisateur);
	}

	/**
	 * {@inheritDoc}
	 * @throws BuisnessException 
	 * @see fr.eni.encheres.bll.Manager#delete()
	 */
	@Override
	public void delete(Utilisateur utilisateur) throws BuisnessException {
		UtilisateurDAOImpl utilisateurDAOImpl = new UtilisateurDAOImpl();
		utilisateurDAOImpl.delete(utilisateur);
	}

	/**
	 * {@inheritDoc}
	 * @throws BuisnessException 
	 * @see fr.eni.encheres.bll.Manager#find()
	 */
	@Override
	public Utilisateur find(int id) throws BuisnessException {
		UtilisateurDAOImpl utilisateurDAOImpl = new UtilisateurDAOImpl();
		return utilisateurDAOImpl.find(id);
	}
	
	/**
	 * {@inheritDoc}
	 * @throws BuisnessException 
	 * @see fr.eni.encheres.bll.Manager#verifier()
	 */
	public boolean verifier(Utilisateur utilisateur) throws BuisnessException {
		UtilisateurDAOImpl utilisateurDAOImpl = new UtilisateurDAOImpl();
		return utilisateurDAOImpl.verifier(utilisateur);
	}
	
	/**
	 * @see fr.eni.encheres.bll.Manager#findAll()
	 */
	@Override
	public ArrayList<Utilisateur> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
