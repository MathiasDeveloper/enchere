package fr.eni.encheres.dal;

import java.util.ArrayList;

/**
 * Interface DAO
 * @param <T>
 */
public interface DAO <T> {
    void create (T objet);
    void update (T objet);
    void delete (T objet);
    T find (int id);
    ArrayList<T> findAll ();
}
