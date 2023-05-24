package org.thesis;

import java.util.List;

public class QuerryTask implements Runnable {

    private List<Querry> queryList;
    private final double startTime;
    private boolean enable;
    private Graph graph;

    public QuerryTask(List<Querry> querryList, Graph graph, double startTime, boolean enable) {
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

            double factor = graph.getFactor(startNodeId, endNodeId,startTime);
            double result = factor* querry.getDijkstraTravelTime();
            querry.setEstimatedTravelTime(result);

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
