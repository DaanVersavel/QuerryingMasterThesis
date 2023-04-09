package org.thesis;

import java.util.List;

public class QuerryTask implements Runnable {

    private List<Querry> queryList;
    private final double startTime;
    private Graph graph;

    public QuerryTask(List<Querry> querryList,double startTime, Graph graph) {
        this.queryList = querryList;
        this.startTime = startTime;
        this.graph = graph;
    }
    @Override
    public void run() {
        System.out.println("Start with query of time "+startTime);
        for(int i = 0; i < queryList.size(); i++){
            //if(i%200 ==0) System.out.println(i);
            Querry querry = queryList.get(i);
            long startNodeId = querry.getStartId();
            long endNodeId = querry.getEndId();
            querry.setStartTime(startTime);

            double estimation = graph.doNormalEstimation(startNodeId, endNodeId, startTime);
            querry.setEstimatedTravelTime(estimation);

            TimeDependentDijkstra timeDependentDijkstra = new TimeDependentDijkstra(graph);
            double timeDependantTime = timeDependentDijkstra.solveDijkstraTimeDependant(startNodeId, endNodeId, startTime);

            querry.setTimeDependantTravelTime(timeDependantTime);
        }
        graph.addQuerryList(startTime,queryList);
        System.out.println("Done with query of time "+startTime);

    }
}
