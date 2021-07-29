/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GrahamScan;
import java.awt.image.*;
import java.awt.Graphics2D.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Dialog.*;
import javax.swing.UIManager;

class Rendering extends JPanel implements MouseMotionListener, MouseListener {

	static BufferedImage obrazBuf=null;
                
	int ramkaw;
    int ramkah;
    int pom_x=400;
    int pom_y=300;
    int min;
    int pomi=0;
    float pom=0;
    Listek pierwszy = new Listek();
	int [] X = new int[1000];
	int [] Y = new int[1000];
    float [] Alfa = new float[1000];
    float [] D = new float[1000];
	Shape [] points = new Shape[1000];
	boolean drag = false;
	Shape s = new Line2D.Float();

	int kaka;
	public int liczba_punktow=0;

	public Rendering(int rw, int rh) {
		addMouseMotionListener(this);
		addMouseListener(this);
		ramkaw=rw;
		ramkah=rh;
		obrazBuf=new BufferedImage(ramkaw, ramkah, BufferedImage.TYPE_INT_ARGB);

	}
        
       
public void sorcik(int l,int r)
{
int i,j,x1,y1;
float a1,d1;
i=l;
j=r;

x1=X[(l+r)/2];
y1=Y[(l+r)/2];
a1=Alfa[(l+r)/2];
d1=D[(l+r)/2];

do
{
    while (Alfa[i]<a1)
    ++i;    
    while (Alfa[j]>a1)
    --j;
    if (i<=j)
    {
            pom = Alfa[i];
            Alfa[i]=Alfa[j];
            Alfa[j]=pom;
            
            pomi = X[i];
            X[i]=X[j];
            X[j]=pomi;
            
            pomi = Y[i];
            Y[i]=Y[j];
            Y[j]=pomi;
            
            pom = D[i];
            D[i]=D[j];
            D[j]=pom;
            if (kaka==i) kaka = j; else if (kaka==j) kaka = i;
            i++;
            j--;
           
    }
              
} while (i<j);
 if (l<j)  sorcik(l,j);
 if (i<r)  sorcik(i,r);
}

   

public void sortuj() 
{
int i;        
//   Centroid
pom_x=pom_y=0;


for(i=0; i<liczba_punktow; i++)
 {
  pom_x+=X[i];
  pom_y+=Y[i];
 }
 pom_x=pom_x/liczba_punktow;
 pom_y=pom_y/liczba_punktow;          
 
// na centroid

for (i=0; i<liczba_punktow; i++)
{
    X[i]-=pom_x;
    Y[i]-=pom_y;
}
for (i=0;i<liczba_punktow;i++) 
{
D[i]=Math.abs(X[i])+Math.abs(Y[i]);
if ((X[i]>=0)&&(Y[i]>=0))
     Alfa[i]=Y[i]/D[i];
if ((X[i]<=0)&&(Y[i]>=0))
     Alfa[i]=2-Y[i]/D[i];
if ((X[i]<=0)&&(Y[i]<=0))
     Alfa[i]=2+Math.abs(Y[i])/D[i];
if ((X[i]>=0)&&(Y[i]<0))
     Alfa[i]=4-Math.abs(Y[i])/D[i];
D[i]=X[i]*X[i]+Y[i]*Y[i];

}

sorcik(0,liczba_punktow-1);

min =0;

for (i=0;i<liczba_punktow;i++)
if (Y[min]==Y[i])
       {
           if (X[min]>X[i])
               min = i;
       }    else
               if (Y[min]>Y[i])
               min = i;


pierwszy.x=X[min];
pierwszy.y=Y[min];
pierwszy.alfa=Alfa[min];
pierwszy.d=D[min];
pierwszy.next=pierwszy;
pierwszy.prev=pierwszy;
Listek temp = pierwszy;

for (i=min +1;;i++)
{
    if (i==liczba_punktow) i=0;
    if (i==min) break;
    Listek nowy = new Listek();
    nowy.x=X[i];
    nowy.y=Y[i];
    nowy.alfa=Alfa[i];
    nowy.d=D[i];
    temp.next=nowy;
    nowy.next=pierwszy;
    pierwszy.prev=nowy;
    nowy.prev=temp;
    temp=nowy;
    
}

float det;

temp = pierwszy;
while(temp.next!=pierwszy)
{
    det=((temp.y-temp.next.next.y)*temp.next.x)+((temp.next.next.x-temp.x)*temp.next.y)+(temp.x*temp.next.next.y)-(temp.y*temp.next.next.x);

    if (det>0) 
    {
        temp.next=temp.next.next;
        temp.next.prev=temp;
        if (temp!=pierwszy) temp=temp.prev;
    }
    else temp=temp.next;
}
i=0;
temp = pierwszy;

while (temp.next!=pierwszy)
{

    temp=temp.next;
    i++;
}

i++;





// z centroidu

for (i=0; i<liczba_punktow; i++)
{
    X[i]+=pom_x;
    Y[i]+=pom_y;
}
      
}
        

	public void paint(Graphics g) {

                super.paint(g);
            
		Graphics2D g2D = (Graphics2D)g;
		Graphics2D gb=obrazBuf.createGraphics();
		int w = (int)getSize().getWidth();
		int h = (int)getSize().getHeight();
		Stroke roundStroke = new BasicStroke(Main.szer, BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER);
                
        gb.setStroke(roundStroke);
		gb.fillRect(0,0,w,h);
               gb.setPaint(Color.white);
               gb.fillRect(0,0,800,600);
               gb.setPaint(Color.blue);
                                
               if (liczba_punktow>2) 
               {
               Listek temp = pierwszy;
                               
               while (temp.next!=pierwszy)
               {
                   int x1=temp.x + pom_x;
                   int y1=temp.y + pom_y;
                   int x2=temp.next.x + pom_x;
                   int y2=temp.next.y + pom_y;
                   gb.setPaint(Color.black);
                   Shape s = new Line2D.Float(x1,y1,x2,y2);
                   gb.draw(s);
                   temp=temp.next;
               }
                   int x1=temp.x + pom_x;
                   int y1=temp.y + pom_y;
                   int x2=temp.next.x + pom_x;
                   int y2=temp.next.y + pom_y;
                 
                   Shape s = new Line2D.Float(x1,y1,x2,y2);
                   gb.draw(s);
               } 
                
                
		for (int i=0;i<liczba_punktow;++i) 
                {
			gb.setPaint(Color.black);

			points[i] = new Ellipse2D.Float(this.X[i]-2, this.Y[i]-2, 4, 4);
        		gb.fill(points[i]);
		}

                gb.setPaint(Color.green);
                g2D.drawImage(obrazBuf,null,0,0);
		repaint();
	}

	public void mousePressed(MouseEvent me)
	{
		for (int i=0;i<liczba_punktow;++i)
		{
			if (points[i].contains(me.getX(), me.getY()))
			{
				drag = true;
                                kaka = i;
                                return;
			}
		}
	}

	public void mouseReleased(MouseEvent me)
	{
        drag = false;
        if (liczba_punktow>1) sortuj();
	}


	public void mouseClicked(MouseEvent me)
	{
	for (int i=0;i<liczba_punktow;++i)
		{
			if (points[i].contains(me.getX(), me.getY()))
			return;
		}
	if (me.getButton()==2) { wyczysc(); return; }

	if (me.getButton()==1)
        {

	int x=me.getX();
	int y=me.getY();

	X[liczba_punktow] = x;
	Y[liczba_punktow] = y;
        liczba_punktow++;
        if (liczba_punktow>1) sortuj();
        }
	}
	public void mouseDragged(MouseEvent me) {
		if (drag) 
                {
                    if (liczba_punktow>1) sortuj();
                        X[kaka] = me.getX();
			Y[kaka] = me.getY();
                        
              	}
	}
	public void mouseMoved(MouseEvent me) { }
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {drag = false; if (liczba_punktow>1) sortuj();}


	public void wyczysc()
	{
	liczba_punktow=0;
	}
}