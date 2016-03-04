package rox.java8.lambda;

public class Apple {
	private int weight;

	public Apple(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public int getWeight(int i) {
		return weight + i;
	}

	public static int getWeight1(Apple a) {
		return a.getWeight();
	}

	public int getWeight2(Apple w) {
		return w.getWeight();
	}
}
