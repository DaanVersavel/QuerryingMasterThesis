package org.thesis;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        String filePathGraph = args[0];
//        String filePathQuerries = args[1];
//        String outputFilePath = args[2];

        String filePathGraph = "src/main/java/org/thesis/Input/Aalst-preprocessing-9.json";
        String filePathQuerries = "src/main/java/org/thesis/Input/Aalst-Querrys.json";
        String outputFilePath = "Aalst";

        Input input = new Input();
        input.inputGraph(filePathGraph);
        input.inputQuerrys(filePathQuerries);
        Graph graph = input.getGraph();
        List<Querry> querryList = input.getQuerryList();


        for(int i = 0; i < querryList.size(); i++){
            Querry querry = querryList.get(i);
            long startNodeId = querry.getStartId();
            long endNodeId = querry.getEndId();
            double startTime = querry.getStartTime();

            double estimation = graph.doEstimation(startNodeId, endNodeId, startTime);
            querry.setEstimatedTravelTime(estimation);

            TimeDependentDijkstra timeDependentDijkstra = new TimeDependentDijkstra(graph);
            double timeDependantTime = timeDependentDijkstra.solveDijkstraTimeDependant(startNodeId, endNodeId, startTime);

            querry.setTimeDependantTravelTime(timeDependantTime);

        }
        Output output = new Output(querryList);
        output.writeToFile(outputFilePath);
        System.out.println("Done");





//        long startNodeId = 9240416902L; //From Cel 0
//        long endNodeId = 861543291L; //from cell 7
//        double startTime = 7*3600;
//
//        double estimation = graph.doEstimation(startNodeId, endNodeId, startTime);
//
//        TimeDependentDijkstra timeDependentDijkstra = new TimeDependentDijkstra(graph);
//        double timeDependantTime = timeDependentDijkstra.solveDijkstraTimeDependant(startNodeId, endNodeId, startTime);
//
//        double factor = graph.getFactor(startNodeId, endNodeId, startTime);
//        double difference = timeDependantTime - estimation;






//
//        System.out.println(startNodeId + " ==> " + endNodeId + " at time " + startTime);
//        System.out.println("Estimation is " + estimation + " seconds");
//        System.out.println("TimeDependant calculation is " + timeDependantTime+ " seconds");
//        System.out.println("Factor is " + factor);
//        System.out.println("Difference in estimation is " + difference+ " seconds");

    }
}