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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
		JMenuBar menuBar = new JMenuBar();
		JMenu killMenu = new JMenu("Kill Me");
		JMenuItem killSwitch = new JMenuItem("Is Kill");
		killSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		killMenu.add(killSwitch);
		menuBar.add(killMenu);
		setJMenuBar(menuBar);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500 + buttonPanel.getHeight());
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		if (args.length == 0) {
			JFrame inputPrompt = new JFrame();
			inputPrompt.setTitle("Please Enter Input");
			inputPrompt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			String choices = JOptionPane.showInputDialog(inputPrompt, "Please enter the comma-then-space-seperated choices.");
			args = choices.split(", ");
		}
		MainFrame mf = new MainFrame(args);
	}
	
}
