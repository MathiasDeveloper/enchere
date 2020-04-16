package fr.eni.encheres.dal;

import fr.eni.encheres.*;

/**
 * Classe usine de DAO
 */
public class DAOFactory {

    /**
     * Récupération de la classe dao categorie
     *
     * @return CategorieDAOImpl
     */
    public CategorieDAOImpl getCategorieDAOImpl(){
        return new CategorieDAOImpl();
    }

    public UtilisateurDAOImpl getUtilisateurDAOImpl() {
        return new UtilisateurDAOImpl();
    }

    /**
     * Récupération de la classe dao categorie
     *
     * @return CategorieDAOImpl
     */
    public ArticleDAOImpl getArticleDAOImpl(){
        return new ArticleDAOImpl();
    }

    /**
     * Récupération de la classe dao enchere
     *
     * @return CategorieDAOImpl
     */
    public EnchereDAOImpl getEnchereDAOImpl(){
        return new EnchereDAOImpl();
    }
    
    /**
     * Récupération de la classe dao retrait
     *
     * @return CategorieDAOImpl
     */
    public RetraitDAOImpl getRetraitDAOImpl() {
        return new RetraitDAOImpl();
    }
}
