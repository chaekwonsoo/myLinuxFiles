import java.io.*;
import JavaIONewFilterStream.LowerCaseInputStream;


public class StarBuzzCoffee {
	/*
	public static void main(String[] args) {
		Baverage baverage = new Espresso();
		System.out.println(baverage.getDescription() + " $" + baverage.cost());
		
		Baverage baverage2 = new DarkRoast();
		baverage2 = new Mocha(baverage2);
		baverage2 = new Mocha(baverage2);
		baverage2 = new Whip(baverage2);
		System.out.println(baverage2.getDescription() + " $" + baverage2.cost());
		
		Baverage baverage3 = new HouseBlend();
		baverage3 = new Soy(baverage3);
		baverage3 = new Mocha(baverage3);
		baverage3 = new Whip(baverage3);
		System.out.println(baverage3.getDescription() + " $" + baverage.cost());
	}
	*/
	
	public static void main(String[] args) throws IOException {
		int c;
		InputStream in = new LowerCaseInputStream(
								new BufferedInputStream(
										new FileInputStream("test.txt")));
		while((c = in.read()) >= 0) {
			System.out.print((char)c);
		}
		
		in.close();
	}
	
}
