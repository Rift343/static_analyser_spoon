package com.static_analyzer_spoon.visitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.visitor.CtScanner;

public class VisitorClass extends CtScanner    {
    private static int visited = 0;//number of class 
    private static int countLigne = 0;//Number of codeline 
    private static int countMethod = 0;//number of methode
    private static double avgLigneMethod = 0;//average number of ligne in a methode
    private static double avgMethodClear = 0;//average number of methode in a class
    private static double avgFiel = 0;//average number of fielf in a class
    private ArrayList<ClassComparator> comporableList = new ArrayList<>();//list to compare the number of Field,methode and codeligne.
    
    @Override
    public <T> void visitCtClass(CtClass<T> ctClass) {
        this.visited = visited + 1;
        
        String sourceCode = ctClass.getOriginalSourceFragment().getSourceCode();
        String[] allLigne = sourceCode.split("\n");
        countLigne = countLigne + allLigne.length;
        
        
        
        
        super.visitCtClass(ctClass);
    }

    public int getVisited(){
        return this.visited;
    }

    public int getCountLigne(){
        return countLigne;
    }

}
