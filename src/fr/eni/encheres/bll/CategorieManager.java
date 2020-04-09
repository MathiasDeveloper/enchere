/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.ArrayList;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class CategorieManager implements Manager<Categorie>{

	private static CategorieManager INSTANCE = null;
	private DAOFactory daoFactory = new DAOFactory();
	/**
	 * Récupération de l'instance
	 *
	 * @return CategorieManager
	 */
	public static CategorieManager getInstance() {
		if (INSTANCE == null)
			INSTANCE = new CategorieManager();
		return INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#create()
	 */
	@Override
	public Categorie create() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#update()
	 */
	@Override
	public Categorie update() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#delete()
	 */
	@Override
	public Categorie delete() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#find()
	 */
	@Override
	public Categorie find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#findAll()
	 */
	@Override
	public ArrayList<Categorie> findAll() throws BuisnessException {
		return daoFactory.getCategorieDAOImpl().findAll();
	}


}
