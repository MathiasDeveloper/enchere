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
	
	private static EnchereManager INSTANCE = null;
	private DAOFactory daoFactory = new DAOFactory();

	/**
<<<<<<< HEAD
	 * Récupération de l'instance
	 *
	 * @return EcnhereManager
	 */
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
	 * Methode qui met à jour l'enchere à partir des infos de la vue
	 */
	@Override
	public void update(Enchere enchere) throws BuisnessException {
		daoFactory.getEnchereDAOImpl().update(enchere);
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
	 * @throws BuisnessException 
	 * @see fr.eni.encheres.bll.Manager#findAll()
	 */
	@Override
	public ArrayList<Enchere> findAll() throws BuisnessException {
		return daoFactory.getEnchereDAOImpl().findAll();
	}
	
	public ArrayList<Enchere> findByCondition(String name, int categorie, String condition) throws BuisnessException{
		return daoFactory.getEnchereDAOImpl().findByCondition(name, categorie, condition);
	}



}
