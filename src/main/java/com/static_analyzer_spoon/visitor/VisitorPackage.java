package com.static_analyzer_spoon.visitor;

import spoon.reflect.declaration.CtPackage;
import spoon.reflect.visitor.CtScanner;

public class VisitorPackage extends CtScanner {
    private static int visited = 0;//number of package visited
    
    @Override
    public void visitCtPackage(CtPackage ctPackage) {
        visited = visited + 1;
        super.visitCtPackage(ctPackage);
    }

    public int getVisited(){
        return visited;
    }

}
