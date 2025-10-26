package com.static_analyzer_spoon.dendrogramme;

import java.util.HashSet;
import java.util.Set;

public class Cluster {
    private Set<String> classNames; // les classes contenues dans ce cluster
    private Cluster left;
    private Cluster right;
    private double averageCoupling; //couplage moyen entre les deux clusters fusionnés

    public Cluster(String className) {
        this.classNames = new HashSet<>();
        this.classNames.add(className);
    }

    public Cluster(Cluster a, Cluster b, double averageCoupling) {
        this.left = a;
        this.right = b;
        this.classNames = new HashSet<>();
        this.classNames.addAll(a.classNames);
        this.classNames.addAll(b.classNames);
        this.averageCoupling = averageCoupling;
    }

    public Set<String> getClassNames() {
        return classNames;
    }

    public double getAverageCoupling() {
        return averageCoupling;
    }

    public Cluster getLeft() {
        return left;
        
    }

    public Cluster getRight() {
        return right;
    }
    
    public boolean isLeaf() {
         return left == null && right == null; 
    }
}
