package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Categorie;
import org.jetbrains.annotations.Contract;

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
}
