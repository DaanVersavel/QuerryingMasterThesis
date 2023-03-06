package org.thesis;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/java/org/thesis/Input/Aalst.json";
        Input input = new Input(filePath);
        Graph graph =input.getGraph();
        System.out.println();
    }
}