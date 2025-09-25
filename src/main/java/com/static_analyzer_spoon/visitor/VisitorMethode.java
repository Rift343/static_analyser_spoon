package com.static_analyzer_spoon.visitor;

import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.CtScanner;

public class VisitorMethode extends CtScanner {

    private static int maxParametre = 0;//max number of parametre in a methode
    private static int ligneNumber = 0;//number of codeligne in a methode
    
    @Override
    public <T> void visitCtMethod(CtMethod<T> CtMethod) {
        
        //Number of parametre in a methode
        int numberOfParameter = CtMethod.getParameters().size();
        if (numberOfParameter > maxParametre) {
            maxParametre = numberOfParameter;
        }
        //Number of ligne code in a methode
        String sourceCodeMethode = CtMethod.getOriginalSourceFragment().getSourceCode();
        String[] allLigneMethode = sourceCodeMethode.split("\n");
        ligneNumber = allLigneMethode.length;
        
        super.visitCtMethod(CtMethod);
    }

    public static int getMaxParametre() {
        return maxParametre;
    }
    public static int getLigneNumber() {
        return ligneNumber;
    }
}
