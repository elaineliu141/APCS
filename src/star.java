import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class star extends Applet{
	public void star(Graphics g){
		//draw pentagons
		//blue pentagon outside
		int xCordBigP[] = new int[5];
		int yCordBigP[] = new int[5];
		for(int k = 0; k<5; k++){
			xCordBigP[k] = (int) (2 * Math.cos(k*2*Math.PI/5-180)+45);
			yCordBigP[k] = (int) (40* Math.sin(k*2*Math.PI/5-180)+45);
		}		
		g.setColor(Color.BLUE);
		g.fillPolygon(xCordBigP, yCordBigP, 5);
		
		//white pentagon inside
		int xCordSmallP[] = new int[5];
		int yCordSmallP[] = new int[5];
		for(int k = 0; k<5; k++){
			xCordSmallP[k] = (int) (20 * Math.cos(k*2*Math.PI/5+180)+45);
			yCordSmallP[k] = (int) (20* Math.sin(k*2*Math.PI/5+180)+45);
		}		
		g.setColor(Color.WHITE);
		g.fillPolygon(xCordSmallP, yCordSmallP, 5);
	}
}
