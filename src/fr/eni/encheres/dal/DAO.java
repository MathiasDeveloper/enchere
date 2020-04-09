package fr.eni.encheres.dal;

import java.util.ArrayList;

import fr.eni.encheres.outils.BuisnessException;

/**
 * Interface DAO
 * @param <T>
 */
public interface DAO <T> {
    void create (T objet) throws BuisnessException;
    void update (T objet) throws BuisnessException;
    void delete (T objet) throws BuisnessException;
    T find (int id) throws BuisnessException;
    ArrayList<T> findAll ();
}
