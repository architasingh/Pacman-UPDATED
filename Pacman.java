import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ActionMap;

public class Pacman extends PlayerControlledGameObject {
	
	private Image left = getImage("pacmanLeft.png");
	private Image right = getImage("pacman.png");
	private Image up = getImage("pacmanUp.png");
	private Image down = getImage("pacmanDown.png");
	
	
	public Pacman(int x, int y, int w, int h, ActionMap am) {
		super(x, y, w, h, "pacman.png", am);
		}
	
	public void moveLeft() {
		super.moveLeft();
		setImage(left);
	}
	
	public void moveRight() {
		super.moveRight();
		setImage(right);

	}
	public void moveUp() {
		super.moveUp();
		setImage(up);
	}
	
	public void moveDown() {
		super.moveDown();
		setImage(down);
	}

}
