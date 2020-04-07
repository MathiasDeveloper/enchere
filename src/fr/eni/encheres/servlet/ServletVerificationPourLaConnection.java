package fr.eni.encheres.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Servlet Home
 */

 @WebServlet( name="HomeServlet", urlPatterns = {"/"} )
public class ServletVerificationPourLaConnection extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	String identifiant = request.getParameter("identifiant");
    	String motdepasse =  request.getParameter("motdepasse");
    	
    	
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
    	rd.forward(request, response);
    }
}
