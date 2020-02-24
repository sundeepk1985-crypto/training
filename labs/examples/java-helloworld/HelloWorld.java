

public class HelloWorld {
	
	Employee anil;
	
	public static void main(String[] args) {
		
		Integer a = new Integer(10);
		int i = 10;
		// byte, short, int, long, float, double, char, boolean
		// String name = "capgemini"; // 9 bytes to 18 bytes
		
		System.out.println("Hello World!");
	
		//	Employee anil = null;	
		
		anil = new Employee(100, "Anil", 50000);
		anilCopy = anil;
		
		anil = null;
		
		anil.printDetail()
		
	
		
		
	}
	
}

public abstract class Person {
	final printDetails() {System.out.println(id + " " + name);}
}

public final class Employee extends Person{
	static int id = 100; // 4
	double salary; // 8
	String name; // 8
	
	//public void printDetails() {
//		
	//}
}



