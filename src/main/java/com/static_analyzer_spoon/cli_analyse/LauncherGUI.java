package com.static_analyzer_spoon.cli_analyse;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.static_analyzer_spoon.visitor.GraphMethode;

public class LauncherGUI extends AbsractLauncher{
    
    public LauncherGUI (String pathString, boolean forMavenInput) {
        path = pathString;
        forMaven = forMavenInput;
        if (forMavenInput) {
            LauncherGUI.analyseforMaven();    
        }
        else {
            LauncherGUI.analyse();
        }

        System.out.println("Analyze finished and env setup");

        

        JFrame frame = new JFrame("Static Analyzer GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        JTextArea textArea = new JTextArea();
        textArea.setBounds(200, 200, 300, 400);
        frame.add(textArea);
        frame.setLayout(null);

        // Set bounds for textArea to take the right side, full height
        textArea.setBounds(200, 0, 580, 560);
        frame.add(textArea);

        // Y position for buttons
        int buttonY = 20;
        int buttonHeight = 40;
        int buttonWidth = 180;
        int buttonX = 10;

        // Button: Nombre de classe visité
        JButton visiedClass = new JButton("Nombre de classe visité");
        visiedClass.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        visiedClass.addActionListener(e -> textArea.append(String.valueOf(processorStaticAnalyze.getVisited()) + "\n"));
        frame.add(visiedClass);
        buttonY += buttonHeight + 10;

        // Button: Nombre de package visité
        JButton visitedPackage = new JButton("Nombre de package visité");
        visitedPackage.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        visitedPackage.addActionListener(e -> textArea.append(String.valueOf(processorStaticAnalyze.getVisitedPackage()) + "\n"));
        frame.add(visitedPackage);
        buttonY += buttonHeight + 10;

        // Button: Nombre de ligne de code
        JButton countLine = new JButton("Nombre de ligne de code");
        countLine.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        countLine.addActionListener(e -> textArea.append(String.valueOf(processorStaticAnalyze.getCountLigne()) + "\n"));
        frame.add(countLine);
        buttonY += buttonHeight + 10;

        // Button: Nombre de méthode
        JButton countMethod = new JButton("Nombre de méthode");
        countMethod.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        countMethod.addActionListener(e -> textArea.append(String.valueOf(processorStaticAnalyze.getCountMethod()) + "\n"));
        frame.add(countMethod);
        buttonY += buttonHeight + 10;

        // Button: Nombre maximum de paramètre dans une méthode
        JButton maxParameter = new JButton("Nombre maximum de paramètre dans une méthode");
        maxParameter.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        maxParameter.addActionListener(e -> textArea.append(String.valueOf(processorStaticAnalyze.getMaxParametre()) + "\n"));
        frame.add(maxParameter);
        buttonY += buttonHeight + 10;

        // Button: Moyenne de champ par classe
        JButton avgField = new JButton("Moyenne de champ par classe");
        avgField.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        avgField.addActionListener(e -> textArea.append(String.valueOf(processorStaticAnalyze.getAvgField()) + "\n"));
        frame.add(avgField);
        buttonY += buttonHeight + 10;

        // Button: Moyenne de ligne par méthode
        JButton avgLineMethod = new JButton("Moyenne de ligne par méthode");
        avgLineMethod.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        avgLineMethod.addActionListener(e -> textArea.append(String.valueOf(processorStaticAnalyze.getAvgLigneMethod()) + "\n"));
        frame.add(avgLineMethod);
        buttonY += buttonHeight + 10;

        // Button: Moyenne de méthode par classe
        JButton avgMethodClass = new JButton("Moyenne de méthode par classe");
        avgMethodClass.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        avgMethodClass.addActionListener(e -> textArea.append(String.valueOf(processorStaticAnalyze.getAvgMethodClass()) + "\n"));
        frame.add(avgMethodClass);
        buttonY += buttonHeight + 10;

        // Button: Show Call Graph
        JButton graphButton = new JButton("Show Call Graph");
        graphButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        graphButton.addActionListener(e -> GraphMethode.visualize());
        frame.add(graphButton);

                
        
        
        
        
        
        
        frame.setLayout(null);
        frame.setVisible(true);



        
        
    }

}
