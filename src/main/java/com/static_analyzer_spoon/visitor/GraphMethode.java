package com.static_analyzer_spoon.visitor;

import java.util.Collection;
import java.util.HashMap;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

public class GraphMethode {

    private static HashMap<GraphNode, Collection<GraphNode>> mapClassLigne = new java.util.HashMap<>();
    private static HashMap<CouplingIdentificator, Double> mapCouplage = new java.util.HashMap<>();

    private static int fullRelationCount = 0;

    public static void add(GraphNode methode, GraphNode newMethodMethode)
    {
        if (mapClassLigne.containsKey(methode)) 
        {
            Collection<GraphNode> list = mapClassLigne.get(methode);
            list.add(newMethodMethode);
            mapClassLigne.put(methode, list);
            fullRelationCount = fullRelationCount + 1;
        } else 
        {
            Collection<GraphNode> listMethode = new java.util.ArrayList<>();
            listMethode.add(newMethodMethode);
            mapClassLigne.put(methode, listMethode);
            fullRelationCount = fullRelationCount + 1;
        }
    }

    public static void show()
    {
        for (GraphNode key : mapClassLigne.keySet()) {
            System.out.println("Methode : " + key + " calls " + mapClassLigne.get(key).toString());
        }
    }



    

    public static void visualize() {
        System.setProperty("org.graphstream.ui", "swing"); // Pour forcer l'affichage en Swing

        Graph graph = new SingleGraph("Call Graph");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

        for (GraphNode key : mapClassLigne.keySet()) {
            String from = key.toString();
            if (graph.getNode(from) == null) {
                graph.addNode(from).setAttribute("ui.label", from);
            }
        
            for (GraphNode node : mapClassLigne.get(key)) {
                String to = node.toString();
                if (graph.getNode(to) == null) {
                    graph.addNode(to).setAttribute("ui.label", to);
                }
        
                String edgeId = from + "->" + to;
                if (graph.getEdge(edgeId) == null) {
                    graph.addEdge(edgeId, from, to, true); // true = orienté
                }
            }
        }

        
        graph.setAttribute("ui.stylesheet", "node { text-mode: normal; fill-color: lightblue; text-color: black; size: 10px;  } node:hover { text-mode: normal; text-color: black; } edge { arrow-shape: arrow;fill-color:#222;arrow-size:5px,3px; } ");
        Viewer viewer = graph.display(false);
        ViewPanel viewPanel = (ViewPanel) viewer.getDefaultView();
        viewPanel.setPreferredSize(new java.awt.Dimension(2000, 2000));
        viewPanel.setVisible(true);

        viewPanel.resizeFrame(2000, 2000);
        viewPanel.getCamera().setViewCenter(0, 0, 0);



        viewer.enableAutoLayout();
    }

    private static void couplage(String firstclassName,String secondClassName){
        
        if (firstclassName.equals(secondClassName)) {
            return;
        }

        CouplingIdentificator couplage = new CouplingIdentificator(firstclassName, secondClassName);

        if (mapCouplage.containsKey(couplage))
        {
            return;
        }

        for (GraphNode key : mapClassLigne.keySet()) {
            if (key.getClassName().compareTo(firstclassName) == 0)
            {
                for (GraphNode node : mapClassLigne.get(key)) {
                    if (node.getClassName().compareTo(secondClassName) == 0)
                    {
                        couplage.incrementRelation();
                    }
                }
            }
            else if (key.getClassName().compareTo(secondClassName) == 0)
            {
                for (GraphNode node : mapClassLigne.get(key)) {
                    if (node.getClassName().compareTo(firstclassName) == 0)
                    {
                        couplage.incrementRelation();
                    }
                }
            }
        }
        mapCouplage.put(couplage, 0.0);
    }

    public static void computeAllCouplage()
    {
        for (GraphNode key : mapClassLigne.keySet()) {
            for (GraphNode node : mapClassLigne.get(key)) {
                couplage(key.getClassName(), node.getClassName());
            }
        }

        for (CouplingIdentificator couplage : mapCouplage.keySet())
        {
            couplage.computeCouplingValue(fullRelationCount);
            mapCouplage.put(couplage, couplage.getCouplingValue());
        }

        
    }

    public static String toStringCouplage()
    {
        String sb = "";

        for (CouplingIdentificator couplage : mapCouplage.keySet())
        {
            sb = sb + "Couplage between " + couplage.getFirstClass() + " and " + couplage.getsecondClass() + " : " + mapCouplage.get(couplage) + "\n";
        }
        return sb;
    }

    public static void visualizeCouplage()
    {
        System.setProperty("org.graphstream.ui", "swing"); // Pour forcer l'affichage en Swing

        Graph graph = new SingleGraph("Coupling graph");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");

        for (CouplingIdentificator identificator : mapCouplage.keySet())
        {
            String nodeA = identificator.getFirstClass();
            String nodeB = identificator.getsecondClass();
            if (graph.getNode(nodeA) == null) {
                graph.addNode(nodeA).setAttribute("ui.label", nodeA);
            }
            if (graph.getNode(nodeB) == null) {
                graph.addNode(nodeB).setAttribute("ui.label", nodeB);
            }

            String edgeId = nodeA + "-" + nodeB;
                if (graph.getEdge(edgeId) == null) {
                    graph.addEdge(edgeId, nodeA, nodeB, false).setAttribute("weight", mapCouplage.get(identificator)); // true = orienté
                    graph.getEdge(edgeId).setAttribute("ui.label", mapCouplage.get(identificator));
                }
        

        }
        graph.setAttribute("ui.stylesheet", "node { text-mode: normal; fill-color: lightblue; text-color: black; size: 10px;  } node:hover { text-mode: normal; text-color: black; } edge { arrow-shape: arrow;fill-color:#222;arrow-size:5px,3px; } ");
        Viewer viewer = graph.display(false);
        ViewPanel viewPanel = (ViewPanel) viewer.getDefaultView();
        viewPanel.setPreferredSize(new java.awt.Dimension(2000, 2000));
        viewPanel.setVisible(true);

        viewPanel.resizeFrame(2000, 2000);
        viewPanel.getCamera().setViewCenter(0, 0, 0);



        viewer.enableAutoLayout();



    }
    

    




}
