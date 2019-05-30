import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall  extends GameObject{

	private final int width;
	private final int height;
	private int locx, locy;
	private Rectangle rect;

	public Wall(int x, int y, int w, int h) {
		super(x,y,w,h);
		width = w;
		height = h;
		rect = new Rectangle(x,y,w,h);
		locx = x;
		locy = y;
		
	}

	public void draw(Graphics g) {
		g.setColor(new Color(252, 141, 132));
		g.fillRect(locx, locy, width, height);
	}
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
