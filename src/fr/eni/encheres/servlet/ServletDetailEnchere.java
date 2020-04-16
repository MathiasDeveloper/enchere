package fr.eni.encheres.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.outils.BuisnessException;
import fr.eni.encheres.outils.Utils;

/**
 * Servlet implementation class ServletDetailEnchere
 */
@WebServlet(name = "/ServletDetailEnchere", urlPatterns = {"/detailEnchere"})
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            article = this.getObjectArticleFromUrl(Utils.transformStringToInt(request.getParameter("id")));
            enchere = this.getObjectEnchereFromUrl(article.getIdArticle());
            utilisateur = this.getObjectUtilisateurFromUrl(article.getUtilisateur().getIdUtilisateur());
            categorie = this.getObjectCategorieFromUrl(article.getCategorie().getIdCategorie());
        } catch (BuisnessException e) {
            e.printStackTrace();
        }

        System.out.println(enchere.toString());
        System.out.println(article.toString());
        System.out.println(utilisateur.toString());
        System.out.println(categorie.toString());

        this.getServletContext().getRequestDispatcher("/WEB-INF/detailEnchere.jsp").forward(request,response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
