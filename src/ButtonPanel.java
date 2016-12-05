import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private Spinner spinner;
	
	private JButton spin;
	private JButton spinAndRemove;
	private JButton recolor;
	
	private GridLayout layout;
	
	public ButtonPanel(Spinner spinner) {
		this.spinner = spinner;
		
		layout = new GridLayout(1, 3);
		setLayout(layout);
		
		spin = new JButton("Spin");
		spinAndRemove = new JButton("Spin and Remove");
		recolor = new JButton("Recolor");
		
		spin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spinner.spin();
			}
		});
		spinAndRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spinner.spinAndRemove();
			}
		});
		recolor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spinner.recolor();	
			}
		});
		
		add(spin);
		add(spinAndRemove);
		add(recolor);
	}
	
}
