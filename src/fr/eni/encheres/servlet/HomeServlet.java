package fr.eni.encheres.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

import java.io.IOException;

/**
 * Servlet Home
 */

 @WebServlet( name="HomeServlet", urlPatterns = {"/Home"} )
public class HomeServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    	boolean connecte=false;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for(Cookie unCookie:cookies)
			{
				if(unCookie.getName().equals("idUtilisateur")) {
					connecte=true;
					this.getServletContext().getRequestDispatcher("/ListeEnchereConnecte").forward(request, response);
				}
			}
			if(connecte==false) {
				this.getServletContext().getRequestDispatcher("/seConnecter").forward(request, response);
			}
		}
    }
}
