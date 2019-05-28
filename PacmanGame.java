import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.*;

public class PacmanGame {

	public boolean youWon = false;
	Ghost pinkghost;
	Ghost blueghost;
	Ghost redghost;
	Pacman pacman;
	
	int level = 0;

	List<GameObject> gos = new ArrayList<>();
	
	
	List<Coin> coins = new ArrayList<>();

	List<Wall> walls = new ArrayList<>();

	List<Ghost> ghosts = new ArrayList<>();
	
	private int Gsize = 40;
	public static final int down = 0, up = 1, right = 2, left =3;

	public PacmanGame() {
		pacman = new Pacman(0, 0, Gsize, Gsize, null);
		pinkghost = new Ghost(240, 350, Gsize, Gsize, "pinkghost.png");
		blueghost = new Ghost(300, 350, Gsize, Gsize, "_blueghost.png");
		redghost = new Ghost(420, 350, Gsize, Gsize, "redghost.png");
		
		for (int y = Gsize/2; y < 720; y += Gsize) {
			for (int x = Gsize/2; x < 720; x += Gsize) {
				coins.add(new Coin(x, y, 5, 5)); // x, y, w, h
			}
		}
		
		walls.add(new Wall(40, 40, 160, 160));// two squares
		walls.add(new Wall(520, 40, 160, 160));// two squares
		walls.add(new Wall(0, 280, 40, 400));// L shaped ones vertical
		walls.add(new Wall(0, 680, 400, 40)); // horizontal
		walls.add(new Wall(640, 250, 40, 300));// vertical ones at the bottom
		walls.add(new Wall(80, 320, 40, 310));
		ghosts.add(pinkghost);
		ghosts.add(blueghost);
		ghosts.add(redghost);
		gos.add(pacman);
//		level++;
	}

	private void loadLevel() {
		// this is just an idea. Maybe store the different levels as text files
		List<List<GameObject>> levelObjects = LevelReader.readInLevel(level);
		gos.clear();
		if (levelObjects != null) {
			for (List<GameObject> list : levelObjects) {
				if (list != null) {
					for (GameObject go : list) {
//						if(go instanceof Log)
//							logs.add(go);
//						if(go instanceof Auto)
//							autos.add(go);
						gos.add(go);
					}
				}
			}
		}
		gos.add(pacman);
		ghosts.add(pinkghost);
		ghosts.add(blueghost);
		ghosts.add(redghost);
	}

	// What do you want to do when a key is hit?
	public void keyHit(String s) {
		if(coins.size() == 29) {
			youWon = true;
		}
		for(int i = 0; i < coins.size(); i++) {
			if(pacman.hit(coins.get(i))) {
				coins.remove(i);
			}
		}
		
		if(s.equals("left")) {
			int pacmansX = pacman.getLocx()-9;
			int pacmansY = pacman.getLocy();
			int num = 0; // number of overlapping walls
			for(int i = 0; i < walls.size(); i++) {
				int wallsLeftX = walls.get(i).getLocx();
				int wallsRightX = walls.get(i).getLocx() + walls.get(i).getWidth();
				int wallsNorthY = walls.get(i).getLocy();
				int wallsSouthY = walls.get(i).getLocy() + walls.get(i).getHeight();
				if(pacmansX -30 <0) {
					num++;
				}
				if(pacmansX > wallsLeftX && pacmansX < wallsRightX) {
					if(pacmansY < wallsSouthY && pacmansY > wallsNorthY) {
						num++;
					}
					if(pacmansY+39 <wallsSouthY && pacmansY +39 >wallsNorthY) {
						num++;
					}
			
				}
			}
			if(num == 0) {
				pacman.moveLeft();
			}
		
			}
			

			
		if(s.equals("right")) {
			int pacmansRight = pacman.getLocx() + 50;
			int pacmansY = pacman.getLocy();
			int num = 0; // number of overlapping walls
			for(int i = 0; i < walls.size(); i++) {
				int wallsLeftX = walls.get(i).getLocx();
				int wallsRightX = walls.get(i).getLocx() + walls.get(i).getWidth();
				int wallsNorthY = walls.get(i).getLocy();
				int wallsSouthY = walls.get(i).getLocy() + walls.get(i).getHeight();
				if(pacmansRight+20 > 720) {
					num++;
				}
				if(pacmansRight > wallsLeftX && pacmansRight < wallsRightX) {
					if(pacmansY < wallsSouthY && pacmansY > wallsNorthY) {
						num++;
					}
					if(pacmansY+40 <wallsSouthY && pacmansY +40 >wallsNorthY) {
						num++;
					}
				}
			}
			if(num == 0) {
				pacman.moveRight();
			}
			
		}
		if(s.equals("up")) {
			int pacmansX = pacman.getLocx();
			int pacmansY = pacman.getLocy() -10;
			int num = 0; // number of overlapping walls
			for(int i = 0; i < walls.size(); i++) {
				int wallsLeftX = walls.get(i).getLocx();
				int wallsRightX = walls.get(i).getLocx() + walls.get(i).getWidth();
				int wallsNorthY = walls.get(i).getLocy();
				int wallsSouthY = walls.get(i).getLocy() + walls.get(i).getHeight();
				if(pacmansY -30 <0) {
					num++;
				}
				if(pacmansY < wallsSouthY && pacmansY > wallsNorthY) {
					if(pacmansX > wallsLeftX && pacmansX < wallsRightX) {
						num++;
					}
					if(pacmansX+39 > wallsLeftX && pacmansX +39 < wallsRightX) {
						num++;
					}
					
				}
			}
			if(num == 0) {
				pacman.moveUp();
			}
		}
		if(s.equals("down")) {
			
			int pacmansX = pacman.getLocx();
			int pacmansY = pacman.getLocy()+50;
			int num = 0; // number of overlapping walls
			for(int i = 0; i < walls.size(); i++) {
				int wallsLeftX = walls.get(i).getLocx();
				int wallsRightX = walls.get(i).getLocx() + walls.get(i).getWidth();
				int wallsNorthY = walls.get(i).getLocy();
				int wallsSouthY = walls.get(i).getLocy() + walls.get(i).getHeight();
				if(pacmansY +20 >720) {
					num++;
				}
				if(pacmansY < wallsSouthY && pacmansY > wallsNorthY) {
					if(pacmansX > wallsLeftX && pacmansX < wallsRightX) {
						num++;
					}
					if(pacmansX+39 > wallsLeftX && pacmansX +39 < wallsRightX) {
						num++;
					}
				}
				
			}
			if(num == 0) {
				pacman.moveDown();
			}
		}				
	}



	public void draw(Graphics g) {
		for(GameObject go:gos) {
			go.draw(g);
		}
		for(GameObject ghost: ghosts) {
			ghost.draw(g);
		}
		for(Coin c: coins) {
			c.draw(g);
		}
		for(Wall w: walls) {
			w.draw(g);
			
		}
		
	}

	public void randomMove() {
		//math.random 4 and determine which way the ghost will go; every time it moves left or right it needs to check if it can turn
			int direction = (int) ((Math.random())*4);
			if(direction == down) {
				if(checkHitWall(ghosts.get(2))==false){
					ghosts.get(2).moveDown();
				}
			}
			if(direction == up) {
				if(checkHitWall(ghosts.get(2))==false) {
					ghosts.get(2).moveUp();
				}
			}
			if(direction == right) {
				if(checkHitWall(ghosts.get(2))==false) {					
					ghosts.get(2).moveRight();
				}
			}
			if(direction ==left) {
				if(checkHitWall(ghosts.get(2))==false) {
					ghosts.get(2).moveLeft();
					}
			}
		}
	

	
	public boolean checkHitWall(Ghost g) {
		for(int i = 0; i < walls.size(); i ++) {
			if(g.hit(walls.get(i))) {
				return true;
			}
		}
		return false;
		
	}
	
}
