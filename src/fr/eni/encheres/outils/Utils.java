package fr.eni.encheres.outils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;


public class Utils {

    /**
     * Methode de transformation de la date envoyer par le formulaire
     *
     * @param paramDate
     * @return Date
     * @throws BuisnessException
     */
    public static Date transformDateParam(String paramDate) throws BuisnessException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        Date date;
        try {
            date = sdf.parse(paramDate);
        } catch (ParseException e) {
            throw new BuisnessException(e.getMessage(), e);
        }
        return date;
    }

    /**
     * Methode de transformation d'une string en time
     *
     * @param time
     * @return
     */
    public static Time transformStringToHeure(String time){
        return Time.valueOf(time);
    }

    /**
     * Methode de transformation d'une chaine de caractere en integer
     *
     * @param number
     * @return
     */
    public static Integer transformStringToInt(String number){
        return Integer.parseInt(number);
    }

}
