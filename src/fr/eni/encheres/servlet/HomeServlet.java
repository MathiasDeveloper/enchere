package fr.eni.encheres.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.outils.BuisnessException;

import java.io.IOException;

/**
 * Servlet Home
 */

 @WebServlet( name="HomeServlet", urlPatterns = {"/Home"} )
public class HomeServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	
    	//On récupère la session
    	HttpSession session = request.getSession();
    	
    	//Si l'utilisateur n'est pas connecté, on le renvoit sur la liste des enchères en mode déconnecté, sinon sur la liste des enchères en mode connecté
    	if(session.getAttribute("idUtilisateur")!=null) {
    		
    		//On redirige vers la jsp
    		this.getServletContext().getRequestDispatcher("/ListeEnchereConnecte").forward(request, response);
    	}else {
    		
    		//On redirige vers la jsp
    		this.getServletContext().getRequestDispatcher("/ListeEnchereDeconnecte").forward(request, response);
    	}
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
