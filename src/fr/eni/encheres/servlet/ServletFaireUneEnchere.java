package fr.eni.encheres.servlet;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
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

    private UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
    
    RetraitManager retraitManager = RetraitManager.getInstance();

    private Article article = new Article();

    private Utilisateur utilisateur = new Utilisateur();

    private Enchere enchere = new Enchere();
    
    Retrait retrait = new Retrait();

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
            // Vérification utilisateur en session et le set
            this.checkUtilisateurSession(request.getSession(), request);

            // Verifie si le champs prix est remplis sinon envoie une erreur à l'utilisateur
            this.checkIfPrixExist(request);

            //On récupère l'enchere
            enchere = this.getObjectEnchereById(article.getIdArticle());

            //On garde l'anchère en base afin de pouvoir garder les infos pour redonner les crédits à l'utilisateur qui perd l'enchère
            Enchere oldEnchere = enchere;
            
            // envoie de l'utilisateur connecter pour faire le changement
            enchere.setUtilisateur(utilisateur);

            // Vérification de l'utilisateur sur son crédit
            this.checkUtilisateurGotMoney(utilisateur, Utils.transformStringToInt(request.getParameter("prix")), request);

            // Verifie si le prix est valide
            this.checkPrixIsValid(request);

            //On modifie le montent de l'enchere
            enchere.setMontantEnchere(Utils.transformStringToInt(request.getParameter("prix")));

            //On met l'article dans l'enchere
            enchere.setArticle(article);
            
            //On récupère l'utilisateur perdant
            Utilisateur utilisateurPerdant = getObjectUtilisateurById(oldEnchere.getUtilisateur().getIdUtilisateur());
            
            //On lui rajoute les crédit de l'enchère perdue
            utilisateurPerdant.setCredit(utilisateurPerdant.getCredit() + oldEnchere.getMontantEnchere());
            
            //On update l'utilisateur perdant
            utilisateurManager.updateCreditUtilisateur(0, utilisateurPerdant);
            
            //On update l'enchere
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
            this.checkUtilisateurSession(request.getSession(), request);

            if (request.getParameter("id") != null || request.getParameter("idArticle") != null) {

                // recupere l'id de l'url
                int id = this.getIdParamrUrl(request);

                // Créer l'article
                article = this.createArticleFromId(id);
                
                //On créer le retrait
                retrait = retraitManager.find(article.getIdArticle());
                
                //On créer l'enchere
                enchere = enchereManager.find(article.getIdArticle());

                // Check si l'article est encore en enchere ou non
                this.checkDateIsLate(article, request);

                // Construit les objets nécessaire à partir de la requete
                this.setAttributeParams(request);
                
                //On passe les objets construits à la jsp
                request.setAttribute("enchere", enchere);
				request.setAttribute("retrait", retrait);
				request.setAttribute("article", article);
				request.setAttribute("utilisateur", utilisateur);

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
        enchere = this.getObjectEnchereById(Utils.transformStringToInt(request.getParameter("idArticle")));

        // Récupérer le prix saisit par l'utilisateur dans une varibale
        int prix = Utils.transformStringToInt(request.getParameter("prix"));

        String message = null;

        try {
            // Si le prix est supérieur à l'enchere précédente
            if (prix > enchere.getMontantEnchere() && prix >= article.getPrixInitial() && prix <= utilisateur.getCredit()) {
                this.setMessageSuccesEnchere(request);
            } else { // cas ou l'enchere est inférieure leve une erreur à l'utilisateur
                message = "Votre enchère est plus basse que l'enchere d'un autre utilisateur ou que le prix initial";
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
     */
    private void checkUtilisateurSession(HttpSession session, HttpServletRequest request) throws BuisnessException {
        boolean isValid = false;

        String message;

        if (session.getAttribute("idUtilisateur") != null) {
            isValid = true;
        }

        if (isValid) {
            utilisateur = getObjectUtilisateurById((int) session.getAttribute("idUtilisateur"));
        } else {
            message = "merci de vous connecter pour pouvoir faire une enchere";
            request.setAttribute("messageUtilisateur", message);
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
    private Article getObjectArticleById(int id) throws BuisnessException {
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
    private Enchere getObjectEnchereById(int id) throws BuisnessException {
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
    private Utilisateur getObjectUtilisateurById(int id) throws BuisnessException {
        return utilisateurManager.find(id);
    }

    /**
     * Méthode de création de l'article par l'id placé en url après vérification si celui ci existe
     *
     * @param id
     *
     * @return Article
     *
     * @throws BuisnessException
     */
    private Article createArticleFromId(int id) throws BuisnessException {
        return article = this.getObjectArticleById(id);
    }

    /**
     * Méthode de création des objets à partir de la requete
     *
     * @param request
     *
     * @throws BuisnessException
     */
    private void setAttributeParams(HttpServletRequest request) throws BuisnessException {

        utilisateur = this.getObjectUtilisateurById(article.getUtilisateur().getIdUtilisateur());

        request.setAttribute("article", article);
        request.setAttribute("utilisateur", utilisateur);
    }

    /**
     * Méthode de vérification sur les credit de l'utilisateur
     *
     * @param utilisateur
     * @param prix
     *
     * @throws BuisnessException
     */
    private void checkUtilisateurGotMoney(Utilisateur utilisateur, int prix, HttpServletRequest request) throws BuisnessException {
        String message = null;

        try {
            // Si les crédit de l'utilisateur est à 0 alors lève une erreur
            if (utilisateur.getCredit() == 0) {
                message = "Merci de rajouter des crédits sur votre compte";
            }
            // Si le prix est supérieur au credit de l'utilisateur
            if (prix > utilisateur.getCredit()) {
                message = "Le montant que vous avez saisie est superieur à celui de vos crédits actuel";
            }

            if (prix > 0 && message == null) {
                utilisateurManager.updateCreditUtilisateur(prix, enchere.getUtilisateur());
            }

            // Si message est différent de null leve une exception
            if (message != null) {
                throw new Exception();
            }

        } catch (Exception e) {
            request.setAttribute("messageCredit", message);
            throw new BuisnessException(e.getMessage(), e);
        }
    }

    private void setMessageSuccesEnchere(HttpServletRequest request) {
        request.setAttribute("messageSucces", "Enchere prise en compte");
    }
}
