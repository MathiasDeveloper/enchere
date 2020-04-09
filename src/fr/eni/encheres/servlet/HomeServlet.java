package fr.eni.encheres.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;

/**
 * Servlet Home
 */

 @WebServlet( name="HomeServlet", urlPatterns = {"/"} )
public class HomeServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
	request.setAttribute("login", "BAILLE");
		
		String url  = request.getContextPath();
		System.out.println(url);
		
		RequestDispatcher rd = request.
				getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
    }
}
