package ChatClient;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class ChatClient implements Runnable, ActionListener {
	
	private JFrame jFrame;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private JTextArea jTextArea;
	private JScrollPane jScrollPane;
	private JTextField jtfInput;
	private JButton jbSend;
	
	public ChatClient() {
		jFrame = new JFrame("ChatClient");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLayout(new FlowLayout());
		jFrame.setSize(300, 320);
		
		Thread myThread = new Thread(this);
		myThread.start();
		
		jTextArea = new JTextArea(15, 15);
		jTextArea.setEditable(false);
		jTextArea.setLineWrap(true);
		
		jScrollPane = new JScrollPane(jTextArea);
		
		jtfInput = new JTextField(15);
		jtfInput.addActionListener(this);
		
		jbSend = new JButton("Send");
		jbSend.addActionListener(this);
		
		jFrame.getContentPane().add(jScrollPane);
		jFrame.getContentPane().add(jtfInput);
		jFrame.getContentPane().add(jbSend);
		jFrame.setVisible(true);
	}
	
	@Override
	public void run() {
		try {
			socket = new Socket("localhost", 4444);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			while(true) {
				Object input = ois.readObject();
				jTextArea.setText(jTextArea.getText() + "Server says " + (String)input + System.getProperty("line.separator"));
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Send") || ae.getSource() instanceof JTextField) {
			 try {
				 oos.writeObject(jtfInput.getText());
				 jTextArea.setText(jTextArea.getText() + "You say: " + jtfInput.getText() + System.getProperty("line.separator"));
				 
			 } catch(IOException e) {
				 e.printStackTrace();
			 }
		 }
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new ChatClient();
			}
		});
	}

}
