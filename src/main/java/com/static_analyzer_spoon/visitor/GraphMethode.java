package com.static_analyzer_spoon.visitor;

import java.util.Collection;
import java.util.HashMap;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

public class GraphMethode {

    private static HashMap<String, Collection<String>> mapClassLigne = new java.util.HashMap<>();

    public static void add(String methode, String newMethodMethode)
    {
        if (mapClassLigne.containsKey(methode)) 
        {
            Collection<String> list = mapClassLigne.get(methode);
            list.add(newMethodMethode);
            mapClassLigne.put(methode, list);
        } else 
        {
            Collection<String> listMethode = new java.util.ArrayList<>();
            listMethode.add(newMethodMethode);
            mapClassLigne.put(methode, listMethode);
        }
    }

    public static void show()
    {
        for (String key : mapClassLigne.keySet()) {
            System.out.println("Methode : " + key + " calls " + mapClassLigne.get(key).toString());
        }
    }

    

public static void visualize() {
    System.setProperty("org.graphstream.ui", "swing"); // Pour forcer l'affichage en Swing

    Graph graph = new SingleGraph("Call Graph");
    graph.setAttribute("ui.quality");
    graph.setAttribute("ui.antialias");

    


    for (String from : mapClassLigne.keySet()) {
        if (graph.getNode(from) == null) {
            graph.addNode(from).setAttribute("ui.label", from);
        }
    
        for (String to : mapClassLigne.get(from)) {
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




}
