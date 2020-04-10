/**
 * 
 */
package fr.eni.encheres.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.outils.BuisnessException;
import fr.eni.encheres.outils.Log;

/**
 * Classe en charge de
 * @author Dorig
 * @version enchere - v1.0
 * @date 7 avr. 2020
 */
public class UtilisateurDAOImpl implements DAO<Utilisateur>{
	
	private static final String CREATE = "INSERT INTO UTILISATEURS"  
		+	"           				 ([pseudo],[nom],[prenom],[email] "
		+	"           				,[telephone],[rue],[codePostal],[ville],[motDePasse])"
		+	"     				VALUES"
		+	"           				(?,?,?,?,?,?,?,?,?)";
	
	private static final String FIND = "SELECT * " + 
										"FROM UTILISATEURS " +
										"WHERE idUtilisateur=?";
	private static final String UPDATE = "UPDATE UTILISATEURS " +
										"SET pseudo=?, " +
										"nom=?, " +
										"prenom=?, " +
										"email=?, " +
										"telephone=?, " +
										"rue=?, " +
										"codePostal=?, " +
										"ville=?, " +
										"motDePasse=? " +
										"WHERE idUtilisateur=?";
	private static final String DELETE = "DELETE " +
										"FROM UTILISATEURS " +
										"WHERE idUtilisateur=?";
	private static final String FIND_BY_EMAIL = "SELECT * " +
			"FROM UTILISATEURS " +
			"WHERE email=? AND motDePasse=?";

	private static final String FIND_BY_PSEUDO = "SELECT * " +
			"FROM UTILISATEURS " +
			"WHERE pseudo=? AND motDePasse=?";

	
	private static ConnectionProvider connectionProvider = new ConnectionProvider();
	private Utilisateur utilisateur = new Utilisateur();
	private Log log;

	/**
	 * {@inheritDoc}
	 * @throws BuisnessException 
	 * @see fr.eni.encheres.dal.DAO#create(java.lang.Object)
	 */
	@Override
	public void create(Utilisateur utilisateur) throws BuisnessException {
		try {
			PreparedStatement pstmt = connectionProvider.getInstance().prepareStatement(CREATE);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.executeQuery();
		} catch (SQLException e) {
			log = new Log(e.getMessage());
			BuisnessException buisnessException = new BuisnessException(e.getMessage(), e);
			throw buisnessException;
		}
	}

	/**
	 * {@inheritDoc}
	 * @throws BuisnessException 
	 * @see fr.eni.encheres.dal.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(Utilisateur utilisateur) throws BuisnessException {
		try {
			PreparedStatement pstmt = connectionProvider.getInstance().prepareStatement(UPDATE);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getIdUtilisateur());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log = new Log(e.getMessage());
			BuisnessException buisnessException = new BuisnessException(e.getMessage(), e);
			throw buisnessException;
		}
	}

	/**
	 * {@inheritDoc}
	 * @throws BuisnessException
	 * @see fr.eni.encheres.dal.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Utilisateur utilisateur) throws BuisnessException {
		try {
			PreparedStatement pstmt = connectionProvider.getInstance().prepareStatement(DELETE);
			pstmt.setInt(1, utilisateur.getIdUtilisateur());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log = new Log(e.getMessage());
			BuisnessException buisnessException = new BuisnessException(e.getMessage(), e);
			throw buisnessException;
		}
	}

	/**
	 * {@inheritDoc}
	 * @throws BuisnessException 
	 * @see fr.eni.encheres.dal.DAO#find(int)
	 */
	@Override
	public Utilisateur find(int id) throws BuisnessException {
			try {
				PreparedStatement pstmt = connectionProvider.getInstance().prepareStatement(FIND);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
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
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log = new Log(e.getMessage());
				BuisnessException buisnessException = new BuisnessException(e.getMessage(), e);
				throw buisnessException;
			}
		return utilisateur;
	}

	/**
	 * Retourne tout les utilisateurs
	 *
	 * @return ArrayList Utilisateur
	 */
	@Override
	public ArrayList<Utilisateur> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean verifier(Utilisateur utilisateur) throws BuisnessException {
		boolean existe = false;

		if(utilisateur.getEmail() != null) {
			try {
				PreparedStatement pstmt = connectionProvider.getInstance().prepareStatement(FIND_BY_EMAIL);
				pstmt.setString(1, utilisateur.getEmail());
				pstmt.setString(2, utilisateur.getMotDePasse());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
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
					existe = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log = new Log(e.getMessage());
				BuisnessException buisnessException = new BuisnessException(e.getMessage(), e);
				throw buisnessException;
			}
		}else {
			try {
				PreparedStatement pstmt = connectionProvider.getInstance().prepareStatement(FIND_BY_PSEUDO);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getMotDePasse());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
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
					existe = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log = new Log(e.getMessage());
				BuisnessException buisnessException = new BuisnessException(e.getMessage(), e);
				throw buisnessException;
			}
		}

		return existe;
	}
}
