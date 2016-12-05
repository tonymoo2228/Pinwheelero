import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainFrame extends JFrame {

	private BorderLayout layout;
	
	private Container content;
	
	Timer timer;
	
	private Spinner spinner;
	private ButtonPanel buttonPanel;
	
	public MainFrame(String[] options) {
		layout = new BorderLayout();
		content = this.getContentPane();
		content.setLayout(layout);
		
		spinner = new Spinner(options);
		content.add(spinner, BorderLayout.CENTER);
		
		buttonPanel = new ButtonPanel(spinner);
		content.add(buttonPanel, BorderLayout.NORTH);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400 + buttonPanel.getHeight());
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		MainFrame mf = new MainFrame(args);
	}
	
}
