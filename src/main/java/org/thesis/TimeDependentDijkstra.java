package org.thesis;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TimeDependentDijkstra {

    private Graph graph;

    public TimeDependentDijkstra(Graph graph){
        this.graph = graph;
    }


    public double solveDijkstraTimeDependant(long startNode, long endNodeId,double startTime){
        PriorityQueue<NodeParser> pq = new PriorityQueue<>(new NodeComparator());
        //Offset from starting time reason why the value for startnode is 0
        Map<Long,Double> shortestTimeMap = new HashMap<>();

        Map<Long,NodeParser> nodeMap = new HashMap<>();

        for(NodeParser node : graph.getNodesMap().values()){
            shortestTimeMap.put(node.getOsmId(),Double.MAX_VALUE);
            nodeMap.put(node.getOsmId(),new NodeParser(node));//Copy of node
            nodeMap.get(node.getOsmId()).setCurrenCost(Double.MAX_VALUE);
        }
        pq.addAll(nodeMap.values());

        shortestTimeMap.put(startNode,0.0);
        NodeParser tempNode = nodeMap.get(startNode);
        tempNode.setCurrenCost(0.0);
        pq.remove(nodeMap.get(startNode));
        pq.add(tempNode);


        //dijkstra algorithm
        for (int i = 1; i <= shortestTimeMap.size(); i++) {
            //shortest time search
            NodeParser removedNode= pq.remove();
            if(shortestTimeMap.get(removedNode.getOsmId())==Double.MAX_VALUE){
                System.out.println("ERROR Double met Max Value");
                break;
            }

            //update the adjacent node-time
            double currenTimeAtNode=startTime+shortestTimeMap.get(removedNode.getOsmId());
            for(EdgeParser edge: removedNode.getOutgoingEdges()){
                //when reaching the node
                double travelTimeToNextEdge = shortestTimeMap.get(edge.getBeginNodeOsmId()) +edge.getTravelTime(currenTimeAtNode,graph.getSpeedMatrixMap());
                //If better time update time and read to pq
                if(currenTimeAtNode+travelTimeToNextEdge<shortestTimeMap.get(edge.getEndNodeOsmId())){
                    shortestTimeMap.put(edge.getEndNodeOsmId(),travelTimeToNextEdge);
                    NodeParser tempnode=nodeMap.get(edge.getEndNodeOsmId());
                    tempnode.setCurrenCost(travelTimeToNextEdge);
                    if(pq.remove(tempnode)){
                        pq.add(tempnode);
                    }
                }
            }
            if(removedNode.getOsmId()==endNodeId)break;
        }

        return shortestTimeMap.get(endNodeId);
    }
}
