package fr.eni.encheres.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	private static UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
       
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
		
		//On récupère la session
		HttpSession session = request.getSession();
		
		//Si l'utilisateur n'est pas connecté, on le renvoit sur une page 404
    	if(session.getAttribute("idUtilisateur")!=null) {
    		
    		//On regarde si le paramètre id est renseigné
    		if(request.getParameter("id")!=null) {
    			
    			//On vérifie si l'utilisateur existe
				if(verifierExistenceUtilisateur(request.getParameter("id"))==false) {
					
					//Si non, on redirige vers une page indiquant que l'utilisateur est inconnu
					this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurInconnu.jsp").forward(request, response);
				}else {
					
					//On envoie l'utilisateur recherché à la jsp
					request.setAttribute("utilisateur", utilisateur);
					
					//On redirige vers la jsp
					this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
				}
			}else {
				
				//On redirige vers une page indiquant que l'utilisateur est inconnu
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurInconnu.jsp").forward(request, response);
			}
    	}else {
    		
    		//On redirige vers la page 404
    		this.getServletContext().getRequestDispatcher("/WEB-INF//erreur404.jsp").forward(request, response);
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//On récupère la session
		HttpSession session = request.getSession();
				
		//Si l'utilisateur n'est pas connecté, on le renvoit sur une page 404
		if(session.getAttribute("idUtilisateur")!=null) {
			
			//On regarde si le paramètre id est renseigné
			if(request.getParameter("id")!=null) {
				
				//0=utilisateur inconnu, 1=erreur
				int modifier=-1; 
				String message="";
				
				//On vérifie l'existence de l'utilisateur
				if(verifierExistenceUtilisateur(request.getParameter("utilisateur"))==false) {
					modifier=0;
				}
				
				//On vérifie le numéro de telephone
				if(verifierTelephone(request.getParameter("telephone"))==false && modifier==-1) {
					message="Votre numéro de téléphone doit uniquement être constitué de 10 chiffres.";
					modifier=1;
				}
				
				//On vérifie le mdp
				if(modifier==-1) {
					switch(verifierMotDePasse(request.getParameter("mdp"), request.getParameter("newmdp"), request.getParameter("confirmation"))) {
						
						//newmdp#confirmation
						case 1: 
							message="Votre mot de passe de confirmation et votre nouveau mot de passe ne correspondent pas.";
							modifier=1;
							break;
							
						//mdp incorrect
						case 2: 
							message="Votre mot de passe actuel est incorrect.";
							modifier=1;
							break;
					}
				}
				
				//On vérifie le mdp
				if(modifier==-1) {
					if(verifierMotDePasse(request.getParameter("mdp"), request.getParameter("newmdp"), request.getParameter("confirmation"))==3) {
						
						//on ne change pas le mdp
						modifierUtilisateur(request, false); 
						message="Votre profil a été modifié avec succès.";
					}else {
						
						//on change le mdp
						modifierUtilisateur(request, true); 
						message="Votre profil a été modifié avec succès.";
					}
					try {
						
						//On met à jour le profil
						utilisateurManager.update(utilisateur);
					} catch (BuisnessException e) {
						message="Une erreur inconnue s'est produite.";
						modifier=1;
						e.printStackTrace();
					}
				}
				
				//Utilisateur inconnu
				if(modifier==0) {
					
					//On redirige vers une page indiquant que l'utilisateur est inconnu
					this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurInconnu.jsp").forward(request, response);
				}else {
					
					//On envoie le message à la jsp
					request.setAttribute("message", message);
					
					//On envoie l'utilisateur à la jsp
					request.setAttribute("utilisateur", utilisateur);
					
					//On redirige vers la jsp
					this.getServletContext().getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
				}
			}else {
				
				//On redirige vers une page indiquant que l'utilisateur est inconnu
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurInconnu.jsp").forward(request, response);
			}
		}else {
		
			//On redirige vers la page 404
			this.getServletContext().getRequestDispatcher("/WEB-INF//erreur404.jsp").forward(request, response);
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
			utilisateur = utilisateurManager.find(Integer.valueOf(id));
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
