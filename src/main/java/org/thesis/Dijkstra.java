package org.thesis;

import java.util.*;

public class Dijkstra {

    private Graph graph;
    private Map<Long,Long> parentMap;
    private Map<Long,Double> shortestTimeMap;
    private List<NodeParser> path;
    private Set<Long> passingCellSet;

    public Dijkstra(Graph graph) {
       this.graph=graph;
    }

    //return map of shortest time to land marks
    public double solveDijkstra(long startNode, long endNodeId){
        PriorityQueue<NodeParser> pq = new PriorityQueue<>(new NodeComparator());
        Map<Long,NodeParser> nodeMap = new HashMap<>();
        parentMap = new HashMap<>();
        shortestTimeMap = new HashMap<>();



        for(NodeParser node : graph.getNodesMap().values()){
            shortestTimeMap.put(node.getOsmId(),Double.MAX_VALUE);
            nodeMap.put(node.getOsmId(),new NodeParser(node));         //Copy of node
            nodeMap.get(node.getOsmId()).setCurrenCost(Double.MAX_VALUE);
            parentMap.put(node.getOsmId(),-1L);
        }
        pq.addAll(nodeMap.values());


        shortestTimeMap.put(startNode,0.0);
        NodeParser tempNode = nodeMap.get(startNode);
        tempNode.setCurrenCost(0.0);
        pq.remove(nodeMap.get(startNode));
        pq.add(tempNode);


        //dijkstra algorithm
        for (int i = 1; i <= nodeMap.size(); i++) {
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
                    parentMap.put(edge.getEndNodeOsmId(), removedNode.getOsmId());
                    NodeParser tempnode=nodeMap.get(edge.getEndNodeOsmId());
                    tempnode.setCurrenCost(travelTimeAtNode);
                    if(pq.remove(tempnode)){
                        pq.add(tempnode);
                    }
                }
            }
            if(removedNode.getOsmId()==endNodeId)break;
        }

        return shortestTimeMap.get(endNodeId);
    }
    public void calculatePath(long begin, long end){
        path=new ArrayList<>();
        passingCellSet = new HashSet<>();

        while(end!=begin){
            path.add(graph.getNodesMap().get(end));
            passingCellSet.add(graph.getNodesMap().get(end).getCellID());
            end = parentMap.get(end);
        }
        path.add(graph.getNodesMap().get(begin));
        passingCellSet.add(graph.getNodesMap().get(begin).getCellID());
        Collections.reverse(path);

    }
    public List<NodeParser> getPath() {
        return path;
    }

    public Set<Long> getPassingCellSet() {
        return passingCellSet;
    }

    public Map<Long, Double> getShortestTimeMap() {
        return shortestTimeMap;
    }
}
