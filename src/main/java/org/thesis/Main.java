package org.thesis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
//        String filePathGraph = args[0];
//        String filePathQuerries = args[1];
//        int numberOfThreads = Integer.parseInt(args[3]);

        String filePathGraph = "C:/Users/daanv/Desktop/Aalst-Querrys.json";
        String filePathQuerries = "D:/School/2022-2023/Masterproef/j/JarThesis/querrys/Aalst-Querrys.json";
        String outputFilePath = "test-64";
        int numberOfThreads =9;

//        List<Double> times = new ArrayList<>();
//                for(int i= 4; i<args.length; i++){
//            times.add(Double.parseDouble(args[i]));
//        }


        Input input = new Input();
        input.inputGraph(filePathGraph);
        input.inputQuerrys(filePathQuerries);
        Graph graph = input.getGraph();
        List<Querry> querryList = input.getQuerryList();


        //String outputFilePath = args[2]+"-"+ graph.getCellMap().size();


//        for(int i = 0; i < querryList.size(); i++){
//            if(i%100 ==0) System.out.println(i);
//            Querry querry = querryList.get(i);
//            long startNodeId = querry.getStartId();
//            long endNodeId = querry.getEndId();
//            double startTime = querry.getStartTime();
//
//            double estimation = graph.doEstimation(startNodeId, endNodeId, startTime);
//            querry.setEstimatedTravelTime(estimation);
//
//            TimeDependentDijkstra timeDependentDijkstra = new TimeDependentDijkstra(graph);
//            double timeDependantTime = timeDependentDijkstra.solveDijkstraTimeDependant(startNodeId, endNodeId, startTime);
//
//            querry.setTimeDependantTravelTime(timeDependantTime);
//
//        }

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        List<Double> times = getTimes();

        for(double startTime : times){
            executor.execute(new QuerryTask(copyQuerrylist(querryList),startTime,graph));
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
            try {
                executor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                // Handle interruption if necessary
            }
        }


//        Output output = new Output(querryList);
//        output.writeToFile(outputFilePath);
//        System.out.println("Done");
        Output2 output2 = new Output2(graph.getQuerysListMap());
        System.out.println("Start writing to file");
        output2.writeToFile(outputFilePath);
        System.out.println("Done");

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

    private static List<Querry> copyQuerrylist(List<Querry> querryList) {
        List<Querry> newList = new ArrayList<>();
        for(Querry querry : querryList){
            newList.add(new Querry(querry.getStartId(), querry.getEndId(),querry.getDijkstreTravelTime()));
        }
        return newList;
    }

    private static List<Double> getTimes() {
        List<Double> times = new ArrayList<>();
        times.add(0.0);
        times.add(4*3600.0);
        times.add(7*3600.0);
        times.add(8*3600.0);
        times.add(9*3600.0);
        times.add(12*3600.0);
        times.add(13*3600.0);
        times.add(16*3600.0);
        times.add(17*3600.0);
        times.add(18*3600.0);
        times.add(19*3600.0);
        times.add(21*3600.0);
        times.add(22*3600.0);
        return times;
    }
    //0 14400 25200 28800 32400 43200 46800 57600 61200 64800 68400 75600 79200
}