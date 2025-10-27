package com.static_analyzer_spoon.visitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.visitor.CtScanner;

public class VisitorClass extends CtScanner    {
    private static int visited = 0;//number of class 
    private static int countLigne = 0;//Number of codeline 
    private static int countMethod = 0;//number of methode
    private static double avgLigneMethod = 0;//average number of ligne in a methode
    private static double avgMethodClass = 0;//average number of methode in a class
    private static double avgField = 0;//average number of field in a class
    private ArrayList<ClassComparator> comparableList = new ArrayList<>();//list to compare the number of Field,methode and codeligne.
    private static int maxParametre = 0;//max number of parametre in a methode
    private static GraphMethode graph = new GraphMethode();

    @Override
    public <T> void visitCtClass(CtClass<T> ctClass) {
        //Number of class
        visited = visited + 1;
        
        //Number of ligne code
        String sourceCode = ctClass.getOriginalSourceFragment().getSourceCode();
        String[] allLigne = sourceCode.split("\n");
        countLigne = countLigne + allLigne.length;

        //Number of methode
        Collection<CtMethod<?>> allMethode = ctClass.getMethods();
        countMethod = countMethod + allMethode.size();

        VisitorMethode visitorMethode = new VisitorMethode();
        //Average number of ligne in a methode (just add not divide by the number of methode)
        for (CtMethod<?> ctMethod : allMethode) {
            ctMethod.accept(visitorMethode);
            avgLigneMethod = avgLigneMethod + VisitorMethode.getLigneNumber();
            maxParametre = VisitorMethode.getMaxParametre();
        }

        // Constructeurs
        Set<CtConstructor<T>> rawConstructors = ctClass.getConstructors();
        List<CtConstructor<?>> allConstructors = new ArrayList<>();

        for (CtConstructor<T> constructor : rawConstructors) {
            allConstructors.add(constructor);
        }

        countMethod += allConstructors.size(); // si tu veux inclure les constructeurs dans le total des "méthodes"

        for (CtConstructor<?> ctConstructor : allConstructors) {
            ctConstructor.accept(visitorMethode);
            avgLigneMethod += VisitorMethode.getLigneNumber();
            maxParametre = Math.max(maxParametre, VisitorMethode.getMaxParametre());
    }

        //Average number of field in a class (just add not divide by the number of class)
        Collection<CtFieldReference<?>> allField = ctClass.getAllFields(); 
        avgField = avgField + allField.size();
        
        // ADD a new ClassComparator to the list
        ClassComparator classComparator = new ClassComparator(allMethode.size(), allField.size(), allLigne.length, ctClass.getSimpleName());
        comparableList.add(classComparator);
        super.visitCtClass(ctClass);
    }

    

    public int getVisited(){
        return visited;
    }

    public int getCountLigne(){
        return countLigne;
    }

    public int getCountMethod() {
        return countMethod;
    }

    public double getAvgLigneMethod() {
        return avgLigneMethod;
    }

    public double getAvgMethodClass() {
        return avgMethodClass;
    }

    public double getAvgField() {
        return avgField;
    }

    public ArrayList<ClassComparator> getComparableList() {
        return comparableList;
    }

    public int getMaxParametre() {
        return maxParametre;
    }

   

}
