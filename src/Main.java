import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
	private JPanel jContentPane = null;
	private PanelBall panel = null; // This is the panel of the game class
	
	private PanelBall getPanel() {
		if (panel == null) {
			panel = new PanelBall(); // The panel is created
		}
		return panel;
	}

	public Main() {
		super();
		initialize();
        // Listeners for the keyboard
        this.addKeyListener(new KeyAdapter() {
        	//Method for the key pressed
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt);
            }
            // Method for the key released
            public void keyReleased(KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
		this.setFocusable(true);
		this.requestFocus();
	}
	
    //	Here i'm stating the method that will send the key pressed to the game class
	private void formKeyPressed(KeyEvent e)
    {
        panel.keyPressed(e);
    }
    
    //	Here i'm stating the method that will send the key released to the game class
    private void formKeyReleased(KeyEvent e)
    {
        panel.keyReleased(e);
    }


	private void initialize() {
		this.setResizable(true);
		this.setBounds(new Rectangle(312, 184, 250, 250)); // Position on the desktop
		this.setMinimumSize(new Dimension(250, 250));
		//this.setMaximumSize(new Dimension(250, 250));
		this.setContentPane(getJContentPane());
		this.setTitle("Pong");
	}


	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	public static void main(String[] args) {
		Settings window = new Settings();
		window.setBounds(700, 250, 450, 90);
	    window.setDefaultCloseOperation(HIDE_ON_CLOSE);
	    window.setResizable(false);
	    
	    window.setTitle("Settings");
	    window.setVisible(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main thisClass = new Main();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
}

