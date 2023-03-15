package org.thesis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class Output2 {
    private Map<Double, List<Querry>> querysListMap;

    public  Output2(Map<Double, List<Querry>> querysListMap) {
        this.querysListMap = querysListMap;
    }

    public void writeToFile(String path){
        try (Writer writer = new FileWriter(path+".json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(querysListMap, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
