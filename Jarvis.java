package Jarvs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author lagvna
 */
public class Jarvis extends JPanel  {
    Random rnd;
    Point point[];
    int pNum = 20;
    int xPoints[];
    int yPoints[];
    int num;
    int xPoints2[];
    int yPoints2[];
    int w, h;
    JFrame window;

    public Jarvis()  {
	window = new JFrame();
        this.setBackground(Color.white);
        this.setSize(600, 600);
        window.setBackground(Color.white);
        window.setSize(700, 700);
        window.add(this);
        window.setTitle("Algorytm Jarvisa");
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	rnd = new Random();

	xPoints = new int[pNum];
	yPoints = new int[pNum];
	xPoints2 = new int[pNum];
	yPoints2 = new int[pNum];

	giftWrapping();
    }
    
    public static void main(String[] args) {
        new Jarvis().setVisible(true);
    }

    public boolean small(int current, int smallest, int i)  {
	int xa, ya, xb, yb, val;
	xa = xPoints[smallest] - xPoints[current];
	xb = xPoints[i] - xPoints[current];
	ya = yPoints[smallest] - yPoints[current];
	yb = yPoints[i] - yPoints[current];
	
	val = xa * yb - xb * ya;
	if ( val > 0 )
	    return true;
	else if ( val < 0 )
	    return false;
	else {
	    if ( xa * xb + ya * yb < 0 )
		return false;
	    else {
		if ( xa * xa + ya * ya > xb * xb + yb * yb )
		    return true;
		else
		    return false;
	    }
	}
    }

    public void giftWrapping()  {
	int x, y;
        // losowe punkty
	for ( int i = 0; i < pNum; i++ ) {
	    xPoints[i] = 30 + rnd.nextInt(600);
	    yPoints[i] = 30 + rnd.nextInt(600);
	}

	int min = 0;
	for ( int i = 1; i < pNum; i++ ) {
	    if ( yPoints[i] == yPoints[min] ) {
		if ( xPoints[i] < xPoints[min] )
		    min = i;
	    }
	    else if ( yPoints[i] < yPoints[min] )
		min = i;
	}

	num = 0;
	int smallest;
	int current = min;
	do {
            try {
                Thread.sleep(500);
                repaint();
            } catch (InterruptedException ex) {
                Logger.getLogger(Jarvis.class.getName()).log(Level.SEVERE, null, ex);
            }
	    xPoints2[num] = xPoints[current];
	    yPoints2[num] = yPoints[current];
	    num++;
	    smallest = 0;
	    if ( smallest == current )
		smallest = 1;
	    for ( int i = 0; i < pNum; i++ ) {
		if ( ( current == i ) || ( smallest == i ) )
		    continue;
		if ( small(current, smallest, i))
		    smallest = i;
	    }
	    current = smallest;
	} while ( current != min );
    }

    @Override
    public void paint(Graphics g)   {
	g.setColor(Color.white);
	g.fillRect(0,0,700,700);
	
	g.setColor(Color.red);
	for ( int i = 0; i < pNum; i++ ) {
	    g.fillOval(xPoints[i]-3,yPoints[i]-3, 7,7);
	}
	
	g.setColor(Color.black);
	g.drawPolygon(xPoints2, yPoints2, num);
    }
}