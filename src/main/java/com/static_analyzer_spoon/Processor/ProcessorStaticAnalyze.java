package com.static_analyzer_spoon.Processor;

import java.util.ArrayList;
import java.util.Collection;

import com.static_analyzer_spoon.visitor.ClassComparator;
import com.static_analyzer_spoon.visitor.VisitorClass;

import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtType;

public class ProcessorStaticAnalyze {

    private int visited;
    private int countLigne;
    private int countMethod;
    private double avgLigneMethod;
    private double avgMethodClass;
    private double avgField;
    private int maxParametre;
    private ArrayList<ClassComparator> allComparable;

    public void process(CtModel model)
    {
        VisitorClass visitorClass = new VisitorClass();
        Collection<CtType<?>> allclass = model.getAllTypes();
        for (CtType<?> ctType : allclass) {
            ctType.accept(visitorClass);
        }
        
        visited = visitorClass.getVisited();
        countLigne = visitorClass.getCountLigne();
        countMethod = visitorClass.getCountMethod();
        avgLigneMethod = visitorClass.getAvgLigneMethod();
        avgMethodClass = visitorClass.getAvgMethodClass();
        avgField = visitorClass.getAvgField();
        maxParametre = visitorClass.getMaxParametre();
        this.allComparable = visitorClass.getComparableList();
        this.calculAvg();
    }

    public void calculAvg()
    {
        if (countMethod != 0) {
            avgLigneMethod = avgLigneMethod / countMethod;
        }
        if (visited != 0) {
            avgMethodClass = (double) countMethod / visited;
            avgField = avgField / visited;
        }
    }

    private void sortClassComparatorByMethode(){
        allComparable.sort((c1, c2) -> Integer.compare(c2.getMethodeCount(), c1.getMethodeCount()));
    }

    private void sortClassComparatorByField(){
        allComparable.sort((c1, c2) -> Integer.compare(c2.getFieldCount(), c1.getFieldCount()));
    }

    private void sortClassComparatorByLigne(){
        allComparable.sort((c1, c2) -> Integer.compare(c2.getLigneCount(), c1.getLigneCount()));
    }

    public void show() {
        System.out.println("Nombre de classes visitées : " + visited);
        System.out.println("Nombre total de lignes : " + countLigne);
        System.out.println("Nombre total de méthodes : " + countMethod);
        System.out.println("Nombre maximal de paramètres dans une méthode : " + maxParametre);
        System.out.println("---- Moyennes (AVG) ----");
        System.out.println("AVG lignes/méthode : " + avgLigneMethod);
        System.out.println("AVG méthodes/classe : " + avgMethodClass);
        System.out.println("AVG champs/classe : " + avgField);
        System.out.println("---- Les 10% ----");
        int top10Index = Math.max(1, allComparable.size() / 10); // Ensure at least one class is shown
        System.out.println("Top 10% des classes avec le plus d'attributs");
        sortClassComparatorByField();
        for (int i = 0; i < top10Index ; i++) {
            System.out.println("    Rang :"+ i+1 +" Classe : " + allComparable.get(i).getSimpleName() + " | Nombre de champs : " + allComparable.get(i).getFieldCount());
        }
        System.out.println("Top 10% des classes avec le plus de méthodes");
        sortClassComparatorByMethode();
        for (int i = 0; i < top10Index ; i++) {
            System.out.println("    Rang :"+ i+1 +" Classe : " + allComparable.get(i).getSimpleName() + " | Nombre de méthodes : " + allComparable.get(i).getMethodeCount());
        }
        System.out.println("Top 10% des classes avec le plus de lignes");
        sortClassComparatorByLigne();
        for (int i = 0; i < top10Index ; i++) {
            System.out.println("    Rang :"+ i+1 +" Classe : " + allComparable.get(i).getSimpleName() + " | Nombre de lignes : " + allComparable.get(i).getLigneCount());
        }
    }

    public int getVisited() {
        return visited;
    }

    public int getCountLigne() {
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

    public int getMaxParametre() {
        return maxParametre;
    }

}
