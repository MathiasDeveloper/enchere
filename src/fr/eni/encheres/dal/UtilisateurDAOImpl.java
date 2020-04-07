/**
 * 
 */
package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class UtilisateurDAOImpl implements DAO<Utilisateur>{
	
	private static ConnectionProvider connectionProvider = new ConnectionProvider();

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#create(java.lang.Object)
	 */
	@Override
	public Utilisateur create(Utilisateur objet) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#update(java.lang.Object)
	 */
	@Override
	public Utilisateur update(Utilisateur objet) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#delete(java.lang.Object)
	 */
	@Override
	public Utilisateur delete(Utilisateur objet) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#find(int)
	 */
	@Override
	public Utilisateur find(int id) {
		String REQUETE = "SELECT * " + 
				"FROM UTILISATEURS " +
				"WHERE no_utilisateur=?";
		Utilisateur utilisateur = new Utilisateur();
			try {
				PreparedStatement pstmt = connectionProvider.getInstance().prepareStatement(REQUETE);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return utilisateur;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.encheres.dal.DAO#findAll(java.lang.Object)
	 */
	@Override
	public ArrayList<Utilisateur> findAll(Utilisateur objet) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
