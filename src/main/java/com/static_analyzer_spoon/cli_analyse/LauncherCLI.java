package com.static_analyzer_spoon.cli_analyse;

import java.util.Scanner;

import com.static_analyzer_spoon.visitor.GraphMethode;


public class LauncherCLI extends AbsractLauncher{

    

    public LauncherCLI (String pathString, boolean forMavenInput) {
        path = pathString;
        forMaven = forMavenInput;
        if (forMavenInput) {
            LauncherCLI.analyseforMaven();    
        }
        else {
            LauncherCLI.analyse();
        }

        Scanner scannerCLI = new Scanner(System.in);
        String input = "";
        
        System.out.println("Analyze finished and env setup");
        
        //Une petite pause pour laisser le temps à l'utilisateur de lire le message
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        while (input.equals("exit")==false) {
            System.out.println("");
            System.out.println("");
            System.out.println("");//all possible command
            System.out.println("+-------------------------------------------------------------------------------------------------------------+");
            System.out.println("|You can now use the following commands :                                                                     |");
            System.out.println("|visitedClass : show the number of visited class                                                              |");
            System.out.println("|visitedPackage : show the number of visited package                                                          |");
            System.out.println("|countLigne : show the number of code line                                                                    |");
            System.out.println("|countMethod : show the number of method                                                                      |");
            System.out.println("|maxParameter : show the maximum number of parameter in a method                                              |");
            System.out.println("|avgField : show the average number of field by class                                                         |");
            System.out.println("|avgLigneMethod : show the average number of line by method                                                   |");
            System.out.println("|avgMethodeClass : show the average number of method by class                                                 |");
            System.out.println("|top10classByField : show the top 10% of class with the most field                                            |");
            System.out.println("|top10classByLigne : show the top 10% of class with the most line of code                                     |");
            System.out.println("|top10classByMethod : show the top 10% of class with the most method                                          |");
            System.out.println("|top10classByFieldAndMethod : show the top 10% of class with the most method and field                        |");
            System.out.println("|withMoreThanXMethod : show the class with more than X method (you will be asked to enter the value of X)     |");
            System.out.println("|graph : show the call graph of the methods (which method calls which other method)                           |");
            System.out.println("|graphGUI : show the call graph of the methods in a GUI (which method calls which other method)               |");
            System.out.println("|exit : exit the program                                                                                      |");
            System.out.println("+-------------------------------------------------------------------------------------------------------------+");
            System.out.println("Enter your command : ");//command input
            input = scannerCLI.nextLine();
            System.out.println("");
            System.out.println("");
            System.out.println("");

            try {
                if (System.getProperty("os.name").toLowerCase().contains("win")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                }
            } catch (Exception e) {
                // Ignore errors, just continue
            }
            
            switch (input) {

                case "visitedClass":
                    processorStaticAnalyze.showVisitedClass();
                    break;
                case "visitedPackage":
                    processorStaticAnalyze.showVisitedPackage();
                    break;
                case "countLigne":
                    processorStaticAnalyze.showCountLigne();
                    break;
                case "countMethod":
                    processorStaticAnalyze.showCountMethod();
                    break;
                case "maxParameter":
                    processorStaticAnalyze.showMaxParameter();
                    break;
                case "avgField":
                    processorStaticAnalyze.showAVGfield();
                    break;
                case "avgLigneMethod":
                    processorStaticAnalyze.showAVGligneMethod();
                    break;
                case "avgMethodeClass":
                    processorStaticAnalyze.showAVGmethodeClass();
                    break;
                case "top10classByField":
                    processorStaticAnalyze.showTOP10classByField();
                    break;
                case "top10classByLigne":
                    processorStaticAnalyze.showTOP10classByLigne();
                    break;
                case "top10classByMethod":
                    processorStaticAnalyze.showTOP10classByMethod();
                    break;
                case "top10classByFieldAndMethod":
                    processorStaticAnalyze.showTOP10classByFieldAndMethod();
                    break;
                case "withMoreThanXMethod":
                    System.out.println("Enter the number of method X : ");
                    String xString = scannerCLI.nextLine();
                    try {
                        int x = Integer.parseInt(xString);
                        processorStaticAnalyze.showClassesWithMoreThanXMethods(x);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                    }
                    break;
                case "graph":
                    GraphMethode.show();
                case "graphGUI":
                    GraphMethode.visualize();;
            
                default:
                    break;
            }
        }
        scannerCLI.close();
        
        System.out.println("End of the program");  
        
        
    }
}
