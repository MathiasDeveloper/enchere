package fr.eni.encheres.outils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Classe de gestion des exceptions
 */
public class BuisnessException extends Exception{

    /**
     * Récupération du message de l'exeption
     * @param message => message de l'exception envoyé
     */
    public BuisnessException(String message, Throwable cause){
        super(message, cause);
    }

}
