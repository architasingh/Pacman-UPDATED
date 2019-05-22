import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class GameObject {

	private static final double OVERLAP_THRESHOLD = 1, HIT_THRESHOLD = .75;
	public final static String PATH_PREFIX = "res/images/";
	private Rectangle rect;
	
	private Image image;
	private int locx, locy;
	//private SoundClip sound;  // MUST be a class for this!!
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
		locx = x;
		locy = y;
		image = getImage(str);
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
			g.drawImage(image, rect.x, rect.y, rect.width, rect.height, null);
			g.drawRect(rect.x, rect.y, rect.width, rect.height);
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
		return overArea > Math.min(thisArea, goArea)*HIT_THRESHOLD;
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

	public void setLocx(int locx) {
		this.locx = locx;
	}

	public int getLocy() {
		return locy;
	}

	public void setLocy(int locy) {
		this.locy = locy;
	}

	public void move(int i, int j) {
		this.getRect().translate(i*(int)(this.getRect().getWidth())*2, j*(int)(this.getRect().getHeight())*2);
		this.setLocx(this.getLocx());
		this.setLocy(this.getLocy());
		locx = this.getRect().x;
		locy = this.getRect().y;
		
		System.out.println(""+locx);
		System.out.println("" + locy);
		
	}
	
	
	public void moveLeft() {
		move(-1,0);
		if (!withinBounds()) {
			moveRight();
		}
	}
	
	public void moveRight() {
		move(1,0);
		if (!withinBounds()) {
			moveLeft();
		}

	}
	
	public void moveUp() {
		move(0,-1);
		if (!withinBounds()) {
			moveDown();
		}

	}

	public void moveDown() {
		move(0,1);
		if (!withinBounds()) {
			moveUp();
		}

	}
	
	private boolean withinBounds() {
		if (this.getLocx() > PacmanGameRunner.WIDTH || this.getLocx() < 0) {
			return false;
		}
		if (this.getLocy() > PacmanGameRunner.HEIGHT || this.getLocy() < 0) {
			return false;
		}
		return true;
	}
}
