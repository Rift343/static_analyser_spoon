package com.static_analyzer_spoon.visitor;

public class ClassComparator {
    
    private int methodeCount;
    private int fieldCount;
    private int lignencount;
    private String simpleName;
    
    public ClassComparator(int m, int f, int l , String s)
    {
        this.fieldCount = f;
        this.methodeCount = m;
        this.lignencount = l;
        this.simpleName = s;
    }

    public int getMethodeCount(){
        return this.methodeCount;

    }

    public int getFieldCount(){
        return this.fieldCount;
    }

    public int getLigneCount(){
        return this.lignencount;
    }

    public String getSimpleName(){
        return this.simpleName;
    }
    


}
