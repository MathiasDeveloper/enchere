package fr.eni.encheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDAOImpl;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Servlet Home
 */

@WebServlet( name="ServletMotDePasseOublie", urlPatterns = {"/motDePasseOublie"} )
public class ServletMotDePasseOublie extends javax.servlet.http.HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

	protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
		String email = request.getParameter("email");
		boolean existeEnBase = false;

		Utilisateur utilisateur = new Utilisateur(email);
		try {
			existeEnBase = utilisateurManager.searchEmail(utilisateur);
		} catch (BuisnessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	


		if(existeEnBase == true) {
			request.setAttribute("email", email);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/formulaireChangementMotDePasse.jsp");
			rd.forward(request, response);
		}else {
			System.out.println(existeEnBase);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/erreurFormulaireMotDePasseOublie.jsp");
			rd.forward(request, response);
		}
	}

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/formulaireMotDePasseOublie.jsp");
		rd.forward(request, response);
	}
}