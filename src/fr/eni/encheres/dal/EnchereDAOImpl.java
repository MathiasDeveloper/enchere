/**
 * 
 */
package fr.eni.encheres.dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.outils.BuisnessException;
import fr.eni.encheres.outils.Log;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class EnchereDAOImpl implements DAO<Enchere>{

	private static final String INSERT_INTO = "INSERT INTO `ENCHERES` (" +
			"`idUtilisateur`, " +
			"`idArticle`, " +
			"`dateEnchere`, " +
			"`montantEnchere`, " +
			"`heureDebutEnchere`, " +
			"`heureFinEnchere`) " +
			"VALUES (?, ?, ?, ?, ?, ?);";

	private ConnectionProvider connectionProvider = new ConnectionProvider();

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
	public Enchere find(int id) {
		// TODO Auto-generated method stub
		return null;
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
