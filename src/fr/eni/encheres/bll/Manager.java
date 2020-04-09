package fr.eni.encheres.bll;

import java.util.ArrayList;

import fr.eni.encheres.outils.BuisnessException;


public interface Manager <T> {

    T create() throws BuisnessException;
    T update() throws BuisnessException;
    T delete() throws BuisnessException;
    T find(int id) throws BuisnessException;
    ArrayList<T> findAll() throws BuisnessException;
}
