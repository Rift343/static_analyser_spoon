package com.static_analyzer_spoon.visitor;

public class GraphNode {
    private String methodeName;
    private String className;

    public GraphNode(String className, String methodeName)
    {
        this.className = className;
        this.methodeName = methodeName;
    }

    public String getMethodeName(){
        return this.methodeName;
    }

    public String getClassName(){
        return this.className;
    }

    private String getFullName(){
        return this.className + "." + this.methodeName;
    }

    public String toString(){
        return getFullName();
    }

}
