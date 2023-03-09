package org.thesis;

public class Main {
    public static void main(String[] args) {
        String filePathGraph = "src/main/java/org/thesis/Input/Aalst.json";
        String filePathQuerries = "src/main/java/org/thesis/Input/Aalst.json";
        Input input = new Input();
        input.inputGraph(filePathGraph);
        input.inputQuerrys(filePathQuerries);
        Graph graph = input.getGraph();





        long startNodeId = 9240416902L; //From Cel 0
        long endNodeId = 861543291L; //from cell 7
        double startTime = 7*3600;

        double estimation = graph.doEstimation(startNodeId, endNodeId, startTime);

        TimeDependentDijkstra timeDependentDijkstra = new TimeDependentDijkstra(graph);
        double timeDependantTime = timeDependentDijkstra.solveDijkstraTimeDependant(startNodeId, endNodeId, startTime);

        double factor = graph.getFactor(startNodeId, endNodeId, startTime);
        double difference = timeDependantTime - estimation;

        System.out.println(startNodeId + " ==> " + endNodeId + " at time " + startTime);
        System.out.println("Estimation is " + estimation + " seconds");
        System.out.println("TimeDependant calculation is " + timeDependantTime+ " seconds");
        System.out.println("Factor is " + factor);
        System.out.println("Difference in estimation is " + difference+ " seconds");

    }
}