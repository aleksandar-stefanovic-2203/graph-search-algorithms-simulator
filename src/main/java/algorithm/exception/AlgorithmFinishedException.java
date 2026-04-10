package algorithm.exception;

@SuppressWarnings("serial")
public class AlgorithmFinishedException extends Exception {
	public AlgorithmFinishedException() {
		super("Algorithm finished successfully!");
	}
}
