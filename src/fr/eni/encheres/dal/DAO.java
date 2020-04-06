package fr.eni.encheres.dal;

import java.util.ArrayList;

/**
 * Interface DAO
 * @param <T>
 */
public interface DAO <T> {
    T create (T objet);
    T delete (T objet);
    T find (int id);
    ArrayList<T> findAll (T objet);
}
