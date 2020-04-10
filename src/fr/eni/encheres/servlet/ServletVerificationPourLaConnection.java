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

 @WebServlet( name="ServletVerificationPourLaConnection", urlPatterns = {"/seConnecter"} )
public class ServletVerificationPourLaConnection extends javax.servlet.http.HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

	protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	String identifiant = request.getParameter("identifiant");
    	String motdepasse =  request.getParameter("motdepasse");
    	boolean existeEnBase = false;
    	int idUtilisateur = 0;
    	
    	request.setAttribute("identifiant", identifiant);
    	request.setAttribute("motdepasse", motdepasse);
    	
    	if(identifiant.contains("@")) {
    		//true = correspond à un email
    		Utilisateur utilisateur = new Utilisateur(identifiant,motdepasse,true);
        	try {
				existeEnBase = utilisateurManager.verifier(utilisateur);
			} catch (BuisnessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	idUtilisateur = utilisateur.getIdUtilisateur();
    	}else {
    		Utilisateur utilisateur = new Utilisateur(identifiant,motdepasse,false); 
    		try {
				existeEnBase = utilisateurManager.verifier(utilisateur);
			} catch (BuisnessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   	
    		idUtilisateur = utilisateur.getIdUtilisateur();
    	}
    	
    	if(existeEnBase == true) {
    		PrintWriter out = response.getWriter();
    		Cookie[] cookies = request.getCookies();
    		    		
    		if(cookies == null || cookies.length > 0){
   				Cookie unCookie = new Cookie("idUtilisateur", String.valueOf(idUtilisateur));
    				response.addCookie(unCookie);
    		} else{
    			out.println("Il existe déjà un cookie pour l'utilisateur avec cet ID");
    			for(Cookie unCookie:cookies)
    			{
    				out.println(unCookie.getName()+"="+unCookie.getValue());
    			}
    		}
    		
    		RequestDispatcher rd = request.getRequestDispatcher("/Home");
        	rd.forward(request, response);
    	}else {
    		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/erreurSeConnecter.jsp");
        	rd.forward(request, response);
    	}
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/formulaireSeConnecter.jsp");
    	rd.forward(request, response);
    }
}