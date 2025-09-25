package com.static_analyzer_spoon.Processor;

import java.util.Collection;

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

    public void process(CtModel model)
    {
        VisitorClass visitorClass = new VisitorClass();
        Collection<CtType<?>> allclass = model.getAllTypes();
        for (CtType<?> ctType : allclass) {
            ctType.accept(visitorClass);
        }
        this.calculAvg();
        visited = visitorClass.getVisited();
        countLigne = visitorClass.getCountLigne();
        countMethod = visitorClass.getCountMethod();
        avgLigneMethod = visitorClass.getAvgLigneMethod();
        avgMethodClass = visitorClass.getAvgMethodClass();
        avgField = visitorClass.getAvgField();
        maxParametre = visitorClass.getMaxParametre();
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
