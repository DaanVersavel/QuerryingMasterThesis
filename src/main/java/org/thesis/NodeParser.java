package org.thesis;

import java.util.*;

public class NodeParser {
	private long osmId;
	private double latitude;
	private double longitude;
	private double currenCost;
	private boolean dissabled;
	private long cellID;
	private List<EdgeParser> outgoingEdges = new ArrayList<>();

	public NodeParser(long osmId, double latitude, double longitude) {
		this.osmId = osmId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dissabled = false;
		this.currenCost=0.0;
	}

	public NodeParser(NodeParser node) {
		this.osmId = node.getOsmId();
		this.latitude = node.getLatitude();
		this.longitude = node.getLongitude();
		this.cellID = node.getCellID();
		this.outgoingEdges = node.getCopyOfOutgoingEdges();
	}

	private List<EdgeParser> getCopyOfOutgoingEdges() {
		List<EdgeParser> copy = new ArrayList<>();
		for (EdgeParser edge : outgoingEdges) {
            copy.add(new EdgeParser(edge));
        }
		return copy;
	}

	public double getCurrenCost() {
		return currenCost;
	}

	public void setCurrenCost(double currenCost) {
		this.currenCost = currenCost;
	}

	public long getOsmId() {
		return osmId;
	}

	public void setOsmId(long osmId) {
		this.osmId = osmId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public List<EdgeParser> getOutgoingEdges() {
		return outgoingEdges;
	}

	public void setOutgoingEdges(List<EdgeParser> outgoingEdges) {
		this.outgoingEdges = outgoingEdges;
	}

	@Override
	public String toString() {
		return (latitude + "," + longitude);
	}

	public void setDissabled(boolean b) {
		this.dissabled = b;
	}

	public boolean getDissabled() {
		return dissabled;
	}

	public long getCellID() {
		return cellID;
	}

	public void setCellID(long celID) {
		this.cellID = celID;
	}

}


