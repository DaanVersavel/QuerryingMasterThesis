package org.thesis;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Output {
    private final JSONArray jsonArray = new JSONArray();
    public Output(List<Querry> querryList) {

        for (Querry querry : querryList) {
            JSONObject querryJson = new JSONObject();
            querryJson.put("startId",querry.getStartId());
            querryJson.put("endId",querry.getEndId());
            querryJson.put("startTime",querry.getStartTime());
            querryJson.put("estimatedTravelTime",querry.getEstimatedTravelTime());
            querryJson.put("timeDependantTravelTime",querry.getTimeDependantTravelTime());
            jsonArray.add(querryJson);
        }
    }
    public void writeToFile(String outputPath) throws IOException {
        PrintWriter pw = new PrintWriter(outputPath + ".json");
        pw.write(jsonArray.toJSONString());
        pw.flush();
        pw.close();
    }
}
