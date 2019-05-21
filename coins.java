import java.awt.Image;
import java.awt.Rectangle;

public class Coin extends GameObject{
	private Image coin;
	private boolean show;
	private int locx, locy;
	private Rectangle rect;
	
	
	Coin(int x, int y, int w, int h){
		super(x,y,w,h, "coin.png");
		locx = x;
		locy = y;
		coin = getImage("coin.png");
		show = true;
		
	}
	
	public void disappear() {
		show = false;
		coin = null;
	}
}
