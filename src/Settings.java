import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class Settings extends JFrame implements ActionListener {

	private JRadioButton easy, medium, hard, speed, level1, level2, spin; // buttons for settings GUI
    
    public Settings()
    {
       //Create settings GUI
       easy = new JRadioButton(" Easy ");
       medium = new JRadioButton(" Medium ");
       hard = new JRadioButton(" Hard ");
       speed = new JRadioButton(" Add Speed per bounce ");
       spin = new JRadioButton(" Randomize ");
       level1 = new JRadioButton(" Level 1 ");
       level2 = new JRadioButton(" Level 2 ");
    
       ButtonGroup buttons = new ButtonGroup(); 
       buttons.add(easy);
       buttons.add(medium);
       buttons.add(hard);
       buttons.add(speed);
       buttons.add(spin);
       buttons.add(level1);
       buttons.add(level2);
       
       JButton ok = new JButton("Okay");
       ok.addActionListener(this);
       
       Container c = getContentPane();
       c.setLayout(new FlowLayout());
       c.add(easy);
       c.add(medium);
       c.add(hard);
       c.add(speed);
       c.add(spin);
       c.add(level1);
       c.add(level2);
       c.add(ok);
    }
	
    public void actionPerformed(ActionEvent e) {
       if(easy.isSelected())
       {
    	   PanelBall.setStepSizeX(5);
    	   PanelBall.setStepSizeY(5);
           PanelBall.setspeed(0);
           PanelBall.setSpin1(0);
           PanelBall.setSpin2(0);
       }
       else if(medium.isSelected())
       {
    	   PanelBall.setStepSizeX(8); 
    	   PanelBall.setStepSizeY(8);
           PanelBall.setspeed(0);
           PanelBall.setSpin1(0);
           PanelBall.setSpin2(0); 
       }
       else if(hard.isSelected())
       {
           PanelBall.setStepSizeX(12.5); 
           PanelBall.setStepSizeY(12.5);
           PanelBall.setspeed(0);
           PanelBall.setSpin1(0);
           PanelBall.setSpin2(0);
       }
       else if(speed.isSelected())
    	   PanelBall.setspeed(1);
       else if(level2.isSelected())
    	   PanelBall.setPaddleSize(13);
       else if(level1.isSelected())
    	   PanelBall.setPaddleSize(25);
       else if(spin.isSelected())
       {
    	   int i = 0; 
    	   while(PanelBall.getHit() && i < 50)
    		{
    	      PanelBall.setSpin1(Math.random() * 15);
    		  PanelBall.setSpin2(Math.random() * 15);
    		  i++;
    		}
       }
    }
    
    public static void main(String[] args){
    	
    }
}