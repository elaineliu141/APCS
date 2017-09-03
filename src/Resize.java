
import java.awt.Color;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JPanel;

public class Resize extends JPanel {
	flag flag = new flag ();
	
    private Rectangle2D.Float myRect = new Rectangle2D.Float(90, 90, 90, 90);
   
    @Override
    public void paint(Graphics g) {
      //Graphics2D g2 = (Graphics2D) g;
	  super.paint(g);
	  Graphics2D graphics2d = (Graphics2D) g;
	  graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);	
	  graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	  //graphics2d.setColor(Color.BLUE);
	  //graphics2d.fill(myRect);
	  flag.paint(graphics2d);
	}
    
    class ResizeHandler implements MouseWheelListener {

	  @Override
	
	  public void mouseWheelMoved(MouseWheelEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
		    if (myRect.getBounds2D().contains(x, y)) {
			  float amount = e.getWheelRotation() * 5f;
			  myRect.width += amount;
			  myRect.height += amount;
			  repaint();
		    }
		}}
	  
	  }
	    public static void main(String[] args) {
		  JFrame jFrame = new JFrame("The American Flag");
		  Resize resi = new Resize();
		  resi.setDoubleBuffered(true);
		  jFrame.add(resi);
		  jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  jFrame.setSize(700, 600);
		  jFrame.setLocationRelativeTo(null);
		  jFrame.setVisible(true);
	
	    }

}
