package com.static_analyzer_spoon.Processor;

import java.util.ArrayList;
import java.util.Collection;

import com.static_analyzer_spoon.visitor.ClassComparator;
import com.static_analyzer_spoon.visitor.GraphMethode;
import com.static_analyzer_spoon.visitor.VisitorClass;
import com.static_analyzer_spoon.visitor.VisitorPackage;

import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtType;

public class ProcessorStaticAnalyze {

    private int visited;
    private int visitedPackage;
    private int countLigne;
    private int countMethod;
    private double avgLigneMethod;
    private double avgMethodClass;
    private double avgField;
    private int maxParametre;
    private int  top10Index; // Ensure at least one class is shown
    private ArrayList<ClassComparator> allComparable;
    private GraphMethode graph = new GraphMethode();

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
        this.top10Index = Math.max(1, allComparable.size() / 10);
        this.calculAvg();

        VisitorPackage visitorPackage = new VisitorPackage();
        Collection<CtPackage> allPackage = model.getAllPackages();
        for (CtPackage pack : allPackage) {
            pack.accept(visitorPackage);
        }

        visitedPackage = visitorPackage.getVisited();
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

    private void sortClassComparatorByMethode()
    {
        allComparable.sort((c1, c2) -> Integer.compare(c2.getMethodeCount(), c1.getMethodeCount()));
    }

    private void sortClassComparatorByField()
    {
        allComparable.sort((c1, c2) -> Integer.compare(c2.getFieldCount(), c1.getFieldCount()));
    }

    private void sortClassComparatorByLigne()
    {
        allComparable.sort((c1, c2) -> Integer.compare(c2.getLigneCount(), c1.getLigneCount()));
    }

    public void showVisitedClass()
    {
        System.out.println("Nombre de classes visitées : " + visited);
    }

    public void showVisitedPackage()
    {
        System.out.println("Nombre de packages visités : " + visitedPackage);
    }

    public void showCountLigne()
    {
        System.out.println("Nombre total de lignes : " + countLigne);
    }

    public void showCountMethod()
    {
        System.out.println("Nombre total de méthodes : " + countMethod);
    }

    public void showMaxParameter()
    {
        System.out.println("Nombre maximal de paramètres dans une méthode : " + maxParametre);
    }

    public void showAVGligneMethod()
    {
        System.out.println("AVG lignes/méthode : " + avgLigneMethod);
    }

    public void showAVGmethodeClass()
    {
        System.out.println("AVG méthodes/classe : " + avgMethodClass);
    }

    public void showAVGfield()
    {
        System.out.println("AVG champs/classe : " + avgField);
    }

    public void  showTOP10classByField()
    {
        System.out.println("Top 10% des classes avec le plus d'attributs");
        sortClassComparatorByField();
        for (int i = 0; i < top10Index ; i++) {
            System.out.println("    Rang :"+ i+1 +" Classe : " + allComparable.get(i).getSimpleName() + " | Nombre de champs : " + allComparable.get(i).getFieldCount());
        }
    }

    public void showTOP10classByMethod()
    {
        System.out.println("Top 10% des classes avec le plus de méthodes");
        sortClassComparatorByMethode();
        for (int i = 0; i < top10Index ; i++) {
            System.out.println("    Rang :"+ i+1 +" Classe : " + allComparable.get(i).getSimpleName() + " | Nombre de méthodes : " + allComparable.get(i).getMethodeCount());
        }
    }

    public void showTOP10classByLigne()
    {
        System.out.println("Top 10% des classes avec le plus de lignes");
        sortClassComparatorByLigne();
        for (int i = 0; i < top10Index ; i++) {
            System.out.println("    Rang :"+ i+1 +" Classe : " + allComparable.get(i).getSimpleName() + " | Nombre de lignes : " + allComparable.get(i).getLigneCount());
        }
    }

    public void showTOP10classByFieldAndMethod() {
        System.out.println("Top 10% des classes avec le plus d'attributs ET de méthodes");
        sortClassComparatorByField();
        ArrayList<ClassComparator> topField = new ArrayList<>(allComparable.subList(0, top10Index));
        sortClassComparatorByMethode();
        ArrayList<ClassComparator> topMethod = new ArrayList<>(allComparable.subList(0, top10Index));
        ArrayList<ClassComparator> intersection = new ArrayList<>();
        for (ClassComparator c : topField) {
            if (topMethod.contains(c)) {
                intersection.add(c);
            }
        }
        if (intersection.isEmpty()) {
            System.out.println("    Aucune classe ne fait partie du top 10% à la fois pour les attributs et les méthodes.");
        } else {
            for (int i = 0; i < intersection.size(); i++) {
                ClassComparator c = intersection.get(i);
                System.out.println("    Rang :" + (i + 1) + " Classe : " + c.getSimpleName() +
                    " | Nombre de champs : " + c.getFieldCount() +
                    " | Nombre de méthodes : " + c.getMethodeCount());
            }
        }
    }


    public void showClassesWithMoreThanXMethods(int x) {
        System.out.println("Classes avec plus de " + x + " méthodes :");
        boolean found = false;
        for (ClassComparator c : allComparable) {
            if (c.getMethodeCount() > x) {
                System.out.println("    Classe : " + c.getSimpleName() + " | Nombre de méthodes : " + c.getMethodeCount());
                found = true;
            }
        }
        if (!found) {
            System.out.println("    Aucune classe ne possède plus de " + x + " méthodes.");
        }
    }

    public void show() {
        showVisitedClass();
        showCountLigne();
        showCountMethod();
        showMaxParameter();
        
        System.out.println("---- Moyennes (AVG) ----");
        showAVGfield();
        showAVGligneMethod();
        showAVGmethodeClass();

        System.out.println("---- Les 10% ----");
        showTOP10classByField();
        showTOP10classByLigne();
        showTOP10classByMethod();

        showTOP10classByFieldAndMethod();
        
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

    public int getVisitedPackage() {
        return visitedPackage;
    }

}
