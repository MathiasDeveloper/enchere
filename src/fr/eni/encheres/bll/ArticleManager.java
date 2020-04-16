/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.ArrayList;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class ArticleManager implements Manager<Article>{

	private static ArticleManager INSTANCE = null;
	private DAOFactory daoFactory = new DAOFactory();

	/**
	 * Récupère l'instance de ArticleManager
	 * @return ArticleManager
	 */
	public static ArticleManager getInstance(){
		if (INSTANCE == null)
			INSTANCE = new ArticleManager();
		return INSTANCE;
	}

	@Override
	public void create(Article article) throws BuisnessException {
	}

	/**
	 * Méthode de création d'un article
	 *
	 * @param article
	 * @throws BuisnessException
	 */
	public Article createArticle(Article article) throws BuisnessException {
		return daoFactory.getArticleDAOImpl().createArticle(article);
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#update()
	 */
	@Override
	public void update(Article article) {

	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#delete()
	 */
	@Override
	public void delete(Article article) {

	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#find()
	 */
	@Override
	public Article find(int id) throws BuisnessException {
		return daoFactory.getArticleDAOImpl().find(id);
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.bll.Manager#findAll()
	 */
	@Override
	public ArrayList<Article> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
