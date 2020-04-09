/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.ArrayList;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAOImpl;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class UtilisateurManager implements Manager<Utilisateur>{
	
	private static UtilisateurManager INSTANCE = null;
	private DAOFactory daoFactory = new DAOFactory();
	/**
	 * Récupération de l'instance
	 *
	 * @return UtilisateurManager
	 */
	public static UtilisateurManager getInstance() {
		if (INSTANCE == null)
			INSTANCE = new UtilisateurManager();
		return INSTANCE;
	}


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
		daoFactory.getUtilisateurDAOImpl().update(utilisateur);
	}

	/**
	 * {@inheritDoc}
	 * @throws BuisnessException 
	 * @see fr.eni.encheres.bll.Manager#delete()
	 */
	@Override
	public void delete(Utilisateur utilisateur) throws BuisnessException {
		daoFactory.getUtilisateurDAOImpl().delete(utilisateur);
	}

	/**
	 * {@inheritDoc}
	 * @throws BuisnessException 
	 * @see fr.eni.encheres.bll.Manager#find()
	 */
	@Override
	public Utilisateur find(int id) throws BuisnessException {
		return daoFactory.getUtilisateurDAOImpl().find(id);
	}
	
	/**
	 * {@inheritDoc}
	 * @throws BuisnessException 
	 * @see fr.eni.encheres.bll.Manager#verifier()
	 */
	public boolean verifier(Utilisateur utilisateur) throws BuisnessException {
		return daoFactory.getUtilisateurDAOImpl().verifier(utilisateur);
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
