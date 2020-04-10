package fr.eni.encheres.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Servlet implementation class ServletListeEnchereConnecte
 */
@WebServlet( name="ServletListeEnchereConnecte", urlPatterns = {"/ListeEnchereConnecte"} )
public class ServletListeEnchereConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EnchereManager enchereManager = EnchereManager.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListeEnchereConnecte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean connecte=false;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for(Cookie unCookie:cookies)
			{
				if(unCookie.getName().equals("idUtilisateur")) {
					connecte=true;
					try {
						System.out.println(enchereManager.findAll());
						request.setAttribute("encheres", enchereManager.findAll());
						this.getServletContext().getRequestDispatcher("/WEB-INF/listeEnchereConnecte.jsp").forward(request, response);
					} catch (BuisnessException e) {
						e.printStackTrace();
					}
				}
			}
			if(connecte==false) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/erreur404.jsp").forward(request, response);
			}
		}else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/erreur404.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
