package fr.eni.encheres.dal;


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
    
    public EnchereDAOImpl getEnchereDAOImpl() {
    	return new EnchereDAOImpl();
    }
}
