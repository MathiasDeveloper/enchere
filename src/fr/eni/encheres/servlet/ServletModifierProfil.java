package fr.eni.encheres.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDAOImpl;

/**
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet(name="ServletModifierProfil", urlPatterns = {"/ModifierProfil"})
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur = new Utilisateur();
	private UtilisateurDAOImpl utilisateurDAOImpl = new UtilisateurDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifierProfil() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getParameter("id").equals(null);
		}catch(Exception e) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurInconnu.jsp").forward(request, response);
		}
		utilisateur = utilisateurDAOImpl.find(Integer.valueOf(request.getParameter("id")));
		if(utilisateur.getPseudo().equals("")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurInconnu.jsp").forward(request, response);
		}
		request.setAttribute("utilisateur", utilisateur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		utilisateur = utilisateurDAOImpl.find(Integer.valueOf(request.getParameter("utilisateur")));
		utilisateur.setPseudo(request.getParameter("pseudo"));
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setEmail(request.getParameter("email"));
		utilisateur.setTelephone(request.getParameter("telephone"));
		utilisateur.setRue(request.getParameter("rue"));
		utilisateur.setCodePostal(request.getParameter("codePostal"));
		utilisateur.setVille(request.getParameter("ville"));
		if(!request.getParameter("newmdp").equals("")) {
			if(request.getParameter("mdp").equals(utilisateur.getMotDePasse())) {
				if(request.getParameter("newmdp").equals(request.getParameter("confirmation"))) {
					utilisateur.setMotDePasse(request.getParameter("newmdp"));
				}else {
					request.setAttribute("message", "Votre mot de passe de confirmation et votre nouveau mot de passe ne correspondent pas.");
					request.setAttribute("utilisateur", utilisateur);
					this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
				}
			}else {
				request.setAttribute("message", "Votre mot de passe actuel est incorrect.");
				request.setAttribute("utilisateur", utilisateur);
				this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
			}
		}
		UtilisateurDAOImpl utilisateurDAOImpl = new UtilisateurDAOImpl();
		utilisateurDAOImpl.update(utilisateur);
		request.setAttribute("message", "Votre profil a été modifié avec succès.");
		request.setAttribute("utilisateur", utilisateur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
	}

}
