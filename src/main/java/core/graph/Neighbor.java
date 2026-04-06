package core.graph;

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
		if(weight < 1) throw new IllegalArgumentException("Weight argument must be a whole number greater than or equal to 1.");
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %d)", destination, weight);
	}
	
	public Neighbor() {
		
	}
	
	public Neighbor(String destination, int weight) {
		this.destination = destination;
		this.weight = weight;
	}
	
	private String destination;
	private int weight;
}
