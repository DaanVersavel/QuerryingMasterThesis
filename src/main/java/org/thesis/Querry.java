package org.thesis;

public class Querry {
    private final long startId;
    private final long endId;
    private double startTime;
    private double dijkstreTravelTime;
    private double estimatedTravelTime;
    private double timeDependantTravelTime;

    public Querry(long startId, long endId, double dijkstreTravelTime) {
        this.startId = startId;
        this.endId = endId;
        this.dijkstreTravelTime = dijkstreTravelTime;
    }

    public void setEstimatedTravelTime(double estimatedTravelTime) {
        this.estimatedTravelTime = estimatedTravelTime;
    }

    public void setTimeDependantTravelTime(double timeDependantTravelTime) {
        this.timeDependantTravelTime = timeDependantTravelTime;
    }

    public long getStartId() {
        return startId;
    }

    public long getEndId() {
        return endId;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEstimatedTravelTime() {
        return estimatedTravelTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getTimeDependantTravelTime() {
        return timeDependantTravelTime;
    }

    public double getDijkstreTravelTime() {
        return dijkstreTravelTime;
    }
}
