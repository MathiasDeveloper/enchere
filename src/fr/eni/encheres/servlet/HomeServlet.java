package fr.eni.encheres.servlet;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.UtilisateurDAOImpl;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Servlet Home
 */

 @WebServlet( name="HomeServlet", urlPatterns = {"/home"} )
public class HomeServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	    	
    }
}
