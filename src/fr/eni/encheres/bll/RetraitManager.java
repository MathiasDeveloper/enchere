/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.ArrayList;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class RetraitManager implements Manager<Retrait>{
	
	private static RetraitManager INSTANCE = null;
	private DAOFactory daoFactory = new DAOFactory();

	/**
	 * Récupère l'instance de ArticleManager
	 * @return ArticleManager
	 */
	public static RetraitManager getInstance(){
		if (INSTANCE == null)
			INSTANCE = new RetraitManager();
		return INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 * @throws BuisnessException 
	 * @see fr.eni.encheres.bll.Manager#create()
	 */
	@Override
	public void create(Retrait retrait) throws BuisnessException {
		daoFactory.getRetraitDAOImpl().create(retrait);
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#update()
	 */
	@Override
	public void update(Retrait retrait) {

	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#delete()
	 */
	@Override
	public void delete(Retrait retrait) {

	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#find()
	 */
	@Override
	public Retrait find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#findAll()
	 */
	@Override
	public ArrayList<Retrait> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



}
