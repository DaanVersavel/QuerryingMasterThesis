package org.thesis;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {

    private Graph graph;

    public Dijkstra(Graph graph) {
       this.graph=graph;
    }

    //return map of shortest time to landmarks
    public double solveDijkstra(long startId, long endId){
        PriorityQueue<NodeParser> pq = new PriorityQueue<>(new NodeComparator());
        Map<Long,Double> shortestTimeMap = new HashMap<>();
        Map<Long,NodeParser> nodeMap = new HashMap<>();

        for(NodeParser node : graph.getNodesMap().values()){
            shortestTimeMap.put(node.getOsmId(),Double.MAX_VALUE);
            nodeMap.put(node.getOsmId(),new NodeParser(node));         //Copy of node
            nodeMap.get(node.getOsmId()).setCurrenCost(Double.MAX_VALUE);
        }
        pq.addAll(nodeMap.values());

        shortestTimeMap.put(startId,0.0);
        NodeParser tempNode = nodeMap.get(startId);
        tempNode.setCurrenCost(0.0);
        pq.remove(nodeMap.get(startId));
        pq.add(tempNode);

        //dijkstra algorithm
        for(int i = 1; i <= nodeMap.size(); i++) {
            //shortest time search
            NodeParser removedNode= pq.remove();
            if(shortestTimeMap.get(removedNode.getOsmId())==Double.MAX_VALUE){
                System.out.println("Node met current cost max Dijkstra");
                break;
            }
            //update the adjacent node-time
            for(EdgeParser edge: removedNode.getOutgoingEdges()){
                //when reaching the node
                double travelTimeAtNode = shortestTimeMap.get(edge.getBeginNodeOsmId())+ edge.getDefaultTravelTime();
                //If better time update time and readd to pq
                if(travelTimeAtNode<shortestTimeMap.get(edge.getEndNodeOsmId())){
                    shortestTimeMap.put(edge.getEndNodeOsmId(),travelTimeAtNode);
                    NodeParser tempnode=nodeMap.get(edge.getEndNodeOsmId());
                    tempnode.setCurrenCost(travelTimeAtNode);
                    if(pq.remove(tempnode)){
                        pq.add(tempnode);
                    }
                }
            }
            if(removedNode.getOsmId()==endId)break;
        }
        return shortestTimeMap.get(endId);
    }
}
