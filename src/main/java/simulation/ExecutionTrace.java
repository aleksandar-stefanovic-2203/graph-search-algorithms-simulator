package simulation;

import java.util.List;

public class ExecutionTrace {
	
	public ExecutionTrace() {}
	
	public List<Node> getPath() {
		return path;
	}

	public int getPrice() {
		return price;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setPath(List<Node> path) {
		this.path = path;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	
	private List<Node> path = null;
	private int price = -1;
	
	private List<Step> steps = null;
	
}
