package fr.eni.encheres.servlet;

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
import fr.eni.encheres.outils.Log;
import fr.eni.encheres.outils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;


@WebServlet(name = "ServletVendreArticle", urlPatterns = {"/VendreArticle"})
public class ServletVendreArticle extends HttpServlet {

	/**
	 * Categorie Manager
	 */
	private static CategorieManager categorieManager = CategorieManager.getInstance();

	/**
	 * Article Manager
	 */
	private static ArticleManager articleManager = ArticleManager.getInstance();

	/**
	 * Enchere Manager
	 */
	private static EnchereManager enchereManager = EnchereManager.getInstance();
	/**
	 * Retrait Manager
	 */
	private static RetraitManager retraitManager = RetraitManager.getInstance();

	public ServletVendreArticle() {
		super();
	}

	/**
	 * Méthodes gestion d'envoie de formulaire
	 *
	 * @param request
	 * @param response
	 *
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			// Vérification parametre du formulaire article
			this.verifierParamsArticleFormulaire(request);

			// Vérification des parametres du formulaire enchere
			this.verifierParamsEnchereFormulaire(request);

			// Création de l'objet Article à partir du formulaire
			Article article = this.creerArticleFormulaire(request);

			// Insertion de l'objet Article en bdd
			article = articleManager.createArticle(article);

			// Création de l'objet Enchere à partir du formulaire
			Enchere enchere = this.creerEnchereFormulaire(article, request);
			
			//Création du retrait
			retraitManager.create(creationRetraitFormulaire(request, article));

			// Insertion en bdd de l'objet Enchere
			enchereManager.create(enchere);

			if (request.getParameter("formSubmit") != null){
				this.setMessageSucces(request);
			}

		} catch (BuisnessException e) {
			e.printStackTrace();
		}
		doGet(request, response);

	}

	/**
	 * Methode gestion de récupération des paramètres de l'url
	 *
	 * @param request
	 * @param response
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On récupère la session
		HttpSession session = request.getSession();

		//Si l'utilisateur n'est pas connecté, on le renvoit sur une page 404
		if(session.getAttribute("idUtilisateur")!=null) {
			// Affiche les catégories
			try {
				if (!categorieManager.findAll().isEmpty()) {
					request.setAttribute("categories", categorieManager.findAll());
					request.setAttribute("utilisateur", UtilisateurManager.getInstance().find((int) session.getAttribute("idUtilisateur")));
				}
			} catch (BuisnessException e) {
				e.printStackTrace();
			}
			// Page de la création d'un article
			this.getServletContext().getRequestDispatcher("/WEB-INF/vendreArticle.jsp").forward(request, response);
		}else {
			//On redirige vers une page indiquant que l'utilisateur est inconnu
    		this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurInconnu.jsp").forward(request, response);
		}
	}

		/**
		 * Methode de création d'un objet article envoyer par le formulaire
		 *
		 * @param request
		 *
		 * @return
		 *
		 * @throws BuisnessException
		 */
		private Article creerArticleFormulaire(HttpServletRequest request) throws BuisnessException {
			request.getAttribute("nomArticle");
			Article article = new Article();
			article.setNomArticle(request.getParameter("nomArticle"));
			article.setDescription(request.getParameter("descriptionArticle"));
			article.setPrixInitial(Integer.parseInt(request.getParameter("prixInitial")));
			article.setDateDebutEnchere(Utils.transformDateParam(request.getParameter("dateDebutEnchere")));
			article.setDateFinEnchere(Utils.transformDateParam(request.getParameter("dateFinEnchere")));
			article.setCategorie(this.creerCategorieFormulaire(request));
			article.setUtilisateur(this.getIdUtilisateurBySession(request.getSession()));

			return article;
		}

		/**
		 * Méthode de création à partir
		 *
		 * @param article
		 * @param request
		 *
		 * @return Enchere
		 */
		private Enchere creerEnchereFormulaire(Article article, HttpServletRequest request) {
			Enchere enchere = new Enchere();
			enchere.setArticle(article);
			enchere.setDateEnchere(article.getDateDebutEnchere());
			enchere.setUtilisateur(this.getIdUtilisateurBySession(request.getSession()));

			try {
				enchere.setHeureDebutEnchere(Utils.transformStringToHeure(request.getParameter("heureDebutEnchere")));
				enchere.setHeureFinEnchere(Utils.transformStringToHeure(request.getParameter("heureFinEnchere")));
			} catch (BuisnessException e) {
				e.printStackTrace();
			}

			return enchere;
		}

		/**
		 * Méthode de création d'une catégorie à partir du formulaire
		 *
		 * @param request
		 *
		 * @return
		 */
		private Categorie creerCategorieFormulaire(HttpServletRequest request) {

			Categorie categorie = new Categorie();

			categorie.setIdCategorie(Utils.transformStringToInt(request.getParameter("categorie")));

			return categorie;
		}

		/**
		 * Récupérer id utilisateur via la session
		 *
		 * @param session
		 *
		 * @return Utilisateur
		 */
		private Utilisateur getIdUtilisateurBySession(HttpSession session) {
			Utilisateur utilisateur = new Utilisateur();

			if (!session.getAttribute("idUtilisateur").equals("")) {
				utilisateur.setIdUtilisateur((int) session.getAttribute("idUtilisateur"));
			}

			return utilisateur;
		}

		private void verifierParamsArticleFormulaire(HttpServletRequest request) throws BuisnessException {

			String message = null;

			Date dateDebut = Utils.transformDateParam(request.getParameter("dateDebutEnchere"));
			Date dateFin = Utils.transformDateParam(request.getParameter("dateFinEnchere"));

			try {

				if (request.getParameter("nomArticle").trim().isEmpty()) {
					message = "Merci de renseignez un nom d'article";
				}
				if (request.getParameter("descriptionArticle").trim().isEmpty()) {
					message = "Merci de mettre une description de votre Article pour que les utilisateurs achetent " +
							"plus facilement votre bien";
				}
				if (request.getParameter("prixInitial").isEmpty()) {
					message = "Merci de donnez un prix de base à votre article";
				}
				if (request.getParameter("dateDebutEnchere").isEmpty()) {
					message = "Merci de donner une date de début d'enchere";
				}

				if (dateDebut.compareTo(dateFin) > 0){
					message = "La date de début doit etre antérieur à la date de fin";
				}

				if (request.getParameter("dateFinEnchere").isEmpty()) {
					message = "Merci de donnez une date de fin à votre enchere";
				}

				if (message != null){
					throw new Exception();
				}

			} catch (Exception e) {
				request.setAttribute("messageArticle", message);
				new Log("Formulaire invalide" + e.getMessage());
				throw new BuisnessException("Le formulaire n'est pas valide", e);
			}

		}

		private void verifierParamsEnchereFormulaire(HttpServletRequest request) throws BuisnessException {
			String message = null;

			Date dateDebut = Utils.transformDateParam(request.getParameter("dateDebutEnchere"));
			Date dateFin = Utils.transformDateParam(request.getParameter("dateFinEnchere"));

			try {
				if (request.getParameter("heureDebutEnchere").trim().isEmpty()) {
					message = "Merci de renseignez une heure de début de l'enchere";
				}
				if (request.getParameter("heureFinEnchere").trim().isEmpty()) {
					message = "Merci de renseignez une heure de fin de l'enchere";
				}

				if (dateDebut.compareTo(dateFin) == 0){
					// TODO MRO : cas à gerer si le jour de début est égale à la date fin alors il faut vérifier si l'heure de début est supérieur à la date de fin
				}

				if (message != null){
					throw new Exception();
				}

			} catch (Exception e) {
				request.setAttribute("messageEnchere", message);
				new Log("Formulaire invalide" + e.getMessage());
				throw new BuisnessException("Le formulaire n'est pas valide " + e.getMessage(), e);
			}
		}

		private void setMessageSucces(HttpServletRequest request){
			request.setAttribute("messageSucces", "Enregistrement fait avec succès");
		}
		
		private Retrait creationRetraitFormulaire(HttpServletRequest request, Article article) throws BuisnessException {
			String message = null;
			Retrait retrait = new Retrait();
			if(request.getParameter("retraitRue").equals("") || request.getParameter("retraitCodePostal").equals("") || request.getParameter("retraitVille").equals("")) {
				try {
					message = "Merci de renseignez toutes les conditions de retrait.";
					throw new Exception();
				} catch (Exception e) {
					request.setAttribute("messageEnchere", message);
					new Log("Formulaire invalide" + e.getMessage());
					throw new BuisnessException("Le formulaire n'est pas valide " + e.getMessage(), e);
				}
			}else{
				if(request.getParameter("retraitCodePostal").length()==5) {
					try {
					    int codePostalNbre = Integer.parseInt(request.getParameter("retraitCodePostal"));
						retrait.setArticle(article);
						retrait.setRue(request.getParameter("retraitRue"));
						retrait.setCodePostal(request.getParameter("retraitCodePostal"));
						retrait.setVille(request.getParameter("retraitVille"));
						return retrait;
					} catch (NumberFormatException e) {
						message = "Veuillez rentrer un code postal valide.";
						request.setAttribute("messageEnchere", message);
						new Log("Formulaire invalide" + e.getMessage());
						throw new BuisnessException("Le formulaire n'est pas valide " + e.getMessage(), e);
					}
				}else {
					try {
						message = "Veuillez rentrer un code postal valide.";
						throw new Exception();
					} catch (Exception e) {
						request.setAttribute("messageEnchere", message);
						new Log("Formulaire invalide" + e.getMessage());
						throw new BuisnessException("Le formulaire n'est pas valide " + e.getMessage(), e);
					}
				}
				
			}
		}


	}
