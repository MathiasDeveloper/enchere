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
}
