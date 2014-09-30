import java.io.*;
import java.net.*;

public class ClientDemo {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1", 7500);
			BufferedReader reader = new BufferedReader(
											new InputStreamReader(
													s.getInputStream()));
			boolean moreData = true;
			
			System.out.println("Established connection");
			
			while(moreData) {
				String line = reader.readLine();
				if(line == null) {
					moreData = false;
				} else {
					System.out.println(line);
				}
			}
		} catch(IOException ioe) {
			System.out.println("IO error: " + ioe);
		}
	}
}
