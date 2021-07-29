/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GrahamScan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

/**
 *
 * @author kysiek
 */
public class Main {

	static int ramkaw;
	static int ramkah;
	static boolean zapisujemy=false;
	static boolean doskokk=false;
	static boolean rozniczka=false;
	static float szer=1;
	static public JFrame f = new JFrame("GrahamScan");
	
	public static void main(String [] args) {

                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                } 
                catch (Exception e) {}
            
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(810,650);

		f.setBackground(Color.white);
        f.setResizable(false);
		Container pojemnik=f.getContentPane();
		ramkaw=f.getWidth();
		ramkah=f.getHeight();
		final Rendering bc = new Rendering(ramkaw, ramkah);
      
		pojemnik.add(bc, BorderLayout.CENTER);
        JMenuBar menu=new JMenuBar();
        JMenu opcje=new JMenu("Opcje");
		JMenuItem wyjscie=new JMenuItem("Wyjscie");
		JMenuItem wyczysc_=new JMenuItem("Wyczysc");
                
                
                opcje.add(wyczysc_);
                opcje.add(wyjscie);
                                		                

                menu.add(opcje);
                     
		wyjscie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
			 System.exit(0);
			}
		});

		wyczysc_.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
			 bc.wyczysc();
			}
		});
                
              
        f.setJMenuBar(menu);
		f.setVisible(true);
	}
}
