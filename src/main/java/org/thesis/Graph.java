package org.thesis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Long, NodeParser> nodesMap;
    private Map<Long,Cell> cellMap;
    private Map<String, Double[][]> speedMatrixMap;


    public Graph() {
        this.nodesMap= new HashMap<>();
        this.cellMap = new HashMap<>();
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

    public void splitGraph(int numberOfCell) {

        //Horizontal Latitude
        //Vertical Longitude
        double minLat = Double.MAX_VALUE;
        double maxLat = Double.MIN_VALUE;
        double minLong = Double.MAX_VALUE;
        double maxLong = Double.MIN_VALUE;
        for(NodeParser node : nodesMap.values()) {
            if(node.getLatitude() < minLat) minLat = node.getLatitude();
            if(node.getLatitude() > maxLat) maxLat = node.getLatitude();
            if(node.getLongitude() < minLong) minLong = node.getLongitude();
            if(node.getLongitude() > maxLong) maxLong = node.getLongitude();
        }
        double horizontalDifferences= maxLat-minLat;
        double verticalDifferences= maxLong-minLong;
        System.out.println();

        int cellsPerEdge= (int) Math.floor(Math.sqrt(numberOfCell));

        //Make the cells
        long itteration= (long) cellsPerEdge *cellsPerEdge;
        for(long i=0; i<itteration; i++) {
            cellMap.put(i, new Cell(i));
        }


        double cellWidthHorizontal=horizontalDifferences/cellsPerEdge;
        double cellWidthVertical=verticalDifferences/cellsPerEdge;


        for(NodeParser node : nodesMap.values()) {
            //horizontal cell
            int horizontalCell= (int) Math.floor((node.getLatitude()-minLat)/cellWidthHorizontal);
            if(horizontalCell==cellsPerEdge) horizontalCell--;

            //vertical cell
            int verticalCell= (int) Math.floor((node.getLongitude()-minLong)/cellWidthVertical);
            if(verticalCell==cellsPerEdge) verticalCell--;

            long cellIndex= ((long) verticalCell *cellsPerEdge)+horizontalCell;

             //add Values
            node.setCellID(cellIndex);
            cellMap.get(cellIndex).addNode(node);
        }
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
        Double[][] speedMatrix5 = {{0.0, 25200.0, 5.5555}, {25200.0, 32400.0, 5.5555}, {32400.0, 43200.0, 5.55550},{43200.0,46800.0,5.5555}, {46800.0,55800.0,5.5555}, {55800.0,68400.0,5.5555}, {68400.0,93600.0,5.5555}};
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

    public void addListToNodeMap(List<NodeParser> nodeList) {
        for (NodeParser node : nodeList) {
            this.nodesMap.put(node.getOsmId(), node);
        }
    }

    public void addToCellMap(Cell cell) {
        this.cellMap.put(cell.getCellId(), cell);
    }

    public double doEstimation(long startNodeId, long endNodeId, double startTime) {
        //calculate traveltime using normal Dijstra
        Dijkstra dijkstra = new Dijkstra(this);
        double traveltime = dijkstra.solveDijkstra(startNodeId, endNodeId);
        double factor = getFactor(startNodeId, endNodeId,startTime);

        return factor * traveltime;
    }

    public double getFactor(long startNodeId, long endNodeId,double startTime) {
        NodeParser beginNode= this.nodesMap.get(startNodeId);
        NodeParser endNode= this.nodesMap.get(endNodeId);
        Cell beginCell = this.cellMap.get(beginNode.getCellID());

        //factormap Time==> TargetCell
        return beginCell.getFactorMap().get(startTime).get(endNode.getCellID());
    }
}
