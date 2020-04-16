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

import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.outils.BuisnessException;

/**
 * Servlet implementation class ServletListeEnchereDeconnecte
 */
@WebServlet( name="ServletListeEnchereDeconnecte", urlPatterns = {"/ListeEnchereDeconnecte"} )
public class ServletListeEnchereDeconnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EnchereManager enchereManager = EnchereManager.getInstance();
    private static CategorieManager categorieManager = CategorieManager.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListeEnchereDeconnecte() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    						
			//On donne à la jsp la liste d'enchères de base (les enchères ouvertes) 
			request.setAttribute("encheres", conditionsSelect("", -1, "encheresouvertes", 0));
			
			//On donne à la jsp toutes les catégories existantes
			request.setAttribute("categories", categorieManager.findAll());
			
			//On redirige vers la jsp
			this.getServletContext().getRequestDispatcher("/WEB-INF/listeEnchereDeconnecte.jsp").forward(request, response);
			
			
		} catch (BuisnessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
				
			//On envoit à la jsp la liste des enchères qu'il a demandé
			request.setAttribute("encheres", conditionsSelect(request.getParameter("nom"), Integer.valueOf(request.getParameter("categorie")), "encheresouvertes", 0));
			
			//On donne à la jsp toutes les catégories existantes
			request.setAttribute("categories", categorieManager.findAll());
			
			//On redirige vers la jsp
			this.getServletContext().getRequestDispatcher("/WEB-INF/listeEnchereDeconnecte.jsp").forward(request, response);
			
		} catch (BuisnessException e) {
			e.printStackTrace();
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
				condition = "AND ARTICLES.dateDebutEnchere<=" + d + " AND ARTICLES.dateFinEnchere>" + d +" AND ARTICLES.idUtilisateur<>" + idUtilisateur;
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
				condition = "AND ARTICLES.dateDebutEnchere<=" + d + " AND ARTICLES.dateFinEnchere>" + d + " AND ARTICLES.idUtilisateur=" + idUtilisateur;
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
