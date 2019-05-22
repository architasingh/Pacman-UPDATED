import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;



public class PacmanGameRunner {
	private JPanel panel;
	private PacmanGame game = new PacmanGame();
	private Timer timer;
	private int ticks;
	public final static String PATH_PREFIX = "res/images/";

	protected  Image getImage(String fn) {
		Image img = null;
		fn = PATH_PREFIX+fn;
		try {
			
			img = ImageIO.read(this.getClass().getResource(fn));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	// Notice this intuitive method for finding the screen size 
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WIDTH = 720,HEIGHT=720;
	private static final int REFRESH_RATE = 500;
//	public Image maze = getImage("maze.png");	
	public PacmanGameRunner() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					start();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				}
			}
		});
	}

	public static void main(String[] args) {
		new PacmanGameRunner();

	}

	private void start() {
		JFrame frame = new JFrame("Pacman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
//				g.drawImage(maze, 0, 0, 1080, 675, null);
				drawGame(g);

				
			}
		};
		// random color to the background
		panel.setBackground(new Color(0, 255, 255));
		
		// so that the frame isn't minimized
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
	
		// so that the frame is placed a little way from top and left side
		frame.setLocation(WIDTH/10, HEIGHT/10);

		// map the keystrokes that the panel detects to the game
		mapKeyStrokesToActions(panel);

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		// after setting visible to true, you can get focus.  You need focus to consume
		// the keystrokes hit by the user
		panel.requestFocusInWindow();
		
		// this timer controls the actions in the game and then repaints after each update to data
		timer = new Timer(REFRESH_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateGame();
				panel.repaint();
			}
		});
		timer.start();
	}

	// this method is called every time the timer goes off (which right now is every 10 milliseconds = 100 times per second
	protected void updateGame() {
		ticks++;// keeps track of the number of times the timer has gone off
		
		int hurts = 1000/1000;
		if(ticks %hurts == 0) {
			panel.repaint();
			System.out.println(ticks/hurts+" seconds");
			
			if(ticks/hurts > 5 ) {
				game.blueghost.randomMove();
			}
//			if(ticks/hurts > 6) {
//				game.yellowghost.randomMove();
//			}
			if(ticks/hurts > 7) {
				game.pinkghost.randomMove();
			}
			if(ticks/hurts > 8) {
				game.redghost.randomMove();
			}
		}
		
		}
		
	

	private void mapKeyStrokesToActions(JPanel panel) {

		// A map is an Data storage interface which defines
		// an association of a key with a value
		// to "add" to a map you use the "put" method
		// to "get" from a map you use "get(key)" and the 
		// value associated with the key is returned (or null)
		ActionMap map = panel.getActionMap();
		InputMap inMap = panel.getInputMap();

		// code below associates pressing the up arrow with the command "up"
		// essentially creating the command "up" being broadcast any time the 
		// up key is hit
		inMap.put(KeyStroke.getKeyStroke("pressed UP"), "up");
		inMap.put(KeyStroke.getKeyStroke("pressed RIGHT"), "right");
		inMap.put(KeyStroke.getKeyStroke("pressed DOWN"), "down");
		inMap.put(KeyStroke.getKeyStroke("pressed LEFT"), "left");
		// code below associates the "up" action with anything in the 
		// actionPerformed method.  Right now, it just prints something
		map.put("up", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hit("up");
			}

			
		});
		panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"left");
		panel.getActionMap().put("left",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				hit("left");
			}
		});
		panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
		panel.getActionMap().put("right",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				hit("right");
			}
		});
		panel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"down");
		panel.getActionMap().put("down",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				hit("down");
			}
		});


	}
	public void hit(String s) {
		game.keyHit(s);
		panel.repaint();
	}
	protected void drawGame(Graphics g) {
		game.draw(g);
	}

