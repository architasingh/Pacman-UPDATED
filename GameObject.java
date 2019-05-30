import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class GameObject {

	private static final double OVERLAP_THRESHOLD = 1, HIT_THRESHOLD = .5;
	public final static String PATH_PREFIX = "res/images/";
	public boolean gameover = false;
	private Rectangle rect;
	private Image image;
	protected int locx;
	protected int locy;
	public int down = 0, up = 1, right = 2, left =3;
	public int direction;

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
	
	public GameObject(int x, int y, int w, int h, String str) {
		rect = new Rectangle(x,y,w,h);
		image = getImage(str);
		locx = x;
		locy = y;
	}
	
	public GameObject(int x, int y, int w, int h) {
		rect = new Rectangle(x,y,w,h);
		locx = x;
		locy = y;
	}
	
	public void setImage(Image i) {
		image = i;
	}

	public void draw(Graphics g) {
		if(image != null) {
			g.drawImage(image, locx, locy, rect.width, rect.height, null);
		}
	}
	public static double area(Rectangle rect) {
		return rect.width*rect.height;
	}
	
	public boolean hit(GameObject go) {
		Rectangle over = collisionRect(go);
		if(over.isEmpty())
			return false;
		double thisArea = area(this.rect), 
				goArea = area(go.getRect()),
				overArea = area(over);
		return overArea >= Math.min(thisArea, goArea)*HIT_THRESHOLD;
	}
	public Rectangle collisionRect(GameObject go) {
		return this.rect.intersection(go.getRect());
	}

	public boolean mostlyOverlapping(GameObject go) {
		Rectangle over = collisionRect(go);
		double thisArea = area(this.rect), 
				goArea = area(go.rect),
				overArea = area(over);
		return overArea > Math.min(thisArea, goArea)*OVERLAP_THRESHOLD;
	}
	public Rectangle getRect() {
		return this.rect;
	}

	public int getLocx() {
		return locx;
	}

	public void setLocx(int x) {
		locx = x;
	}

	public int getLocy() {
		return locy;
	}

	public void setLocy(int y) {
		locy = y;
	}

	public void move(int i, int j) {
		System.out.println("move: rect=" + this.getRect());
		this.getRect().translate(i*(int)(this.getRect().getWidth()),
				j*(int)(this.getRect().getHeight()));
		locx = this.getRect().x;
		locy = this.getRect().y;
		this.setLocx(this.getLocx());
		this.setLocy(this.getLocy());
		
		System.out.println("move: x="+locx);
		System.out.println("move: y=" + locy);
			
	}
	
	public void moveLeft() {
		move(-1,0);
		direction = left;
		if (!withinBounds()) {
			moveRight();
			direction = right;
		}
	}
	
	public void moveRight() {
		move(1,0);
		direction = right;
		if (!withinBounds()) {
			moveLeft();
			direction = left;
		}

	}
	
	public void moveUp() {
		move(0,-1);
		direction = up;
		if (!withinBounds()) {
			moveDown();
			direction = down;
		}

	}

	public void moveDown() {
		move(0,1);
		direction = down;
		if (!withinBounds()) {
			moveUp();
			direction = up;
		}

	}
	
	private boolean withinBounds() {
		if (this.getLocx() + this.getRect().getWidth() > PacmanGameRunner.WIDTH || this.getLocx() < 0) {
			return false;
		}
		if (this.getLocy() + this.getRect().getHeight() > PacmanGameRunner.HEIGHT || this.getLocy() < 0) {
			return false;
		}
		return true;
	}
}
