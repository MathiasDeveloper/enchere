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

 @WebServlet( name="ServletSeDeconnecter", urlPatterns = {"/seDeconnecter"} )
public class ServletSeDeconnecter extends javax.servlet.http.HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

	protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	// Recupere la session
    	HttpSession session = request.getSession(true);
    	
    	if(request.getParameter("idUtilisateur") != null ){  
            session.invalidate();
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/listeEnchereConnecte.jsp");
        	rd.forward(request, response);
        }
    	
    
    }
}