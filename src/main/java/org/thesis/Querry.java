package org.thesis;

public class Querry {
    private final long startId;
    private final long endId;
    private double startTime;
    private double estimatedTravelTime;
    private double timeDependantTravelTime;
    private final double dijkstraTravelTime;

    public Querry(long startId, long endId,double dijkstraTravelTime) {
        this.startId = startId;
        this.endId = endId;
        this.dijkstraTravelTime = dijkstraTravelTime;
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
    public double getDijkstraTravelTime() {
        return dijkstraTravelTime;
    }
}
