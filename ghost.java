	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.Rectangle;
	import java.io.File;
	import java.io.IOException;
	import java.io.InputStream;
	import javax.imageio.ImageIO;
	import javax.imageio.stream.ImageInputStream;




public class ghost extends GameObject {
	private Image image;
	private Rectangle rect;
	private int locx, locy, width, height;
//	private Direction d;
	public ghost(int x, int y, int w, int h, String str) {
		super(x, y, w, h, str);
		rect = new Rectangle(x,y,w,h);
		locx = x;
		locy = y;
	}
	
	public void randomMove() {
		//first make all the ghosts get out otf the box from the exit this will be done in the pacmangame class
		//math.random 4 and determine which way the ghost will go; every time it moves left or right it needs to check if it can turn
		int direction = (int) ((Math.random())*4);
		
		if(direction == 0) {
			moveDown();
		}
		if(direction == 1) {
			moveUp();
		}
		if(direction == 2) {
			moveRight();
		}
		if(direction ==3) {
			moveLeft();
		}
		System.out.println("move");
	}
	
	
	private boolean nearwall() {
	
		return false;
	}
	
	public void follow(Pacman p) {
		int diffx = p.getLocx()-this.getLocx();
		int diffy = p.getLocy()-this.getLocy();
		if(diffx < 0) {
				this.moveLeft();
			
		}
		if(diffx > 0) {
				this.moveRight();
			
		}
		if(diffy < 0) {
				this.moveUp();
			
		}
		if(diffy>0) {
				this.moveDown();
			
		}
		
	}

	

}

	
	

