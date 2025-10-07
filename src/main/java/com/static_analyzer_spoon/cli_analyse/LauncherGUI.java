package com.static_analyzer_spoon.cli_analyse;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
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
        //frame.add(textArea);
        frame.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane);
        textArea.setBounds(200, 200, 400, 600);

        // Set bounds for textArea to take the right side, full height
        scrollPane.setBounds(200, 0, 580, 560);
        frame.add(scrollPane);

        // Y position for buttons
        int buttonY = 20;
        int buttonHeight = 40;
        int buttonWidth = 180;
        int buttonX = 10;

        // Button: Nombre de classe visité
        JButton visiedClass = new JButton("Nombre de classe visité");
        visiedClass.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        visiedClass.addActionListener(e -> textArea.append("Nombre de classe visité: " + processorStaticAnalyze.getVisited() + "\n\n"));
        frame.add(visiedClass);
        buttonY += buttonHeight + 10;

        // Button: Nombre de package visité
        JButton visitedPackage = new JButton("Nombre de package visité");
        visitedPackage.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        visitedPackage.addActionListener(e -> textArea.append("Nombre de package visité: " + processorStaticAnalyze.getVisitedPackage() + "\n\n"));
        frame.add(visitedPackage);
        buttonY += buttonHeight + 10;

        // Button: Nombre de ligne de code
        JButton countLine = new JButton("Nombre de ligne de code");
        countLine.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        countLine.addActionListener(e -> textArea.append("Nombre de ligne de code" + processorStaticAnalyze.getCountLigne() + "\n\n"));
        frame.add(countLine);
        buttonY += buttonHeight + 10;

        // Button: Nombre de méthode
        JButton countMethod = new JButton("Nombre de méthode");
        countMethod.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        countMethod.addActionListener(e -> textArea.append("Nombre de méthode:" + processorStaticAnalyze.getCountMethod() + "\n\n"));
        frame.add(countMethod);
        buttonY += buttonHeight + 10;

        // Button: Nombre maximum de paramètre dans une méthode
        JButton maxParameter = new JButton("Nombre maximum de paramètre dans une méthode");
        maxParameter.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        maxParameter.addActionListener(e -> textArea.append("Nombre maximum de paramètre dans une méthode:" + processorStaticAnalyze.getMaxParametre() + "\n\n"));
        frame.add(maxParameter);
        buttonY += buttonHeight + 10;

        // Button: Moyenne de champ par classe
        JButton avgField = new JButton("Moyenne de champ par classe");
        avgField.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        avgField.addActionListener(e -> textArea.append("Moyenne des champs par classe: " + processorStaticAnalyze.getAvgField() + "\n\n"));
        frame.add(avgField);
        buttonY += buttonHeight + 10;

        // Button: Moyenne de ligne par méthode
        JButton avgLineMethod = new JButton("Moyenne de ligne par méthode");
        avgLineMethod.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        avgLineMethod.addActionListener(e -> textArea.append("Moyenne de ligne par méthode: " + processorStaticAnalyze.getAvgLigneMethod() + "\n\n"));
        frame.add(avgLineMethod);
        buttonY += buttonHeight + 10;

        // Button: Moyenne de méthode par classe
        JButton avgMethodClass = new JButton("Moyenne de méthode par classe");
        avgMethodClass.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        avgMethodClass.addActionListener(e -> textArea.append("Moyenne de méthode par classe: " + processorStaticAnalyze.getAvgMethodClass() + "\n\n"));
        frame.add(avgMethodClass);
        buttonY += buttonHeight + 10;
        // Button: Top 10% des classes avec le plus d'attributs
        JButton top10classByField = new JButton("Top 10% classes by field");
        top10classByField.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        top10classByField.addActionListener(e -> textArea.append(processorStaticAnalyze.getTOP10classByField() + "\n"));
        frame.add(top10classByField);
        buttonY += buttonHeight + 10;

            // Button: Top 10% des classes avec le plus de lignes de code
        JButton top10classByLine = new JButton("Top 10% classes by line");
        top10classByLine.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        top10classByLine.addActionListener(e -> {
            textArea.append(processorStaticAnalyze.getTOP10classByLigne() + "\n");
        });
        frame.add(top10classByLine);
        buttonY += buttonHeight + 10;

        // Button: Top 10% des classes avec le plus de méthodes
        JButton top10classByMethod = new JButton("Top 10% classes by method");
        top10classByMethod.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        top10classByMethod.addActionListener(e -> textArea.append(processorStaticAnalyze.getTOP10classByMethod() + "\n"));
        frame.add(top10classByMethod);
        buttonY += buttonHeight + 10;

        // Button: Top 10% des classes avec le plus de méthodes et d'attributs
        JButton top10classByFieldAndMethod = new JButton("Top 10% classes by field & method");
        top10classByFieldAndMethod.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        top10classByFieldAndMethod.addActionListener(e -> textArea.append(processorStaticAnalyze.getTOP10classByFieldAndMethod() + "\n"));
        frame.add(top10classByFieldAndMethod);
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
