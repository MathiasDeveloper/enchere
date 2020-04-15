package fr.eni.encheres.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Servlet implementation class ServletListeEnchereConnecte
 */
@WebServlet( name="ServletListeEnchereConnecte", urlPatterns = {"/ListeEnchereConnecte"} )
public class ServletListeEnchereConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EnchereManager enchereManager = EnchereManager.getInstance();
    private static CategorieManager categorieManager = CategorieManager.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListeEnchereConnecte() {
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
    		try {
    			
    			//On donne à la jsp l'id de l'utilisateur
				request.setAttribute("utilisateur", session.getAttribute("idUtilisateur"));
				
				//On donne à la jsp la liste d'enchères de base (les enchères ouvertes) 
				request.setAttribute("encheres", conditionsSelect("", -1, "encheresouvertes", (int) session.getAttribute("idUtilisateur")));
				
				//On donne à la jsp toutes les catégories existantes
				request.setAttribute("categories", categorieManager.findAll());
				
				//On redirige vers la jsp
				this.getServletContext().getRequestDispatcher("/WEB-INF/listeEnchereConnecte.jsp").forward(request, response);
				
				
			} catch (BuisnessException e) {
				e.printStackTrace();
			}
    	}else {
    		
    		//On redirige vers la page 404
    		this.getServletContext().getRequestDispatcher("/WEB-INF/erreur404.jsp").forward(request, response);
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
    		try {
    			
    			//Si on a le résultat des deux checkboxs, on envoit un message d'erreur
    			if(request.getParameter("encheres")!=null && request.getParameter("ventes")!=null) {
    				
    				//Message d'erreur
    				String message="Vous ne pouves pas cocher les cases des Achats et des ventes en même temps.";
    				
    				//On donne à la jsp le message d'erreur
    				request.setAttribute("message", message);
    				
    			}else{
    				
    				String checkbox="";
    				
    				//On regarde quel checkbox l'utilisateur a coché
    				if(request.getParameter("encheres")!=null) {
    					
    					//Si l'utilisateur à coché une checkbox des enchères
    					checkbox="encheres";
    				}else {
    					
    					//Si l'utilisateur à coché une checkbox des ventes
    					checkbox="ventes";
    				}
    				
    				//On envoit à la jsp la liste des enchères qu'il a demandé
    				request.setAttribute("encheres", conditionsSelect(request.getParameter("nom"), Integer.valueOf(request.getParameter("categorie")), request.getParameter(checkbox), (int) session.getAttribute("idUtilisateur")));
    			}
    			
    			//On donne à la jsp l'id de l'utilisateur
				request.setAttribute("utilisateur", session.getAttribute("idUtilisateur"));
				
				//On donne à la jsp toutes les catégories existantes
				request.setAttribute("categories", categorieManager.findAll());
				
				//On redirige vers la jsp
				this.getServletContext().getRequestDispatcher("/WEB-INF/listeEnchereConnecte.jsp").forward(request, response);
				
			} catch (BuisnessException e) {
				e.printStackTrace();
			}
    	}else {
    		
    		//On redirige vers la page 404
    		this.getServletContext().getRequestDispatcher("/WEB-INF/erreur404.jsp").forward(request, response);
    	}
	}
	
	private ArrayList<Enchere> conditionsSelect(String name, int categorie, String checkbox, int idUtilisateur) throws BuisnessException{
		
		ArrayList<Enchere> encheres = null;
		String condition = "";
		
		//On récupère la date du jour et on la formate
		Date date = new Date();
		String sFormat = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(sFormat); 
		String d = "'" + sdf.format(date) + "'";
		
		//On créer la condition suivant la checkbox cochée
		switch(checkbox) {
		
			//Cas des enchères ouvertes
			case "encheresouvertes":
				condition = "AND ARTICLES.dateDebutEnchere<" + d + " AND ARTICLES.dateFinEnchere>" + d +" AND ARTICLES.idUtilisateur<>" + idUtilisateur;
				encheres = enchereManager.findByCondition(name, categorie, condition);
				break;
				
			//Cas des enchères en cours
			case "encheresencours":
				condition = "AND ARTICLES.dateDebutEnchere<" + d + " AND ARTICLES.dateFinEnchere>" + d +" AND ENCHERES.idUtilisateur=" + idUtilisateur;
				encheres = enchereManager.findByCondition(name, categorie, condition);
				break;
				
			//Cas des enchères remportées
			case "encheresremportees":
				condition = "AND ARTICLES.dateFinEnchere<" + d +" AND ENCHERES.idUtilisateur=" + idUtilisateur;
				encheres = enchereManager.findByCondition(name, categorie, condition);
				break;
				
			//Cas des ventes en cours
			case "ventesencours":
				condition = "AND ARTICLES.dateDebutEnchere<" + d + " AND ARTICLES.dateFinEnchere>" + d + " AND ARTICLES.idUtilisateur=" + idUtilisateur;
				encheres = enchereManager.findByCondition(name, categorie, condition);
				break;
				
			//Cas des ventes non débutées
			case "ventesnondebutees":
				condition = "AND ARTICLES.dateDebutEnchere>" + d + " AND ARTICLES.idUtilisateur=" + idUtilisateur;
				encheres = enchereManager.findByCondition(name, categorie, condition);
				break;
				
			//Cas des ventes terminées
			case "ventesterminees":
				condition = "AND ARTICLES.dateFinEnchere<" + d + " AND ARTICLES.idUtilisateur=" + idUtilisateur;
				encheres = enchereManager.findByCondition(name, categorie, condition);
				break;
		}
		return encheres;
	}

}
