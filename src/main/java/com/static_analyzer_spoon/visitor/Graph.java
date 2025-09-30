package com.static_analyzer_spoon.visitor;

import java.util.Collection;
import java.util.HashMap;

import spoon.reflect.declaration.CtMethod;

public class Graph {

    HashMap<CtMethod<?>, Collection<CtMethod<?>>> mapClassLigne = new java.util.HashMap<>();

    public void add(CtMethod<?> methode, CtMethod<?> newMethodMethode)
    {
        if (mapClassLigne.containsKey(methode)) 
        {
            Collection<CtMethod<?>> list = mapClassLigne.get(methode);
            list.add(newMethodMethode);
            mapClassLigne.put(methode, list);
        } else 
        {
            Collection<CtMethod<?>> listMethode = new java.util.ArrayList<>();
            listMethode.add(newMethodMethode);
            mapClassLigne.put(methode, listMethode);
        }
    }



}
