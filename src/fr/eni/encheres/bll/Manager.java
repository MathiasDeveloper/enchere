package fr.eni.encheres.bll;

import java.util.ArrayList;

import fr.eni.encheres.outils.BuisnessException;


public interface Manager <T> {

    void create(T objet) throws BuisnessException;
    void update(T objet) throws BuisnessException;
    void delete(T objet) throws BuisnessException;
    T find(int id) throws BuisnessException;
    ArrayList<T> findAll() throws BuisnessException;
}
