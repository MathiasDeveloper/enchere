package fr.eni.encheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDAOImpl;

/**
 * Servlet Home
 */

 @WebServlet( name="ServletVerificationPourLaConnection", urlPatterns = {"/seConnecter"} )
public class ServletVerificationPourLaConnection extends javax.servlet.http.HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
        	UtilisateurDAOImpl utilisateurDAOImpl = new UtilisateurDAOImpl();  
        	existeEnBase = utilisateurDAOImpl.verifier(utilisateur);
        	idUtilisateur = utilisateur.getidUtilisateur();
    	}else {
    		Utilisateur utilisateur = new Utilisateur(identifiant,motdepasse,false);
    		UtilisateurDAOImpl utilisateurDAOImpl = new UtilisateurDAOImpl();  
    		existeEnBase = utilisateurDAOImpl.verifier(utilisateur);   	
    		idUtilisateur = utilisateur.getidUtilisateur();
    	}
    	
    	if(existeEnBase == true) {
    		PrintWriter out = response.getWriter();
    		Cookie[] cookies = request.getCookies();
    		    		
    		if(cookies==null || cookies.length == 1){
    						
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
    		
    		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/validationSeConnecter.jsp");
        	rd.forward(request, response);
    	}else {
    		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/erreurSeConnecter.jsp");
        	rd.forward(request, response);
    	}
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	
    	RequestDispatcher rd = request.getRequestDispatcher("formulaireSeConnecter.jsp");
    	rd.forward(request, response);
    }
}