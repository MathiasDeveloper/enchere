package fr.eni.encheres.servlet;

import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.outils.BuisnessException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletVendreArticle", urlPatterns = {"/VendreArticle"})
public class ServletVendreArticle extends HttpServlet {

    private static CategorieManager categorieManager = CategorieManager.getInstance();

    public ServletVendreArticle(){
        super();
    }

    /**
     * Méthodes gestion d'envoie de formulaire
     *
     * @param request
     * @param response
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Methode gestion de récupération des paramètres de l'url
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("categories", categorieManager.findAll());
        } catch (BuisnessException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/vendreArticle.jsp").forward(request,response);
    }
}
