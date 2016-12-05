import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A class representing spinners that have choices.
 * The spinner can spin to make a choice for you.
 * The choices for a spinner are mutable.
 *
 */
public class Spinner extends JPanel {
	
	private double numberOfOptions;
	private double degreesPerOption;
	
	private double angleSpun;
	
	private HashMap<Integer, Color> colors;
	private HashMap<Integer, String> options;
	
	private Random gen;
	
	/**
	 * Creates a pinwheel spinny thing with a specific set of choices.
	 * @param choices The choices for this spinner.
	 */
	public Spinner(String[] choices) {
		this.numberOfOptions = choices.length;
		options = new HashMap<Integer, String>();
		colors = new HashMap<Integer, Color>();
		degreesPerOption = 360.0/numberOfOptions;
		angleSpun = 0;
		
		gen = new Random();
		
		Color color;
		for (int i = 0; i < numberOfOptions; i++) {
			do {
				color = getRandomColor();
			} while(colors.values().contains(color));
			colors.put((Integer) i, color);
			options.put((Integer) i, choices[i]);
		}
	}	
	
	/**
	 * Generates a random pretty pink pastel color.
	 * @return The prettiest, pinkest, pastelest color you can imagine.
	 */
	private Color getRandomColor() {
		float hue = gen.nextFloat();
		float saturation = (gen.nextInt(2000) + 1000) / 10000f;
		float luminance = 0.9f;
		Color color = Color.getHSBColor(hue, saturation, luminance);
		return color;
	}
	
	/**
	 * Removes the choice specified at the index in the options list.
	 * @param index The index to remove from.
	 */
	private void removeCurrent(int index) {
		for (int i = index; i < numberOfOptions; i++) {
			colors.put(i, colors.get(i+1));
			options.put(i, options.get(i+1));
		}
		numberOfOptions--;
		degreesPerOption = 360.0/numberOfOptions;
		repaint();
	}
	
	/**
	 * Spins the wheel and displays the result.
	 * @param remove Whether or not to remove the result.
	 */
	private void spinFunction(boolean remove) {
		int angleToSpin = gen.nextInt(1080) + 1;
		Timer t;
		t = new Timer(10, null);
		ActionListener taskPerformer = new ActionListener() {
			int firecount = 0;
			public void actionPerformed(ActionEvent evt) {        
		    firecount++;
			angleSpun++;
			repaint();
		    if ( firecount == angleToSpin ) {
		    	t.stop();
				int index = (int) (numberOfOptions - Math.ceil(((angleSpun-45)%360)/degreesPerOption));
				JOptionPane.showMessageDialog(new JFrame(), options.get(index));
				if (remove) {
					removeCurrent(index);
				}
		    }
		  }
		};
		t.addActionListener(taskPerformer);
		t.start();
	}
	
	/**
	 * Spins the wheel and removes the result.
	 */
	public void spinAndRemove() {
		spinFunction(true);
	}
	
	/**
	 * Spins the wheel without removing the result.
	 */
	public void spin() {
		spinFunction(false);
	}
	
	/**
	 * Recolors every choice in the spinner.
	 */
	public void recolor() {
		Color color;
		colors = new HashMap<Integer, Color>();
		for (int i = 0; i < numberOfOptions; i++) {
			do {
				color = getRandomColor();
			} while(colors.values().contains(color));
			colors.put((Integer) i, color);
		}
		repaint();
	}
	
	/**
	 * Paints a wonderful pinwheel.
	 */
	@Override 
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		int xCenter = (int) (this.getWidth()/2.0);
		int yCenter = (int) (this.getHeight()/2.0);
		
		int xMax;
		int yMax;
		Graphics2D g2d = (Graphics2D) g.create();
		for (int i = 0; i < numberOfOptions; i++) {
			g2d.setColor(colors.get(i));
			g2d.fill(new Arc2D.Double(0, 0, this.getWidth(), this.getHeight(), i * degreesPerOption + angleSpun, degreesPerOption, Arc2D.PIE));
			g2d.setColor(Color.BLACK);
			xMax = (int) ((this.getWidth()/2.0) * Math.cos(Math.toRadians((i+.5) * degreesPerOption + angleSpun)));
			yMax = (int) ((this.getHeight()/2.0) * Math.sin(Math.toRadians((i+.5) * degreesPerOption + angleSpun)));
			FontMetrics metrics = g.getFontMetrics(g.getFont());
			int adv = metrics.stringWidth(options.get(i))/2;
			g2d.drawString(options.get(i), xCenter + xMax/2 - adv, yCenter - yMax/2);
		}
		for (int i = 0; i < numberOfOptions; i++) {
			xMax = (int) ((this.getWidth()/2.0) * Math.cos(Math.toRadians(i * degreesPerOption + angleSpun)));
			yMax = (int) ((this.getHeight()/2.0) * Math.sin(Math.toRadians(i * degreesPerOption + angleSpun)));
			g.setColor(Color.BLACK);
			g.drawLine(xCenter, yCenter, xCenter + xMax, yCenter - yMax);
		}
		g.drawLine(xCenter, yCenter, xCenter + 50, yCenter - 50);
		g.fillPolygon(new int[] {xCenter + 50, xCenter + 50, xCenter + 40}, new int[] {yCenter - 50, yCenter - 40, yCenter - 50}, 3);
		g.drawOval(0, 0, this.getWidth(), this.getHeight());
	}
}
