package fr.eni.encheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDAOImpl;
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
		
		boolean existeEnBase = false;
		int idUtilisateur = 0;
		
		//Verification de similitude entre les mots de passe
		if(motdepasse.equals(confirmation)) {
			Utilisateur utilisateur = new Utilisateur(pseudo,nom,prenom,email,tel,rue,cp,ville,motdepasse);
        	utilisateurManager.create(utilisateur);
        	
        	Utilisateur utilisateur2 = new Utilisateur(pseudo,motdepasse,false);
        	
        	try {
				existeEnBase = utilisateurManager.verifier(utilisateur2);
			} catch (BuisnessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	idUtilisateur = utilisateur.getIdUtilisateur();

			//Si ajouter, alors on ajoute le cookie et on envoyer vers page accueil
			PrintWriter out = response.getWriter();
    		Cookie[] cookies = request.getCookies();

    		if(cookies==null || cookies.length > 0){

   				Cookie unCookie = new Cookie("idUtilisateur", String.valueOf(idUtilisateur));
    				unCookie.setMaxAge(300);
    				response.addCookie(unCookie);
    		}else{
    			out.println("Il existe déjà un cookie pour l'utilisateur avec cet ID");
    			for(Cookie unCookie:cookies)
    			{
    				out.println(unCookie.getName()+"="+unCookie.getValue());
    			}
    		}

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/validationInscription.jsp");
			rd.forward(request, response);
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