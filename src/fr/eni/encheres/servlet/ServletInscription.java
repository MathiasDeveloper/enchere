package fr.eni.encheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Servlet Home
 */

@WebServlet( name="ServletInscription", urlPatterns = {"/Inscription"} )
public class ServletInscription extends javax.servlet.http.HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

	protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String pseudo       = request.getParameter("pseudo");
		String nom          = request.getParameter("nom");
		String prenom       = request.getParameter("prenom");
		String email        = request.getParameter("email");
		String tel          = request.getParameter("tel");
		String rue          = request.getParameter("rue");
		String cp           = request.getParameter("cp");
		String ville        = request.getParameter("ville");
		String motdepasse   = request.getParameter("motdepasse");
		String confirmation = request.getParameter("confirmation");

		boolean ajouteEnBase = false;
		boolean existeEnBase = false;
		int idUtilisateur = 0;

		//Verification de similitude entre les mots de passe
		if(motdepasse.equals(confirmation)) {
			Utilisateur utilisateur = new Utilisateur(pseudo,nom,prenom,email,tel,rue,cp,ville,motdepasse);
			try {
				ajouteEnBase = utilisateurManager.ajout(utilisateur);
			} catch (BuisnessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (ajouteEnBase) {

				//Si ajouter, alors on ajoute la session et on renvoie vers page accueil
			
				HttpSession session = request.getSession();
				
				Utilisateur utilisateur2 = new Utilisateur(email,motdepasse,true);
	        	try {
	        		existeEnBase = utilisateurManager.verifier(utilisateur);
				} catch (BuisnessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        	
	        	if(existeEnBase) {
	        		idUtilisateur = utilisateur.getIdUtilisateur();

					session.setAttribute("idUtilisateur", idUtilisateur);

					request.setAttribute("pseudo", pseudo);
					request.setAttribute("motdepasse", motdepasse);
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/validationInscription.jsp");
					rd.forward(request, response);
	        	}else {
	        		out.println("Il y a eu un problème lors de l'ajout en base");
	        	}
	        	
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/erreurAjoutInscription.jsp");
				rd.forward(request, response);
			}		
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/erreurInscription.jsp");
			rd.forward(request, response);
		}
	}

	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/formulaireInscription.jsp");
		rd.forward(request, response);
	}
}