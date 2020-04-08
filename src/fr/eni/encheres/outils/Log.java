package fr.eni.encheres.outils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Classe de Création de log
 */
public class Log {

    /**
     * Constructor qui s'occupe de crée le à l'instanciation de la classe
     *
     * @param message => Message de l'erreur à récuperer via => "e.getMessage()"
     */
    public Log(String message) {
        String fileName = "log.txt";
        File file = new File(fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(fileName, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger logger = Logger.getLogger("log");
        logger.addHandler(fileHandler);
        logger.setLevel(Level.WARNING);
        logger.warning("Message d'erreur : " + message);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
    }
}
