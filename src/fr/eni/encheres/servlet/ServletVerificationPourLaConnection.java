package fr.eni.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.UtilisateurDAOJdbcImpl;

/**
 * Servlet Home
 */

 @WebServlet( name="ServletVerificationPourLaConnection", urlPatterns = {"/"} )
public class ServletVerificationPourLaConnection extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	String identifiant = request.getParameter("identifiant");
    	String motdepasse =  request.getParameter("motdepasse");
    	
    	request.setAttribute("identifiant", identifiant);
    	request.setAttribute("motdepasse", motdepasse);
    	
    	Utilisateur utilisateur = new Utilisateur(identifiant,motdepasse);
    	UtilisateurDAO utilisateurDAO = new UtilisateurDAOJdbcImpl();  
    	utilisateurDAO.verifier(utilisateur);
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/validation.jsp");
    	rd.forward(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/formulaire.jsp");
    	rd.forward(request, response);
    }
}
