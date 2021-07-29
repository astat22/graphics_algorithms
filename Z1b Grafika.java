import java.util.*;
import java.awt.*;
import java.awt.event.*;


class MojWindowAdapter extends WindowAdapter
{
	public void windowClosing(WindowEvent e) {System.exit(0);}	
}

class MyButtonAdapter implements ActionListener
{
	Odcinek p;
	MyButtonAdapter(Odcinek p) {this.p=p;}
	public void actionPerformed (ActionEvent e) { p.action();}
}


class Egzekutor extends Button
{
	Egzekutor(Odcinek p)
	{
		super("Wykonaj");
		addActionListener(new MyButtonAdapter(p));
	}
}


class MyFrame extends Frame
{
	MyFrame(Odcinek p)
	{
		super("Rysuj odcinek");
		setBounds(50,50,200,150);
		addWindowListener (new MojWindowAdapter());
		setLayout(new GridLayout(5,1));
		Egzekutor wykonaj=new Egzekutor(p);
		p.x1= new TextField(3);
		p.y1=new TextField(3);
		p.x2=new TextField(3);
		p.y2=new TextField(3);
		add(p.x1);
		add(p.y1);
		add(p.x2);
		add(p.y2);
		add(wykonaj);
		setResizable(false);
		
	}
}


public class Odcinek
{

MyFrame frame;
TextField x1;
TextField y1;
TextField x2;
TextField y2;

void action()
{
}


public static void main(String[] args)
	{

		Odcinek p=new Odcinek();
		p.frame= new MyFrame(p);
		p.frame.setVisible(true);
		
	}
}
