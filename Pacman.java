import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ActionMap;

public class Pacman extends PlayerControlledGameObject {
	
	private static final double OVERLAP_THRESHOLD = .75, HIT_THRESHOLD = .05;
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
	
	public void move(int i, int j) {
		this.getRect().translate(i*(int)((this.getRect().getWidth())/4), j*(int)(this.getRect().getHeight())/4);
		locx = this.getRect().x;
		locy = this.getRect().y;
		
		System.out.println(""+locx);
		System.out.println("" + locy);

		
	}
	public int getLocy() {
		return locy;
	}
	public int getLocx() {
		return locx;
	}
	
	
	public void moveLeft() {
		move(-1,0);
		this.setLocx(this.getLocx());
		this.setLocy(this.getLocy());
		setImage(left);
	}
	
	public void moveRight() {
		move(1,0);
		this.setLocx(this.getLocx());
		this.setLocy(this.getLocy());
		setImage(right);
	}

	public void moveUp() {
		move(0,-1);
		this.setLocx(this.getLocx());
		this.setLocy(this.getLocy());
		
	}
	
	public void moveDown() {
		move(0,1);
		this.setLocx(this.getLocx());
		this.setLocy(this.getLocy());


	}
	public boolean hit(GameObject go) {
		Rectangle over = collisionRect(go);
		if(over.isEmpty())
			return false;
		double thisArea = area(rect), 
				goArea = area(go.getRect()),
				overArea = area(over);
		return overArea > Math.min(thisArea, goArea)*HIT_THRESHOLD;
	}
	public Rectangle collisionRect(GameObject go) {
		return this.rect.intersection(go.getRect());
	}

	public boolean mostlyOverlapping(GameObject go) {
		Rectangle over = collisionRect(go);
		double thisArea = area(this.rect), 
				goArea = area(go.getRect()),
				overArea = area(over);
		return overArea > Math.min(thisArea, goArea)*OVERLAP_THRESHOLD;
	}
}
