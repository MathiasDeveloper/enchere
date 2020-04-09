package fr.eni.encheres.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletVendreArticle", urlPatterns = {"/VendreArticle"})
public class ServletVendreArticle extends HttpServlet {

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
        this.getServletContext().getNamedDispatcher("/WEB-INF/vendreArticle.jsp").forward(request,response);
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

    }
}
