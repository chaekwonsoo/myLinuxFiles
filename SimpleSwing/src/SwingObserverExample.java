import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingObserverExample {
	
	JFrame frame;
	
	public static void main(String[] args) {
		SwingObserverExample example = new SwingObserverExample();
		example.go();
	}
	
	public void go() {
		frame = new JFrame();
		JButton button = new JButton("정말 해도 될까요?");
		
		button.addActionListener(new AngelListener());
		button.addActionListener(new DevilListener());
		frame.getContentPane().add(BorderLayout.CENTER, button);
		
		frame.setSize(200, 200);
		frame.setVisible(true);
	}
	
	class AngelListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("안돼! 나중에 분명히 후회할걸!");
		}
	}
	
	class DevilListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("당연하지! 그냥 한 번 해봐!");
		}
	}
	
}
