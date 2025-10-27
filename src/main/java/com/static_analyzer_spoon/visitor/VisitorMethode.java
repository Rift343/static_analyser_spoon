package com.static_analyzer_spoon.visitor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import spoon.reflect.code.CtInvocation;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.visitor.CtScanner;
import spoon.reflect.visitor.filter.TypeFilter;

public class VisitorMethode extends CtScanner {

    private static int maxParametre = 0;//max number of parametre in a methode
    private static int ligneNumber = 0;//number of codeligne in a methode
    private static Collection<GraphNode> allMethodCalled;//The method called by the methode
    
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

        List<CtInvocation<?>> invocations = CtMethod.getElements(new TypeFilter<>(CtInvocation.class));
        List<CtExecutableReference<?>> calledMethods = invocations.stream()
                                                                  .map(inv -> (CtExecutableReference<?>) inv.getExecutable())
                                                                  .collect(Collectors.toList());
        allMethodCalled = calledMethods.stream()
            .map(ref -> 
                new GraphNode(ref.getDeclaringType().getQualifiedName(), ref.getSimpleName())
                )
            .collect(Collectors.toList());
        
        allMethodCalled.forEach(string -> {GraphNode node = new GraphNode(CtMethod.getDeclaringType().getQualifiedName(),CtMethod.getSimpleName());GraphMethode.add(node,string );});//Add to the graph the method call

        
        
        super.visitCtMethod(CtMethod);
    }

    @Override
    public <T> void visitCtConstructor(CtConstructor<T> ctConstructor) {
        // Nombre de paramètres
        int numberOfParameter = ctConstructor.getParameters().size();
        if (numberOfParameter > maxParametre) {
            maxParametre = numberOfParameter;
        }

        // Nombre de lignes : méthode robuste
        SourcePosition pos = ctConstructor.getPosition();
        if (pos != null && pos.isValidPosition()) {
            ligneNumber = pos.getEndLine() - pos.getLine() + 1;
        } else {
            ligneNumber = 0;
        }

        // Appels de méthodes
        List<CtInvocation<?>> invocations = ctConstructor.getElements(new TypeFilter<>(CtInvocation.class));
        List<CtExecutableReference<?>> calledMethods = invocations.stream()
            .map(inv -> inv.getExecutable())
            .collect(Collectors.toList());

        allMethodCalled = calledMethods.stream()
            .map(ref -> new GraphNode(ref.getDeclaringType().getQualifiedName(), ref.getSimpleName()))
            .collect(Collectors.toList());

        // Ajout au graphe
        GraphNode source = new GraphNode(ctConstructor.getDeclaringType().getQualifiedName(), ctConstructor.getSimpleName());
        allMethodCalled.forEach(target -> GraphMethode.add(source, target));

        super.visitCtConstructor(ctConstructor);
    }



    public static int getMaxParametre() {
        return maxParametre;
    }
    public static int getLigneNumber() {
        return ligneNumber;
    }
}
