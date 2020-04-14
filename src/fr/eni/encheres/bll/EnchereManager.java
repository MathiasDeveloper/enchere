package fr.eni.encheres.bll;

import java.util.ArrayList;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class EnchereManager implements Manager<Enchere>{

	/**
	 * @param DDAOFactory
	 */
	private DAOFactory daoFactory = new DAOFactory();


	/**
	 * @param EnchereManager
	 */
	private static EnchereManager INSTANCE =  null;

	public static EnchereManager getInstance() {
		if (INSTANCE == null)
			INSTANCE = new EnchereManager();
		return INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#create()
	 */
	@Override
	public void create(Enchere enchere) throws BuisnessException {
		daoFactory.getEnchereDAOImpl().create(enchere);
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#update()
	 */
	@Override
	public void update(Enchere enchere) {

	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#delete()
	 */
	@Override
	public void delete(Enchere enchere) {

	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#find()
	 */
	public Enchere find(int id) throws BuisnessException {
		return daoFactory.getEnchereDAOImpl().find(id);
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#findAll()
	 */
	@Override
	public ArrayList<Enchere> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



}
