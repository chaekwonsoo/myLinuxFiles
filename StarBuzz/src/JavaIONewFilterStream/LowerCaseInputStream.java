package JavaIONewFilterStream;

import java.io.*;

public class LowerCaseInputStream extends FilterInputStream {

	public LowerCaseInputStream(InputStream in) {
		super(in);
	}

	@Override
	public int read() throws IOException {
		int c = super.read();
		return (c == -1 ? c : Character.toLowerCase(c));
	}
	
	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int bytesRead = super.read(b,  off,  len);
		
		for(int i = 0; i < bytesRead; i++) {
			b[i] = (byte)Character.toLowerCase(b[i]);
		}
		
		return bytesRead;
	}
	
}
