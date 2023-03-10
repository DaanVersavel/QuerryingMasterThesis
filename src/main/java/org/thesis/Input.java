package org.thesis;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Input {
    private final Graph graph = new Graph();

    private List<Querry> querryList = new ArrayList<>();

    public Input() {

    }

    public void inputGraph(String filePath) {
        JSONParser parser = new JSONParser();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            JSONObject mapJson = (JSONObject) parser.parse(br);

            for (Object key : mapJson.keySet()) {
                JSONObject celljson = (JSONObject) mapJson.get(key);
                Cell cell = new Cell((Long) celljson.get("cellId"));
                List<NodeParser> nodeList = new ArrayList<>();
                JSONArray nodeArray = (JSONArray) celljson.get("cellList");
                for (Object nodeobj : nodeArray) {
                    JSONObject nodejson = (JSONObject) nodeobj;
                    NodeParser node = new NodeParser((long) nodejson.get("osmId"), (double) nodejson.get("latitude"), (double) nodejson.get("longitude"));
                    node.setCellID((long) nodejson.get("cellID"));

                    List<EdgeParser> edges = new ArrayList<>();
                    JSONArray edgesJson = (JSONArray) nodejson.get("outgoingEdges");
                    for (Object edgeObj : edgesJson) {
                        JSONObject edgeJson = (JSONObject) edgeObj;
                        EdgeParser edge = new EdgeParser((long) edgeJson.get("beginNodeOsmId"), (long) edgeJson.get("endNodeOsmId"), (double) edgeJson.get("defaultTravelTime"));
                        edge.setEdgeType((String) edgeJson.get("edgeType"));
                        edge.setLength((double) edgeJson.get("length"));
                        // set any other attributes for the edge
                        edges.add(edge);
                    }
                    node.setOutgoingEdges(edges);
                    nodeList.add(node);
                }
                cell.addNodeList(nodeList);
                this.graph.addListToNodeMap(nodeList);
                long landmarkid = (long) celljson.get("LandmarkId");

                cell.setLandmark(cell.getCellNodesMap().get(landmarkid));

                //add factormap
                Map factormapJson = (Map) celljson.get("FactorMap");
                for (Object objkey : factormapJson.keySet()) {
                    JSONObject factorMapobj = (JSONObject) factormapJson.get(objkey);
                    Map<Long, Double> factorEntry = new HashMap<>();
                    for (Object objkey2 : factorMapobj.keySet()) {
                        factorEntry.put(Long.valueOf(objkey2.toString()), (Double) factorMapobj.get(objkey2));
                    }
                    cell.addFactorMap(Double.parseDouble(objkey.toString()), factorEntry);
                }
                this.graph.addToCellMap(cell);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public Graph getGraph() {
        return graph;
    }

    public List<Querry> getQuerryList() {
        return querryList;
    }

    public void inputQuerrys(String filePathQuerries) {
        JSONParser parser = new JSONParser();
        try (BufferedReader br = new BufferedReader(new FileReader(filePathQuerries))) {
            JSONArray jsonArray = (JSONArray) parser.parse(br);

            for (Object obj : jsonArray) {
                JSONObject querryJson = (JSONObject) obj;
                Querry querry  = new Querry((long)querryJson.get("startId"),(long)querryJson.get("endId"),(double)querryJson.get("startTime"));
                querryList.add(querry);
            }


        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
