package Chatserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class ChatServer implements Runnable, ActionListener {

	private JFrame jFrame;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private JTextArea jTextArea;
	private JScrollPane jScrollPane;
	private JTextField jtfInput;
	private JButton jbSend;
	

	ChatServer() {
		jFrame = new JFrame("ChatServer");
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
			serverSocket = new ServerSocket(4444);		// We are going to listen on port 4444.
			clientSocket = serverSocket.accept();
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ois = new ObjectInputStream(clientSocket.getInputStream());
			
			while(true) {
				Object input = ois.readObject();
				jTextArea.setText(jTextArea.getText() + "Client says " + (String)input + System.getProperty("line.separator"));
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
				new ChatServer();
			}
		});
	}
	
}
