import java.net.*;
import java.io.*;

public class SimpleIterativeServer {
	public static void main(String[] args) {
		try {
			ServerSocket sock = new ServerSocket(7500); 	// port number on which this server process should wait
																// for a client request to arrive.
			
			// this method makes the server process to wait(be blocked) until the client request comes.
			Socket newSocket = sock.accept();
			// ------------------------------------------------------------------------------------
			
			BufferedReader reader = new BufferedReader(
											new InputStreamReader(
													newSocket.getInputStream()));
			PrintStream outp = new PrintStream(
										newSocket.getOutputStream());
			outp.println("Hello:: enter QUIT to exit.");
			boolean moreData = true;
			
			while(moreData) {
				String line = reader.readLine();
				if(line == null) {
					moreData = false;
				} else {
					outp.println("From server: " + line + System.getProperty("line.separator"));
					if(line.trim().equals("QUIT"))
						moreData = false;
				}
			}
			newSocket.close();
		} catch(IOException ioe) {
			System.out.println("IO error: " + ioe);
		}
	}
}
