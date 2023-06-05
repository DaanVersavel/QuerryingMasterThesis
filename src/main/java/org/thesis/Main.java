package org.thesis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        String filePathGraph = args[0];
        String filePathQuerries = args[1];
        int numberOfThreads = Integer.parseInt(args[3]);
        boolean enable = Boolean.parseBoolean(args[4]);

        List<Double> times = new ArrayList<>();
        for (int i = 5; i < args.length; i++) {
            times.add(Double.parseDouble(args[i]));
        }

        Input input = new Input();
        input.inputGraph(filePathGraph);
        input.inputQuerrys(filePathQuerries);
        Graph graph = input.getGraph();
        List<Querry> querryList = input.getQuerryList();


        String outputFilePath = args[2] + "-" + graph.getCellMap().size();
        if (!enable) outputFilePath = outputFilePath + "-NC";


        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        for (double startTime : times) {
            executor.execute(new QuerryTask(copyQuerrylist(querryList), graph, startTime, enable));
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
            try {
                executor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                // Handle interruption if necessary
            }
        }

        Output output = new Output(graph.getQuerysListMap());
        System.out.println("Start writing to file");
        output.writeToFile(outputFilePath);
        System.out.println("Done");
    }

    private static List<Querry> copyQuerrylist(List<Querry> querryList) {
        List<Querry> newList = new ArrayList<>();
        for (Querry querry : querryList) {
            newList.add(new Querry(querry.getStartId(), querry.getEndId(), querry.getDijkstraTravelTime()));
        }
        return newList;
    }
}