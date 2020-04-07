/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.ArrayList;

import fr.eni.encheres.bo.Utilisateur;
<<<<<<< HEAD
=======
import fr.eni.encheres.dal.UtilisateurDAOImpl;
>>>>>>> refs/remotes/origin/release.creationclasses

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class UtilisateurManager implements Manager<Utilisateur>{

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#getInstance()
	 */
	@Override
	public Utilisateur getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#create()
	 */
	@Override
	public Utilisateur create() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#update()
	 */
	@Override
	public Utilisateur update() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#delete()
	 */
	@Override
	public Utilisateur delete() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#find()
	 */
	@Override
<<<<<<< HEAD
	public Utilisateur find() {
		// TODO Auto-generated method stub
		return null;
=======
	public Utilisateur find(int id) {
		UtilisateurDAOImpl utilisateurDAOImpl = new UtilisateurDAOImpl();
		return utilisateurDAOImpl.find(id);
>>>>>>> refs/remotes/origin/release.creationclasses
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#findAll()
	 */
	@Override
	public ArrayList<Utilisateur> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



}
