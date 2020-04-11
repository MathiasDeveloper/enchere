package fr.eni.encheres.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Home
 */

 @WebServlet( name="HomeServlet", urlPatterns = {"/Home"} )
public class HomeServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	HttpSession session = request.getSession();
    	if(session.getAttribute("idUtilisateur")!=null) {
    		this.getServletContext().getRequestDispatcher("/ListeEnchereConnecte").forward(request, response);
    	}else {
    		this.getServletContext().getRequestDispatcher("/ListeEnchereDeconnecter").forward(request, response);
    	}
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
