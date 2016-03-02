package rox.java8.lambda;

public class Apple {
	private int weight;

	public Apple(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public static int getWeight1(Apple a) {
		return a.getWeight();
	}

	public int getWeightA(Apple w) {
		return weight;
	}

	public int getWeightB(Apple w) {
		return w.getWeight();
	}
}
