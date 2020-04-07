package fr.eni.encheres.bll;

import java.util.ArrayList;

public interface Manager <T> {
    T getInstance();
    T create();
    T update();
    T delete();
    T find();
    ArrayList<T> findAll();
}
