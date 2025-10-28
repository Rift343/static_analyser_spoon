package com.static_analyzer_spoon.cli_analyse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import com.static_analyzer_spoon.dendrogramme.Cluster;
import com.static_analyzer_spoon.dendrogramme.Dendrogramme;
import com.static_analyzer_spoon.visitor.CouplingIdentificator;
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
        textArea.setBounds(200, 200, 600, 600);
        //frame.add(textArea);
        frame.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane);
        textArea.setBounds(200, 200, 1200, 900);

        // Set bounds for textArea to take the right side, full height
        scrollPane.setBounds(200, 0, 1200, 900);
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
        buttonY += buttonHeight + 10;

        // Button: Coupling string
        JButton CouplingStringButton = new JButton("Show compling value");
        CouplingStringButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        CouplingStringButton.addActionListener(e -> textArea.append(GraphMethode.toStringCouplage()+"\n"));
        frame.add(CouplingStringButton);
        buttonY += buttonHeight + 10;

        // Button: Show coupling Graph
        JButton graphCouplingButton = new JButton("Show coupling Graph");
        graphCouplingButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        graphCouplingButton.addActionListener(e -> GraphMethode.visualizeCouplage());
        frame.add(graphCouplingButton);
        buttonY += buttonHeight + 10;
        HashMap<CouplingIdentificator, Double> mapCouplage = GraphMethode.getMapCouplage();
        
        // Button: Show dendogram
        JButton dendogram = new JButton("Show dendogram");
        dendogram.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
            dendogram.addActionListener(e -> {
            // Étape 1 : Initialiser les clusters
            
            
            showDendrogram(Dendrogramme.getRootCluster());
        });

        frame.add(dendogram);
        buttonY += buttonHeight + 10;

        //show module
        JLabel cpLabel = new JLabel("Seuil CP :");
        cpLabel.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        frame.add(cpLabel);

        buttonY += buttonHeight + 10;

        JTextField cpField = new JTextField("0.05"); // valeur par défaut
        cpField.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        frame.add(cpField);
        buttonY += buttonHeight + 10;

        JButton showModulesButton = new JButton("Afficher le dendrogramme");
        showModulesButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        frame.add(showModulesButton);

        showModulesButton.addActionListener(e -> {
            afficherModulesExtraits(Double.parseDouble(cpField.getText()), mapCouplage, Dendrogramme.getRootCluster());
        });

        

        
        
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void showDendrogram(Cluster rootCluster) {
        DefaultMutableTreeNode rootNode = buildTreeNode(rootCluster);
        JTree tree = new JTree(rootNode);

        JScrollPane scrollPane = new JScrollPane(tree);
        JFrame frame = new JFrame("Dendrogramme des classes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static DefaultMutableTreeNode buildTreeNode(Cluster cluster) {
        // Crée un label avec les noms de classes et le score de couplage
        String label = "";    
        if (cluster.isLeaf()) {
            label = String.join("",cluster.getClassNames());
        }

        if (!cluster.isLeaf()){
            label += " [score=" + String.format("%.6f", cluster.getAverageCoupling()) + "]";
        }
        

        // Crée le nœud Swing
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(label);

        // Ajoute récursivement les sous-clusters
        if (cluster.getLeft() != null) {
            node.add(buildTreeNode(cluster.getLeft()));
        }
        if (cluster.getRight() != null) {
            node.add(buildTreeNode(cluster.getRight()));
        }

        return node;
    }

    public Map<String, String> assignColors(List<Cluster> modules) {
        Map<String, String> classToColor = new HashMap<>();
        List<String> palette = Arrays.asList(
            "red", "blue", "green", "orange", "purple", "cyan", "magenta", "yellow", "gray", "brown"
        );

        int colorIndex = 0;

        for (Cluster module : modules) {
            String color = palette.get(colorIndex % palette.size());
            for (String className : module.getClassNames()) {
                classToColor.put(className, color);
            }
            colorIndex++;
        }

        return classToColor;
    }

    public void afficherModulesExtraits(double cp, Map<CouplingIdentificator, Double> mapCouplage, Cluster racine) {
        int maxModules = mapCouplage.size() / 2;
        List<Cluster> modules = Dendrogramme.extractModules(mapCouplage, cp, maxModules);
        Map<String, String> classToColor = assignColors(modules);

        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Modules extraits");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

        for (Cluster module : modules) {
            Set<String> classes = module.getClassNames();
            String color = classToColor.get(classes.iterator().next());
            List<String> classList = new ArrayList<>(classes);

            for (String className : classList) {
                if (graph.getNode(className) == null) {
                    Node a = graph.addNode(className);
                    a.setAttribute("ui.label", className);
                    a.setAttribute("ui.style", "fill-color: " + color + ";");
                }
            }

            for (int i = 0; i < classList.size(); i++) {
                for (int j = i + 1; j < classList.size(); j++) {
                    String classA = classList.get(i);
                    String classB = classList.get(j);
                    CouplingIdentificator idAB = new CouplingIdentificator(classA, classB);
                    CouplingIdentificator idBA = new CouplingIdentificator(classB, classA);
                    Double score = mapCouplage.getOrDefault(idAB, mapCouplage.get(idBA));
                    if (score != null) {
                        String edgeId = classA + "--" + classB;
                        if (graph.getEdge(edgeId) == null) {
                            Edge edge = graph.addEdge(edgeId, classA, classB);
                            edge.setAttribute("ui.label", score);
                        }
                    }
                }
            }
        }
        
        List<Node> toRemove = new ArrayList<>();
        for (Node node : graph) {
            if (node.getDegree() == 0) {
                toRemove.add(node);
            }
        }
        for (Node node : toRemove) {
            graph.removeNode(node);
        } 


        graph.setAttribute("ui.stylesheet",
            "node { text-mode: normal; size: 10px; text-color: black; }" +
            "node:hover { text-mode: normal; text-color: black; }" +
            "edge { arrow-shape: arrow; fill-color: #222; arrow-size: 5px, 3px; }"
        );

        Viewer viewer = graph.display(false);
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
        viewer.enableAutoLayout();
    }




}
