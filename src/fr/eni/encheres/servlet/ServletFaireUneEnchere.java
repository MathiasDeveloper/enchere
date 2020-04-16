package fr.eni.encheres.servlet;

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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ServletFaireUneEnchere", urlPatterns = {"/faireEnchere"})
public class ServletFaireUneEnchere extends HttpServlet {

    /**
     * Instance EnchereManager
     *
     * @object
     */
    private EnchereManager enchereManager = EnchereManager.getInstance();

    private ArticleManager articleManager = ArticleManager.getInstance();

    private CategorieManager categorieManager = CategorieManager.getInstance();

    private UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

    private Article article = new Article();

    private Utilisateur utilisateur = new Utilisateur();

    private Categorie categorie = new Categorie();

    private Enchere enchere = new Enchere();

    /**
     * Méthode de traitement de requete de type POST
     *
     * @param request
     * @param response
     *
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            // Verifie si le champs prix est remplis sinon envoie une erreur à l'utilisateur
            this.checkIfPrixExist(request);

            // Verifie si le prix est valide
            this.checkPrixIsValid(request);

            // Initialisation des objets apres vérifications
            this.setAttributeParams(request);

            // Vérification de l'utilisateur sur son crédit
            this.checkUtilisateurGotMoney(utilisateur, Utils.transformStringToInt(request.getParameter("prix")));

            enchereManager.update(enchere);

        } catch (BuisnessException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }


    /**
     * Méthode de traitement des requetes de type GET
     *
     * @param request
     * @param response
     *
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {

            // Check si l'utilisateur existe en Session
            this.checkUtilisateurSession(request.getSession());


            if (request.getParameter("id") != null || request.getParameter("idArticle") != null) {

                // recupere l'id de l'url
                int id = this.getIdParamrUrl(request);

                // Créer l'article
                article = this.createArticleFromId(id);

                // Check si l'article est encore en enchere ou non
                this.checkDateIsLate(article, request);

                // Construit les objets nécessaire à partir de la requete
                this.setAttributeParams(request);

                this.getServletContext().getRequestDispatcher("/WEB-INF/faireEnchere.jsp").forward(request, response);
            } else {
                this.getServletContext().getRequestDispatcher("/WEB-INF/erreur404.jsp").forward(request, response);
            }


        } catch (BuisnessException e) {
            e.printStackTrace();
        }

    }

    /**
     * Vérifie si le prix existe bien
     *
     * @param request
     *
     * @throws BuisnessException
     */
    private void checkIfPrixExist(HttpServletRequest request) throws BuisnessException {
        String message = null;

        try {
            // Si le prix est vide
            if (request.getParameter("prix").trim().equals("")) {
                message = "Merci de renseigner un prix pour prendre en compte votre enchere";
            }

            if (message != null) {
                throw new Exception();
            }

        } catch (Exception e) {
            request.setAttribute("messagePrix", message);
            throw new BuisnessException(e.getMessage(), e);
        }
    }


    /**
     * Méthode qui vérifie si le prix est valide ou non
     *
     * @param request
     */
    private void checkPrixIsValid(HttpServletRequest request) throws BuisnessException {

        // Récupération de l'objet enchere via l'id de l'article
        enchere = this.getObjectEnchereFromUrl(Utils.transformStringToInt(request.getParameter("idArticle")));

        // Récupérer le prix saisit par l'utilisateur dans une varibale
        Integer prix = Utils.transformStringToInt(request.getParameter("prix"));

        String message = null;

        try {
            // Si le prix est supérieur à l'enchere précédente
            if (prix > enchere.getMontantEnchere()) {
                message = "Votre enchère à été prise en compte";
            } else { // cas ou l'enchere est inférieure leve une erreur à l'utilisateur
                message = "Votre enchère est plus basse que l'enchere d'un autre utilisateur";
                throw new Exception("L'utilisateur à saisie un chiffre inferieur à l'enchere");
            }
        } catch (Exception e) {
            request.setAttribute("message", message);
            throw new BuisnessException(e.getMessage(), e);
        }
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
     * Méthode qui vérifie si l'id est présent dans l'url ou pas et le retourne
     *
     * @param request
     *
     * @return int
     *
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
     * Méthode de vérification si l'utilisateur est présent en session
     *
     * @param session
     *
     */
    private void checkUtilisateurSession(HttpSession session) {
        boolean isValid = false;

        if (session.getAttribute("idUtilisateur") != null) {
            isValid = true;
        }

        if (isValid){
            utilisateur.setIdUtilisateur((int) session.getAttribute("idUtilisateur"));
        }

    }

    /**
     * Retourne l'objet Article à partir de l'url
     *
     * @param id
     *
     * @return Article
     *
     * @throws BuisnessException
     */
    private Article getObjectArticleFromUrl(int id) throws BuisnessException {
        return articleManager.find(id);
    }

    /**
     * Retourne l'objet Enchere à partir de l'url
     *
     * @param id
     *
     * @return Enchere
     *
     * @throws BuisnessException
     */
    private Enchere getObjectEnchereFromUrl(int id) throws BuisnessException {
        return enchereManager.find(id);
    }

    /**
     * Retourne l'objet Utilisateur à partir de l'url
     *
     * @param id
     *
     * @return Utilisateur
     *
     * @throws BuisnessException
     */
    private Utilisateur getObjectUtilisateurFromUrl(int id) throws BuisnessException {
        return utilisateurManager.find(id);
    }

    /**
     * Retourne l'objet Categorie à partir de l'url
     *
     * @param id
     *
     * @return Categorie
     *
     * @throws BuisnessException
     */
    private Categorie getObjectCategorieFromUrl(int id) throws BuisnessException {
        return categorieManager.find(id);
    }

    /**
     * Méthode de création de l'article par l'id placé en url après vérification si celui ci existe
     *
     * @param id
     *
     * @return Article
     * @throws BuisnessException
     */
    private Article createArticleFromId(int id) throws BuisnessException {
         return article = this.getObjectArticleFromUrl(id);
    }

    /**
     * Méthode de création des objets à partir de la requete
     *
     * @param request
     *
     * @throws BuisnessException
     */
    private void setAttributeParams(HttpServletRequest request) throws BuisnessException {

        enchere = this.getObjectEnchereFromUrl(article.getIdArticle());
        utilisateur = this.getObjectUtilisateurFromUrl((int) request.getSession().getAttribute("idUtilisateur"));
        categorie = this.getObjectCategorieFromUrl(article.getCategorie().getIdCategorie());

        // Utilisateur en session pour l'update
        enchere.setUtilisateur(utilisateur);

        // Vérification si le prix existe sinon le reste n'est pas executer
        this.checkIfPrixExist(request);

        // Insertion du prix dans l'enchere
        enchere.setMontantEnchere(Utils.transformStringToInt(request.getParameter("prix")));

        request.setAttribute("article", article);
        request.setAttribute("enchere", enchere);
        request.setAttribute("utilisateur", utilisateur);
        request.setAttribute("categorie", categorie);
    }

    private void checkUtilisateurGotMoney(Utilisateur utilisateur, int prix) throws BuisnessException {
        String message = null;

        try {
            // Si les crédit de l'utilisateur est à 0 alors lève une erreur
            if (utilisateur.getCredit() == 0){
                message = "Merci de rajouter des crédits sur votre compte";
            }
            // Si le prix est supérieur au credit de l'utilisateur
            if (prix > utilisateur.getCredit()){
                message = "Le montant que vous avez saisi est superieur à celui de vos crédits actuelle";
            }

            // Si message est différent de null leve une exception
            if (message != null){
                throw new Exception();
            }

        } catch (Exception e){
            throw new BuisnessException(e.getMessage(), e);
        }

    }
}
