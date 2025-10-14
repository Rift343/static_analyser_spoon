package com.static_analyzer_spoon.visitor;

public class CouplingIdentificator {

    private String firstClass;
    private String secondClass;
    private int numberOfRelation = 0;
    private double  couplingValue = 0.0;

    public CouplingIdentificator(String f, String s)
    {
        if (f.compareTo(s) == 0)
        {
            throw new IllegalArgumentException("The two classes must be different");
        }

        if(f.compareTo(s)<0)
        {
            this.firstClass = f;
            this.secondClass = s;
        }
        else
        {
            this.firstClass = s;
            this.secondClass = f;
        }

        
    }

    private void incrementRelation()
    {
        this.numberOfRelation = this.numberOfRelation + 1;
    }   

    private void computeCouplingValue(int totalRelation)
    {
        if (totalRelation == 0)
        {
            this.couplingValue = 0.0;
        }
        else
        {
            this.couplingValue = (double)this.numberOfRelation / (double)totalRelation;
        }
    }

    private Double getCouplingValue()
    {
        return this.couplingValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;  
        }
        
        CouplingIdentificator other = (CouplingIdentificator) obj;
        return (this.firstClass.equals(other.firstClass) && this.secondClass.equals(other.secondClass)) || (this.firstClass.equals(other.secondClass) && this.secondClass.equals(other.firstClass));
    }
        
    @Override
    public int hashCode() {
        return firstClass.hashCode() + secondClass.hashCode();
    }




}
