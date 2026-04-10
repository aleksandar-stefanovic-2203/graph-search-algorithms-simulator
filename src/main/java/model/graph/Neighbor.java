package model.graph;

import utilities.ValidationUtils;

public class Neighbor {
	
	public String getDestination() {
		return destination;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setWeight(int weight) {		
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %d)", destination, weight);
	}
	
	public Neighbor() {}
	
	public Neighbor(String destination, int weight) {
		ValidationUtils.requireNonBlank(destination);
		this.destination = destination;
		this.weight = weight;
	}
	
	private String destination;
	private int weight;
}
