package org.thesis;

import java.util.List;

public class QuerryTask implements Runnable {

    private final List<Querry> queryList;
    private final double startTime;
    private Graph graph;
    private final boolean enable;


    public QuerryTask(List<Querry> querryList, double startTime, Graph graph, boolean enable) {
        this.queryList = querryList;
        this.startTime = startTime;
        this.graph = graph;
        this.enable = enable;

    }
    @Override
    public void run() {
        System.out.println("Start with query of time "+startTime);
        for(int i = 0; i < queryList.size(); i++){
            Querry querry = queryList.get(i);
            long startNodeId = querry.getStartId();
            long endNodeId = querry.getEndId();
            querry.setStartTime(startTime);

            double estimation = graph.doEstimation(startNodeId, endNodeId, startTime);
            querry.setEstimatedTravelTime(estimation);

            if(enable){
                TimeDependentDijkstra timeDependentDijkstra = new TimeDependentDijkstra(graph);
                double timeDependantTime = timeDependentDijkstra.solveDijkstraTimeDependant(startNodeId, endNodeId, startTime);
                querry.setTimeDependantTravelTime(timeDependantTime);
            }
        }
        graph.addQuerryList(startTime,queryList);
        System.out.println("Done with query of time "+startTime);
    }
}
