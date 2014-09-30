import java.net.*;
import java.io.*;

public class ThreadHandler extends Thread{
	Socket newSocket;
	int threadNumber;
	
	ThreadHandler(Socket newSocket, int threadNumber) {
		this.newSocket = newSocket;
		this.threadNumber = threadNumber;
	}
	
	public void run() {
		try {
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

class ConcurrentServer {
	public static void main(String[] args) {
		int threadNumber = 1;
		try {
			ServerSocket sock = new ServerSocket(7500);
			
			while(true) {
				Socket newSock = sock.accept();
				System.out.println("Creating a thread...");
				Thread thread = new ThreadHandler(newSock, threadNumber);
				thread.start();
				threadNumber++;
			}
			
		} catch(IOException ioe) {
			System.out.println("IO error: " + ioe);
		}
	}
}