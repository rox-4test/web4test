package rox.java8.lambda;

import java.util.function.Consumer;
import java.util.function.ToIntFunction;

public class LambdaDemo {

	interface Foo {
		void foo();
	}
	
	void consume(String s) {
		System.out.println(s);
	}
	
	public static void foo(ToIntFunction<Apple> f) {
		int r = f.applyAsInt(new Apple(3));
		System.out.println(r);
	}

	public static void main(String[] args) {
		//Class 			-> static method 	: f(Apple) -> int;
		//Explicit object 	-> instance method 	: f(Apple) -> int;
		//Apple 			-> instance method 	: f()      -> int;
		foo(Apple::getWeight); //instance
		foo(Apple::getWeight1); //static class
		foo(new Apple(11)::getWeightA); //particular object
		foo(new Apple(11)::getWeightB);

		Foo f = () -> Thread.dumpStack();
		try {
			f.foo();
		} catch (Throwable t) {
			System.err.println(t);
		}
		f = Thread::dumpStack;
		try {
			f.foo();
		} catch (Throwable t) {
			System.err.println(t);
		}

		Consumer<String> c = (String s) -> System.out.println(s);
		c.accept("a");
		c = System.out::println;
		c.accept("b");
		c = new LambdaDemo()::consume;
		c.accept("c");
	}
}
