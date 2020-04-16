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

@WebServlet( name="ServletChangementMotDePasse", urlPatterns = {"/changementMotDePasse"} )
public class ServletChangementMotDePasse extends javax.servlet.http.HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

	protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
		String motDePasse   = request.getParameter("motdepasse");
		String confirmation = request.getParameter("confirmation");
		
		boolean updateDone = false;
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("emailFin");
			
		
		if(motDePasse.equals(confirmation)) {
			Utilisateur utilisateur = new Utilisateur(email, motDePasse);
			try {
				updateDone = utilisateurManager.updatePassword(utilisateur);
			} catch (BuisnessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			if(updateDone) {
				session.invalidate();
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/confirmationChangementMDP.jsp");
				rd.forward(request, response);
			}else {
				//erreurupdateMDP - plus de session email
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/erreurUpdateMDP.jsp");
				rd.forward(request, response);
			}
		}else {
			System.out.println("non");
			// retourne mot de passe différents
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/erreurChangementMDP.jsp");
			rd.forward(request, response);
		}
		
		
	}

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
		
		doPost(request, response);
	}
}