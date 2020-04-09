package fr.eni.encheres.servlet;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.outils.BuisnessException;
import fr.eni.encheres.outils.Utils;

import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            Article article = this.creerArticle(request);
            Enchere enchere = this.creerEnchere(article, request);
            articleManager.create(article);
            enchereManager.create(enchere);
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

        // Affiche les catégories
        try {
            if (!categorieManager.findAll().isEmpty()) {
                request.setAttribute("categories", categorieManager.findAll());
            }
        } catch (BuisnessException e) {
            e.printStackTrace();
        }
        // Page de la création d'un article
        this.getServletContext().getRequestDispatcher("/WEB-INF/vendreArticle.jsp").forward(request, response);
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
    private Article creerArticle(HttpServletRequest request) throws BuisnessException {
        request.getAttribute("nomArticle");
        Article article = new Article();
        article.setNomArticle(request.getParameter("nomArticle"));
        article.setDescription(request.getParameter("descriptionArticle"));
        article.setPrixInitial(Integer.parseInt(request.getParameter("prixInitialVente")));
        article.setDateDebutEnchere(Utils.transformDateParam(request.getParameter("DateDebutEnchere")));
        article.setDateFinEnchere(Utils.transformDateParam(request.getParameter("DateFinEnchere")));
        article.setPrixInitial(Utils.transformStringToInt(request.getParameter("prixInitialVente")));
        article.setIdCategorie(Utils.transformStringToInt(request.getParameter("categorie")));
        return article;
    }

    private Enchere creerEnchere(Article article, HttpServletRequest request) {
        Enchere enchere = new Enchere();
        enchere.setArticle(article);
        enchere.setDateEnchere(article.getDateDebutEnchere());
        enchere.setHeureDebutEnchere(Utils.transformStringToHeure(request.getParameter("heureDebutEnchere")));
        enchere.setHeureFinEnchere(Utils.transformStringToHeure(request.getParameter("heureFinEnchere")));

        return enchere;
    }


}
