package com.static_analyzer_spoon.dendrogramme;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.static_analyzer_spoon.Processor.ProcessorStaticAnalyze;
import com.static_analyzer_spoon.visitor.CouplingIdentificator;
import com.static_analyzer_spoon.visitor.GraphMethode;

public class Dendrogramme {

    static List<Cluster> clustersD;
    List<Cluster> modules;

    public Dendrogramme(Map<CouplingIdentificator, Double> mapCouplage, double CP, int maxModules) {
        Set<String> allClassNames = GraphMethode.extractAllClassNames(mapCouplage);

        List<Cluster> clusters = new ArrayList<>();
        for (String className : allClassNames) {
            clusters.add(new Cluster(className)); // constructeur avec une seule classe
        }

        this.clustersD = clusters;
        hierarchicalClustering(mapCouplage);
        this.modules = extractModules(mapCouplage, CP, maxModules);

    }

    private List<Cluster> extractModules(Map<CouplingIdentificator, Double> mapCouplage, double CP, int maxModules) {
        Cluster root = clustersD.get(0); // racine du dendrogramme
        List<Cluster> modules = new ArrayList<>();
        Queue<Cluster> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty() && modules.size() < maxModules) {
            Cluster current = queue.poll();
            double score = ProcessorStaticAnalyze.computeAverageCoupling(current.getClassNames(), mapCouplage);

            if (score >= CP || current.isLeaf()) {
                modules.add(current);
            } else {
                if (current.getLeft() != null) queue.add(current.getLeft());
                if (current.getRight() != null) queue.add(current.getRight());
            }
        }
        return modules;
    }

    private void hierarchicalClustering(Map<CouplingIdentificator, Double> mapCouplage) {
        while (clustersD.size() > 1) {
            double maxCoupling = -1;
            Cluster bestA = null, bestB = null;

            for (int i = 0; i < clustersD.size(); i++) {
                for (int j = i + 1; j < clustersD.size(); j++) {
                    double coupling = ProcessorStaticAnalyze.computeAverageCoupling(clustersD.get(i), clustersD.get(j), mapCouplage);
                    if (coupling > maxCoupling) {
                        maxCoupling = coupling;
                        bestA = clustersD.get(i);
                        bestB = clustersD.get(j);
                    }
                }
            }

            Cluster merged = new Cluster(bestA, bestB, maxCoupling);
            clustersD.remove(bestA);
            clustersD.remove(bestB);
            clustersD.add(merged);
        }
        // contient un seul cluster racine = dendrogramme
    }

    public List<Cluster> getModules() {
        return modules;
    }

    public static Cluster getRootCluster() {
        return clustersD.get(0);
    }

}
