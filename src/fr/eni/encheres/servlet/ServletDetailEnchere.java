package fr.eni.encheres.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.outils.BuisnessException;
import fr.eni.encheres.outils.Utils;

/**
 * Servlet implementation class ServletDetailEnchere
 */
@WebServlet(name = "/ServletDetailEnchere", urlPatterns = { "/DetailEnchere" })
public class ServletDetailEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EnchereManager enchereManager = EnchereManager.getInstance();

	private ArticleManager articleManager = ArticleManager.getInstance();

	private CategorieManager categorieManager = CategorieManager.getInstance();

	private UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

	private Article article = new Article();

	private Utilisateur utilisateur = new Utilisateur();

	private Categorie categorie = new Categorie();

	private Enchere enchere = new Enchere();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletDetailEnchere() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * try {
		 * 
		 * article =
		 * this.getObjectArticleFromUrl(Utils.transformStringToInt(request.getParameter(
		 * "idArticle"))); enchere =
		 * this.getObjectEnchereFromUrl(article.getIdArticle()); utilisateur =
		 * this.getObjectUtilisateurFromUrl(article.getUtilisateur().getIdUtilisateur())
		 * ; categorie =
		 * this.getObjectCategorieFromUrl(article.getCategorie().getIdCategorie()); }
		 * catch (BuisnessException e) { e.printStackTrace(); }
		 * 
		 * System.out.println(enchere.toString());
		 * System.out.println(article.toString());
		 * System.out.println(utilisateur.toString());
		 * System.out.println(categorie.toString());
		 * 
		 * this.getServletContext().getRequestDispatcher("/WEB-INF/detailEnchere.jsp").
		 * forward(request,response);
		 * response.getWriter().append("Served at: ").append(request.getContextPath());
		 * }
		 */

		try {

			
			// Check si l'utilisateur existe en Session
			this.checkUtilisateurSession(request.getSession(), request);

			if (request.getParameter("id") != null || request.getParameter("idArticle") != null) {

				// recupere l'id de l'url
				int id = this.getIdParamrUrl(request);

				// Créer l'article
				article = this.createArticleFromId(id);
				
				RetraitManager retraitManager = new RetraitManager();
				
				Retrait retrait = retraitManager.find(article.getIdArticle());
				
				Enchere enchere = enchereManager.find(article.getIdArticle());

				// Check si l'article est encore en enchere ou non
				this.checkDateIsLate(article, request);

				// Construit les objets nécessaire à partir de la requete
				request.setAttribute("enchere", enchere);
				request.setAttribute("retrait", retrait);
				request.setAttribute("article", article);
				request.setAttribute("utilisateur", utilisateur);
				//request.setAttribute("retrait", retrait);

				this.getServletContext().getRequestDispatcher("/WEB-INF/detailEnchere.jsp").forward(request, response);
				response.getWriter().append("Served at: ").append(request.getContextPath());
				System.out.println(enchere.toString());
				System.out.println(article.toString());
				System.out.println(utilisateur.toString());
				System.out.println(categorie.toString());
			} else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/erreur404.jsp").forward(request, response);
			}

		} catch (BuisnessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Retourne l'objet Article à partir de l'url
	 *
	 * @param id
	 * @return Article
	 * @throws BuisnessException
	 */
	private Article getObjectArticleFromUrl(int id) throws BuisnessException {
		return articleManager.find(id);
	}

	/**
	 * Méthode de vérification si l'utilisateur est présent en session
	 *
	 * @param session
	 */
	private void checkUtilisateurSession(HttpSession session, HttpServletRequest request) throws BuisnessException {
		boolean isValid = false;

		String message;

		if (session.getAttribute("idUtilisateur") != null) {
			isValid = true;
		}

		if (isValid) {
			utilisateur = getObjectUtilisateurFromUrl((int) session.getAttribute("idUtilisateur"));
		} else {
			message = "merci de vous connecter pour pouvoir faire une enchere";
			request.setAttribute("messageUtilisateur", message);
		}

	}

	/**
	 * Retourne l'objet Enchere à partir de l'url
	 *
	 * @param id
	 * @return Enchere
	 * @throws BuisnessException
	 */
	private Enchere getObjectEnchereFromUrl(int id) throws BuisnessException {
		return enchereManager.find(id);
	}

	/**
	 * Retourne l'objet Utilisateur à partir de l'url
	 *
	 * @param id
	 * @return Utilisateur
	 * @throws BuisnessException
	 */
	private Utilisateur getObjectUtilisateurFromUrl(int id) throws BuisnessException {
		return utilisateurManager.find(id);
	}

	/**
	 * Méthode qui vérifie si l'id est présent dans l'url ou pas et le retourne
	 *
	 * @param request
	 *
	 * @return int
	 */
	private int getIdParamrUrl(HttpServletRequest request) {

		int id = 0;

		if (request.getParameter("id") != null) {
			id = Utils.transformStringToInt(request.getParameter("id"));
		}

		if (request.getParameter("idArticle") != null) {
			id = Utils.transformStringToInt(request.getParameter("idArticle"));
		}

		return id;
	}

	/**
	 * Méthode de création de l'article par l'id placé en url après vérification si
	 * celui ci existe
	 *
	 * @param id
	 *
	 * @return Article
	 *
	 * @throws BuisnessException
	 */
	private Article createArticleFromId(int id) throws BuisnessException {
		return article = this.getObjectArticleFromUrl(id);
	}

	/**
	 * Méthode qui vérifie si l'enchere est toujour possible
	 *
	 * @param article
	 */
	private void checkDateIsLate(Article article, HttpServletRequest request) {

		boolean isLate = false;

		String message = null;

		// Date du jour
		Date date = new Date();

		// Si il est trop tard is late passe à true
		if (article.getDateFinEnchere().compareTo(date) < 0) {
			isLate = true;
		}

		if (isLate) {
			message = "L'article n'est actuellement dans aucune enchere";
		}

		if (message != null) {
			request.setAttribute("messageDate", message);
		}

	}

	/**
	 * Méthode de création des objets à partir de la requete
	 *
	 * @param request
	 *
	 * @throws BuisnessException
	 */
	private void setAttributeParams(HttpServletRequest request) throws BuisnessException {

		utilisateur = this.getObjectUtilisateurFromUrl(article.getUtilisateur().getIdUtilisateur());

		request.setAttribute("article", article);
		request.setAttribute("utilisateur", utilisateur);
	}

	/**
	 * Retourne l'objet Categorie à partir de l'url
	 *
	 * @param id
	 * @return Categorie
	 * @throws BuisnessException
	 */
	private Categorie getObjectCategorieFromUrl(int id) throws BuisnessException {
		return categorieManager.find(id);
	}

}
