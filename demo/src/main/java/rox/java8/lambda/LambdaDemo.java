package rox.java8.lambda;

import java.util.function.Consumer;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

public class LambdaDemo {

	interface Foo {
		void foo();
		
		public static int getWeight(Apple a) {
			return a.getWeight();
		}
	}
	
	void consume(String s) {
		System.out.println(s);
	}
	
	int getWeight(Apple a) {
		return a.getWeight();
	}
	
	public static void foo(ToIntFunction<Apple> f) {
		int r = f.applyAsInt(new Apple(3));
		System.out.println(r);
	}
	
	public static void foo(int i, ToIntBiFunction<Apple, Integer> f) {
		int r = f.applyAsInt(new Apple(i), i);
		System.out.println(r);
	}

	/*
	There are three main kinds of method references:
	1. A method reference to a static method (for example, the method parseInt of Integer, written Integer::parseInt)
	
	2. A method reference to an instance method of an arbitrary type (for example, the method length of a String, written String::length)
	//RoX: arbitrary type => in fact, it should be the type of FIRST parameter in function discriptor (a,b) -> a.foo(b) ==> A::foo 
	//RoX: => instance method of internal object
	
	3. A method reference to an instance method of an existing object 
	(for example, suppose you	have a local variable expensiveTransaction that holds an object of type Transaction, 
	which supports an instance method getValue; you can write expensiveTransaction::getValue)
	//RoX: => instance method of external object
	
	 */
	public static void main(String[] args) {
		//Any class			-> static method 	: apply(Apple) -> int;
		//an existing object-> instance method 	: apply(Apple) -> int;
		//Apple			   	-> instance method 	: apply()      -> int;
		
		//Function descriptor of ToIntFunction ('s abstract method) : (Apple) -> int
		foo(Foo::getWeight); 				//static method						# apply(Apple)
		foo(Apple::getWeight1); 			//static method						# apply(Apple)
		foo(Apple::getWeight); 				//instance method (of param type) 	# apply()
		foo(new Apple(11)::getWeight2); 	//existing object's instance method	# apply(Apple)
		foo(new LambdaDemo()::getWeight); 	//existing object's instance method	# apply(Apple)
		foo(3, Apple::getWeight);			// #apply(int)
		
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
		c = String::codePoints;
	}
}
