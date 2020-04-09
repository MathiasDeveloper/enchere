/**
 * 
 */
package fr.eni.encheres.dal;

import java.sql.*;
import java.util.ArrayList;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.outils.BuisnessException;
import fr.eni.encheres.outils.Log;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class ArticleDAOImpl implements DAO<Article>{

	private ConnectionProvider connectionProvider = new ConnectionProvider();

	private static final String INSERT_INTO =
			"INSERT INTO `ARTICLES` (`idArticle`, `nomArticle`, `description`, `dateDebutEnchere`," +
			"`dateFinEnchere`, `prixInitial`, `prixVente`, `idUtilisateur`, `idCategorie`) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?, 1, ?)";

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#create(java.lang.Object)
	 */
	@Override
	public void create(Article article) throws BuisnessException {
		try {
			PreparedStatement ps = connectionProvider.getInstance().prepareStatement(
					INSERT_INTO,
					Statement.RETURN_GENERATED_KEYS
			);

			ResultSet rs = ps.getGeneratedKeys();

			while (rs.next()){
				article.setIdArticle(rs.getInt(1));
			}
			ps.setString (2, article.getNomArticle());
			ps.setString (3, article.getDescription());
			ps.setDate   (4, (Date) article.getDateDebutEnchere());
			ps.setDate   (5, (Date) article.getDateFinEnchere());
			ps.setInt    (6, article.getPrixInitial());
			ps.setInt    (7, article.getIdUtilisateur());
			ps.setInt    (8, article.getIdCategorie());

		} catch (SQLException e){
			new Log(e.getMessage());
			throw new BuisnessException(e.getMessage(),e);
		}
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(Article article) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Article article) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#find(int)
	 */
	@Override
	public Article find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return ArrayList<Article>
	 */
	@Override
	public ArrayList<Article> findAll() {
		// TODO Auto-generated method stub
		return null;
	}




}
