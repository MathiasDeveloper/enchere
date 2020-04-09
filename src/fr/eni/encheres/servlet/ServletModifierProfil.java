package fr.eni.encheres.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDAOImpl;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet(name="ServletModifierProfil", urlPatterns = {"/ModifierProfil"})
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur = new Utilisateur();
	private UtilisateurManager utilidateurManager = new UtilisateurManager();
       
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
		if(verifierExistenceUtilisateur(request.getParameter("id"))==false) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurInconnu.jsp").forward(request, response);
		}
		request.setAttribute("utilisateur", utilisateur);
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(verifierExistenceUtilisateur(request.getParameter("utilisateur"))==false) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurInconnu.jsp").forward(request, response);
		}
		if(verifierTelephone(request.getParameter("telephone"))==false) {
			request.setAttribute("message", "Votre numéro de téléphone doit uniquement être constitué de 10 chiffres.");
			request.setAttribute("utilisateur", utilisateur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
		}
		switch(verifierMotDePasse(request.getParameter("mdp"), request.getParameter("newmdp"), request.getParameter("confirmation"))) {
		case 1: //newmdp#confirmation
			request.setAttribute("message", "Votre mot de passe de confirmation et votre nouveau mot de passe ne correspondent pas.");
			request.setAttribute("utilisateur", utilisateur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
			break;
		case 2: //mdp incorrect
			request.setAttribute("message", "Votre mot de passe actuel est incorrect.");
			request.setAttribute("utilisateur", utilisateur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
			break;
		}
		if(verifierExistenceUtilisateur(request.getParameter("utilisateur"))==true && verifierTelephone(request.getParameter("telephone"))==true && (verifierMotDePasse(request.getParameter("mdp"), request.getParameter("newmdp"), request.getParameter("confirmation"))==0 || verifierMotDePasse(request.getParameter("mdp"), request.getParameter("newmdp"), request.getParameter("confirmation"))==3)) {
			if(verifierMotDePasse(request.getParameter("mdp"), request.getParameter("newmdp"), request.getParameter("confirmation"))==3) {
				modifierUtilisateur(request, false); //on ne change pas le mdp
			}else {
				modifierUtilisateur(request, true); //on change le mdp
			}
			try {
				utilidateurManager.update(utilisateur);
			} catch (BuisnessException e) {
				e.printStackTrace();
			}
			request.setAttribute("message", "Votre profil a été modifié avec succès.");
			request.setAttribute("utilisateur", utilisateur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
		}
	}
	
	//Méthode modifier Utilisateur
	private void modifierUtilisateur(HttpServletRequest request, boolean mdp){
		utilisateur.setPseudo(request.getParameter("pseudo"));
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setEmail(request.getParameter("email"));
		utilisateur.setTelephone(request.getParameter("telephone"));
		utilisateur.setRue(request.getParameter("rue"));
		utilisateur.setCodePostal(request.getParameter("codePostal"));
		utilisateur.setVille(request.getParameter("ville"));
		if(mdp) {
			utilisateur.setMotDePasse(request.getParameter("newmdp"));
		}
	};
	
	//Méthode vérifier Existence Utilisateur
	private boolean verifierExistenceUtilisateur(String id) {
		boolean existe=true;
		try {
			utilisateur = utilidateurManager.find(Integer.valueOf(id));
			if(utilisateur.getPseudo()==null) {
				existe=false;
			}
		} catch (NumberFormatException e) {
			existe=false;
		} catch (BuisnessException e) {
			e.printStackTrace();
			existe=false;
		}
		return existe;
	};
	
	//Méthode vérifier Téléphone
	private boolean verifierTelephone(String telephone) {
		boolean telephoneOk=true;
		if(telephone.length()==10) {
			try {
			    int telephoneNbre = Integer.parseInt(telephone);
			} catch (NumberFormatException e) {
				telephoneOk=false;
			}
		}else {
			telephoneOk=false;
		}
		return telephoneOk;
	};
	
	//Méthode vérifiant le mdp (0=ok, 1=newmdp#confirmation, 2=mdp incorrect, 3=ne pas changer le mdp)
	private int verifierMotDePasse(String mdp, String newMdp, String confirmation) {
		int mdpOk=3;
		if(!newMdp.equals("")) {
			if(mdp.equals(utilisateur.getMotDePasse())) {
				if(newMdp.equals(confirmation)) {
					mdpOk=0;
				}else {
					mdpOk=1;
				}
			}else {
				mdpOk=2;
			}
		}
		return mdpOk;
	}
}
