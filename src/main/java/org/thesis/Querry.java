package org.thesis;

public class Querry {
    private final long startId;
    private long endId;
    private double startTime;
    private double estimatedTravelTime;
    private double timeDependantTravelTime;

    public Querry(long startId, long endId, double startTime) {
        this.startId = startId;
        this.endId = endId;
        this.startTime = startTime;
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

    public double getTimeDependantTravelTime() {
        return timeDependantTravelTime;
    }
}
