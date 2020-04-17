package fr.eni.encheres.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Servlet implementation class ServletSupprimerCompte
 */
@WebServlet(name="ServletSupprimerCompte", urlPatterns = {"/SupprimerCompte"})
public class ServletSupprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSupprimerCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//On récupère la session
		HttpSession session = request.getSession();
		
		//Si l'utilisateur n'est pas connecté, on le renvoit sur une page 404
    	if(session.getAttribute("idUtilisateur")!=null) {
    		
    		//On regarde si le paramètre id est renseigné
    		if(request.getParameter("id")!=null) {
				try {
					
					//On supprime l'utilisateur
					utilisateurManager.delete(utilisateurManager.find(Integer.valueOf(request.getParameter("id"))));
					
					//On supprime la session
					session.invalidate();
					
					//On redirige vers la page d'accueil
					this.getServletContext().getRequestDispatcher("/Home").forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (BuisnessException e) {
					e.printStackTrace();
				}
	    	}else {
	    		
	    		//On redirige vers la page 404
	    		this.getServletContext().getRequestDispatcher("/WEB-INF//erreur404.jsp").forward(request, response);
	    	}
    	}else {
    		
    		//On redirige vers la page 404
    		this.getServletContext().getRequestDispatcher("/WEB-INF//erreur404.jsp").forward(request, response);
    	}
	}
}
