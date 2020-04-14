/**
 * 
 */
package fr.eni.encheres.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import fr.eni.encheres.bo.Article;
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

	/**
	 * REQUETE insertion en bdd pour une enchere
	 *
	 * @string
	 */
	private static final String INSERT_INTO = "INSERT INTO `ENCHERES` (" +
			"`idUtilisateur`, " +
			"`idArticle`, " +
			"`dateEnchere`, " +
			"`montantEnchere`, " +
			"`heureDebutEnchere`, " +
			"`heureFinEnchere`) " +
			"VALUES (?, ?, ?, ?, ?, ?);";

	/**
	 * REQUETE récupération de l'enchere en bdd
	 *
	 * @string
	 */
	private static final String FIND_BY_ID = "SELECT " +
			"`idUtilisateur`," +
			" `dateEnchere`, " +
			"`montantEnchere`, " +
			"`heureDebutEnchere`, " +
			"`heureFinEnchere` " +
			"FROM `ENCHERES` " +
			"WHERE `idArticle` = ?";

	private ConnectionProvider connectionProvider = new ConnectionProvider();

	private Enchere enchere = new Enchere();

	private Utilisateur utilisateur = new Utilisateur();

	private Article article = new Article();

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#create(java.lang.Object)
	 */
	@Override
	public void create(Enchere enchere) throws BuisnessException {
		try {
			PreparedStatement ps = connectionProvider.getInstance().prepareStatement(INSERT_INTO);

			ps.setInt(1, enchere.getUtilisateur().getIdUtilisateur());
			ps.setInt(2, enchere.getArticle().getIdArticle());
			ps.setDate(3, enchere.getDateEnchere());
			ps.setInt(4, enchere.getMontantEnchere());
			ps.setTime(5, enchere.getHeureDebutEnchere());
			ps.setTime(6, enchere.getHeureFinEnchere());

			ps.executeUpdate();

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
	public Enchere find(int id) throws BuisnessException {

		try {
			PreparedStatement ps = connectionProvider.getInstance().prepareStatement(FIND_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				utilisateur.setIdUtilisateur(rs.getInt(1));
				enchere.setUtilisateur(utilisateur);
				enchere.setDateEnchere(rs.getDate(2));
				enchere.setMontantEnchere(rs.getInt(3));
				enchere.setHeureDebutEnchere(rs.getTime(4));
				enchere.setHeureFinEnchere(rs.getTime(5));
			}

		} catch (SQLException e){
			new Log(e.getMessage());
			throw new BuisnessException(e.getMessage(), e);
		}
		return enchere;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#findAll(java.lang.Object)
	 */
	@Override
	public ArrayList<Enchere> findAll() {
		// TODO Auto-generated method stub
		return null;
	}




}
