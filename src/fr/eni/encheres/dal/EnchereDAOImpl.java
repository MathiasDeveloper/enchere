/**
 * 
 */
package fr.eni.encheres.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.outils.BuisnessException;
import fr.eni.encheres.outils.Log;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class EnchereDAOImpl implements DAO<Enchere>{
	
	private static final String FINDALL = "SELECT * " + 
										"FROM ENCHERES " + 
										"JOIN UTILISATEURS ON ENCHERES.idUtilisateur=UTILISATEURS.idUtilisateur " + 
										"JOIN ARTICLES ON ENCHERES.idArticle=ARTICLES.idArticle " + 
										"JOIN CATEGORIES ON ARTICLES.idCategorie=CATEGORIES.idCategorie";
	private static ConnectionProvider connectionProvider = new ConnectionProvider();
	private Log log;

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#create(java.lang.Object)
	 */
	@Override
	public void create(Enchere enchere) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(Enchere enchere) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Enchere enchere) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#find(int)
	 */
	@Override
	public Enchere find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @throws BuisnessException 
	 * @see fr.eni.encheres.dal.DAO#findAll(java.lang.Object)
	 */
	@Override
	public ArrayList<Enchere> findAll() throws BuisnessException {
		ArrayList<Enchere> encheres = new ArrayList<Enchere>();
		try {
			PreparedStatement pstmt = connectionProvider.getInstance().prepareStatement(FINDALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				Categorie categorie = new Categorie();
				Article article = new Article();
				Enchere enchere = new Enchere();
				utilisateur.setIdUtilisateur(rs.getInt("idUtilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("codePostal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("motDePasse"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
				categorie.setIdCategorie(rs.getInt("idCategorie"));
				categorie.setLibelle(rs.getString("libelle"));
				article.setCategorie(categorie);
				article.setDateDebutEnchere(rs.getDate("dateDebutEnchere"));
				article.setDateFinEnchere(rs.getDate("dateFinEnchere"));
				article.setDescription(rs.getString("description"));
				article.setIdArticle(rs.getInt("idArticle"));
				article.setNomArticle(rs.getString("nomArticle"));
				article.setPrixInitial(rs.getInt("prixInitial"));
				article.setPrixVente(rs.getInt("prixVente"));
				enchere.setArticle(article);
				enchere.setDateEnchere(rs.getDate("dateEnchere"));
				enchere.setHeureDebutEnchere(rs.getTime("heureDebutEnchere"));
				enchere.setHeureFinEnchere(rs.getTime("heureFinEnchere"));
				enchere.setMontantEnchere(rs.getInt("montantEnchere"));
				encheres.add(enchere);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log = new Log(e.getMessage());
			BuisnessException buisnessException = new BuisnessException(e.getMessage(), e);
			throw buisnessException;
		}
		return encheres;
	}




}
