package org.thesis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Long, NodeParser> nodesMap;
    private Map<Long,Cell> cellMap;
    private Map<String, Double[][]> speedMatrixMap;
    private Map<Double, List<Querry>> querysListMap;


    public Graph() {
        this.nodesMap= new HashMap<>();
        this.cellMap = new HashMap<>();
        this.querysListMap = new HashMap<>();
        //only used for the comparing between real value and estimation
        this.speedMatrixMap = new HashMap<>();
        makeSpeedMatrixs();
    }

    public Map<Long, NodeParser> getNodesMap() {
        return nodesMap;
    }

    public void setNodesMap(Map<Long, NodeParser> nodesMap) {
        this.nodesMap = nodesMap;
    }

    public Map<Long, Cell> getCellMap() {
        return cellMap;
    }

    public void setCellMap(Map<Long, Cell> cellMap) {
        this.cellMap = cellMap;
    }

    public Map<String, Double[][]> getSpeedMatrixMap() {
        return speedMatrixMap;
    }
    public void addListToNodeMap(List<NodeParser> nodeList) {
        for (NodeParser node : nodeList) {
            this.nodesMap.put(node.getOsmId(), node);
        }
    }

    public void addToCellMap(Cell cell) {
        this.cellMap.put(cell.getCellId(), cell);
    }

    public double getFactor(long startNodeId, long endNodeId,double startTime) {
        NodeParser beginNode= this.nodesMap.get(startNodeId);
        NodeParser endNode= this.nodesMap.get(endNodeId);
        Cell beginCell = this.cellMap.get(beginNode.getCellID());
        //factormap Time==> TargetCell
        return beginCell.getFactorMap().get(startTime).get(endNode.getCellID());
    }

    public void addQuerryList(double startTime, List<Querry> querryList) {
        querysListMap.put(startTime, querryList);
    }

    public Map<Double, List<Querry>> getQuerysListMap() {
        return querysListMap;
    }

    public void makeSpeedMatrixs() {
        //Primary
        Double[][] speedMatrix = {{0.0,25200.0,19.4444},{25200.0,32400.0,11.1111},{32400.0,43200.0,19.4444},{43200.0,46800.0,16.6666},{46800.0,55800.0,19.4444},{55800.0,68400.0,11.1111},{68400.0,93600.0,19.4444}};
        this.speedMatrixMap.put("primary", speedMatrix);
        //Primary_linkk
        Double[][] speedMatrix9 = {{0.0,25200.0,19.4444},{25200.0,32400.0,11.1111},{32400.0,43200.0,19.4444},{43200.0,46800.0,16.6666},{46800.0,55800.0,19.4444},{55800.0,68400.0,11.1111},{68400.0,93600.0,19.4444}};
        this.speedMatrixMap.put("primary_link", speedMatrix9);
        //Secondary
        Double[][] speedMatrix2 = {{0.0, 25200.0, 13.8888}, {25200.0, 32400.0, 8.3333}, {32400.0, 43200.0, 13.8888},{43200.0,46800.0,13.8888}, {46800.0,55800.0,13.8888}, {55800.0,68400.0,8.3333}, {68400.0,93600.0,13.8888}};
        this.speedMatrixMap.put("secondary", speedMatrix2);
        //Secondary
        Double[][] speedMatrix11 = {{0.0, 25200.0, 13.8888}, {25200.0, 32400.0, 8.3333}, {32400.0, 43200.0, 13.8888},{43200.0,46800.0,13.8888}, {46800.0,55800.0,13.8888}, {55800.0,68400.0,8.3333}, {68400.0,93600.0,13.8888}};
        this.speedMatrixMap.put("secondary_link", speedMatrix11);
        //Tertiary
        Double[][] speedMatrix3 = {{0.0, 25200.0, 13.8888}, {25200.0, 32400.0, 8.3333}, {32400.0, 43200.0, 13.8888},{43200.0,46800.0,13.8888}, {46800.0,55800.0,13.8888}, {55800.0,68400.0,8.3333}, {68400.0,93600.0,13.8888}};
        this.speedMatrixMap.put("tertiary", speedMatrix3);
        //Tertiary-Link
        Double[][] speedMatrix10 = {{0.0, 25200.0, 13.8888}, {25200.0, 32400.0, 8.3333}, {32400.0, 43200.0, 13.8888},{43200.0,46800.0,13.8888}, {46800.0,55800.0,13.8888}, {55800.0,68400.0,8.3333}, {68400.0,93600.0,13.8888}};
        this.speedMatrixMap.put("tertiary_link", speedMatrix10);
        //Residential
        Double[][] speedMatrix4 = {{0.0, 25200.0, 13.8888}, {25200.0, 32400.0, 8.3333}, {32400.0, 43200.0, 13.8888},{43200.0,46800.0,8.3333}, {46800.0,55800.0,13.8888}, {55800.0,68400.0,8.3333}, {68400.0,93600.0,13.8888}};
        this.speedMatrixMap.put("residential", speedMatrix4);
        //living_street
        Double[][] speedMatrix5 = {{0.0, 25200.0, 8.3333}, {25200.0, 32400.0, 5.5555}, {32400.0, 43200.0, 8.3333},{43200.0,46800.0,5.5555}, {46800.0,55800.0,8.3333}, {55800.0,68400.0,5.5555}, {68400.0,93600.0,8.3333}};
        this.speedMatrixMap.put("living_street", speedMatrix5);
        //motorway_link
        Double[][] speedMatrix6 = {{0.0,25200.0,19.4444},{25200.0,32400.0,11.1111},{32400.0,43200.0,19.4444},{43200.0,46800.0,16.6666},{46800.0,55800.0,19.4444},{55800.0,68400.0,11.1111},{68400.0,93600.0,19.4444}};
        this.speedMatrixMap.put("motorway_link", speedMatrix6);
        //trunk
        Double[][] speedMatrix7 = {{0.0, 25200.0,19.4444}, {25200.0,32400.0,19.4444}, {32400.0, 43200.0, 19.4444},{43200.0,46800.0,19.4444}, {46800.0,55800.0,19.4444}, {55800.0,68400.0,19.4444}, {68400.0,93600.0,19.4444}};
        this.speedMatrixMap.put("trunk", speedMatrix7);
        //motorway
        Double[][] speedMatrix8 = {{0.0, 25200.0, 33.3333}, {25200.0, 32400.0, 27.7777}, {32400.0, 43200.0, 30.5555},{43200.0,46800.0,27.7777}, {46800.0,55800.0,30.5555}, {55800.0,68400.0,25.0}, {68400.0,93600.0,33.3333}};
        this.speedMatrixMap.put("motorway", speedMatrix8);
    }
}
