import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ActionMap;

public class Pacman extends PlayerControlledGameObject {
	
	private Rectangle rect;
	private int locx, locy;
	private Image left = getImage("pacmanLeft.png");
	private Image right = getImage("pacman.png");
	private Image up;
	private Image down;
	
	public Pacman(int x, int y, int w, int h, ActionMap am) {
		super(x, y, w, h, "pacman.png", am);
		rect = new Rectangle (x,y,w,h);
		locx = x;
		locy = y;
	}

}
