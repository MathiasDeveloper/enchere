package fr.eni.encheres.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.outils.BuisnessException;
import fr.eni.encheres.outils.Log;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class CategorieDAOImpl implements DAO<Categorie>{

	/**
	 * Request find all
	 *
	 * @string
	 */
	private static final String FIND_ALL = "SELECT idCategorie,libelle FROM CATEGORIES";


	private static final String FIND_BY_ID = "SELECT `idCategorie`," +
			" `libelle`" +
			" FROM `CATEGORIES` " +
			"WHERE `idCategorie` = ?";

	private  Categorie categorie = new Categorie();

	/**
	 * Connection provider
	 */
	private static ConnectionProvider connectionProvider = new ConnectionProvider();

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#create(java.lang.Object)
	 */
	@Override
	public void create(Categorie categorie) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(Categorie categorie) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Categorie categorie) {
		// TODO Auto-generated method stubvoid
	}

	/**
	 * Retourne la catégorie de l'article associer via l'id categorie
	 *
	 * @param id
	 * @return Categorie
	 */
	@Override
	public Categorie find(int id) throws BuisnessException {
		try {
			PreparedStatement ps = connectionProvider.getInstance().prepareStatement(FIND_BY_ID);
			ps.setInt(1 , id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()){
				categorie.setIdCategorie(rs.getInt(1));
				categorie.setLibelle(rs.getString(2));
			}

		} catch (SQLException e){
			new Log(e.getMessage());
			throw new BuisnessException(e.getMessage(), e);
		}

		return categorie;
	}

	/**
	 *
	 * @return Categories
	 * @throws BuisnessException
	 */
	@Override
	public ArrayList<Categorie> findAll() throws BuisnessException {
		ArrayList<Categorie> categories = new ArrayList<Categorie>();
		PreparedStatement pstmt = null;

		try {
			pstmt = connectionProvider.getInstance().prepareStatement(FIND_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				Categorie categorie = new Categorie();
				categorie.setIdCategorie(rs.getInt(1));
				categorie.setLibelle(rs.getString(2));
				categories.add(categorie);
			}
		} catch (SQLException e) {
			new Log(e.getMessage());
			throw new BuisnessException(e.getMessage(),e);
		}

		return categories;
	}




}
