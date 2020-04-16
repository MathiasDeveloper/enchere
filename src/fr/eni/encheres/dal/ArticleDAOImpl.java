/**
 * 
 */
package fr.eni.encheres.dal;

import java.sql.*;
import java.util.ArrayList;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
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
			"INSERT INTO ARTICLES (nomArticle, description, dateDebutEnchere," +
			"dateFinEnchere, prixInitial, prixVente, idUtilisateur, idCategorie) " +
			"VALUES (?, ?, ?, ?, ?, 0, ?, ?)";

	private static final String FIND_BY_ID = "SELECT " +
			"idArticle," +
			" nomArticle, " +
			"description," +
			" dateDebutEnchere, " +
			"dateFinEnchere," +
			" prixInitial, " +
			"prixVente, " +
			"idUtilisateur, " +
			"idCategorie " +
			"FROM ARTICLES " +
			"WHERE idArticle = ?";

	private Article article = new Article();

	private Utilisateur utilisateur = new Utilisateur();

	private Categorie categorie = new Categorie();

	@Override
	public void create(Article objet) throws BuisnessException {
	}

	/**
	 * Méthode de création d'un article
	 *
	 * @param article
	 * @return Article
	 * @throws BuisnessException
	 */
	public Article createArticle(Article article) throws BuisnessException {


		try {
			PreparedStatement ps = connectionProvider.getInstance().prepareStatement(
					INSERT_INTO,
					Statement.RETURN_GENERATED_KEYS
			);


			ps.setString (1, article.getNomArticle().trim());
			ps.setString (2, article.getDescription().trim());
			ps.setDate   (3, article.getDateDebutEnchere());
			ps.setDate   (4, article.getDateFinEnchere());
			ps.setInt    (5, article.getPrixInitial());
			ps.setInt    (6, article.getUtilisateur().getIdUtilisateur());
			ps.setInt    (7, article.getCategorie().getIdCategorie());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			while (rs.next()){
				article.setIdArticle(rs.getInt(1));
			}

			ps.setInt    (1, article.getIdArticle());

		} catch (SQLException e){
			new Log(e.getMessage());
			throw new BuisnessException(e.getMessage(),e);
		}

		return article;
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
	public Article find(int id) throws BuisnessException {

		try{
			PreparedStatement ps = connectionProvider.getInstance().prepareStatement(FIND_BY_ID);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				article.setIdArticle(rs.getInt(1));
				article.setNomArticle(rs.getString(2));
				article.setDescription(rs.getString(3));
				article.setDateFinEnchere(rs.getDate(4));
				article.setDateFinEnchere(rs.getDate(5));
				article.setPrixInitial(rs.getInt(6));
				article.setPrixVente(rs.getInt(7));
				utilisateur.setIdUtilisateur(rs.getInt(8));
				categorie.setIdCategorie(rs.getInt(9));
				article.setCategorie(categorie);
				article.setUtilisateur(utilisateur);
			}

		} catch (SQLException e){
			new Log(e.getMessage());
			throw new BuisnessException(e.getMessage(),e);
		}
		return article;
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
