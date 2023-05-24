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


//        String filePathGraph = "D:/Onedrives/OneDrive - KU Leuven/2022-2023/Masterproof/Testen/preprocessing/random/16-cell/Aalst-preprocessing-16R.json";
//        String filePathQuerries = "D:/Onedrives/OneDrive - KU Leuven/2022-2023/Masterproof/Testen/querrys/Aalst-Querrys.json";
//        String outputFilePath = "AalstC-9";
//        int numberOfThreads = 9;
//        boolean enable = true;

        List<Double> times = new ArrayList<>();
                for(int i= 5; i<args.length; i++){
            times.add(Double.parseDouble(args[i]));
        }

        Input input = new Input();
        input.inputGraph(filePathGraph);
        input.inputQuerrys(filePathQuerries);
        Graph graph = input.getGraph();
        List<Querry> querryList = input.getQuerryList();


        String outputFilePath = args[2]+"-"+ graph.getCellMap().size();
        if(!enable) outputFilePath = outputFilePath+"-NC";



        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        //List<Double> times = getTimes();

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
        for(Querry querry : querryList){
            newList.add(new Querry(querry.getStartId(), querry.getEndId(),querry.getDijkstraTravelTime()));
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