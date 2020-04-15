package fr.eni.encheres.outils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.sql.Date;


public class Utils {

    /**
     * Methode de transformation de la date envoyer par le formulaire
     *
     * @param paramDate
     * @return Date
     * @throws BuisnessException
     */
    public static Date transformDateParam(String paramDate) {
        return  Date.valueOf(paramDate);
    }

    /**
     * Methode de transformation d'une string en time
     *
     * @param time
     * @return
     */
    public static Time transformStringToHeure(String time) throws BuisnessException {
        DateFormat df = new SimpleDateFormat("hh:mm");
        Time timeValue;

        try{
            timeValue = new Time(df.parse(time).getTime());
        } catch (ParseException e) {
            new Log(e.getMessage());
            throw new BuisnessException(e.getMessage(), e);
        }
        return timeValue;
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
