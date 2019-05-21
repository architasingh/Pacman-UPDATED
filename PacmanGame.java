import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.*;

public class PacmanGame {

	// what are the things you see in the game?
	List<GameObject> gos = new ArrayList<>();
	
	// list of autos should come from loadLevel
	List<GameObject> coins = new ArrayList<>();
	//list of walls
	List<Wall> walls = new ArrayList<>();
	// list of logs
	List<GameObject> ghosts = new ArrayList<>();
	private int Gsize = 40;
	
	ghost pinkghost;
	ghost blueghost;
	ghost redghost;
	ghost yellowghost;
	Pacman pacman;
	int level=0;
	public PacmanGame() {
		
		pacman = new Pacman(0,0, Gsize, Gsize,  null);
		pinkghost = new ghost(240,350,Gsize,Gsize, "pinkghost.png");
		blueghost = new ghost(300,350,Gsize,Gsize, "blueghost.png"); 
		yellowghost = new ghost(360,350,Gsize,Gsize, "yellowghost.png");
		redghost = new ghost(420,350,Gsize,Gsize, "redghost.png");
		walls.add(new Wall(40,40, 160, 160));//two squares
		walls.add(new Wall(520,40,160, 160));//two squares
		walls.add(new Wall(0,280,40, 400));//L shaped ones vertical
		walls.add(new Wall(0,680, 400,40)); //horizontal
		walls.add(new Wall(640,250,40,300));//vertical ones at the bottom
		walls.add(new Wall(80,330,40,310));
		ghosts.add(pinkghost);
		ghosts.add(blueghost);
		ghosts.add(redghost);
		ghosts.add(yellowghost);
		gos.add(pacman);
		level++;
		for(int i=0; i < 10;)
		
		loadLevel();
	}
	
	
	
	private void loadLevel() {
		// this is just an idea.  Maybe store the different levels as text files
		List<List<GameObject>> levelObjects = LevelReader.readInLevel(level);
		gos.clear();
		if(levelObjects != null) {
			for(List<GameObject> list: levelObjects) {
				if(list != null) {
					for(GameObject go: list) {
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
		ghosts.add(yellowghost);
		
	}



	// What do you want to do when a key is hit?
	public void keyHit(String s) {
		System.out.println("In pacman game (keyHit): "+s);
		if(s.equals("left")) {
			
			int pacmansX = pacman.getLocx()-9;
			int pacmansY = pacman.getLocy();
			int num = 0; // number of overlapping walls
			for(int i = 0; i < walls.size(); i++) {
				int wallsLeftX = walls.get(i).getLocx();
				int wallsRightX = walls.get(i).getLocx() + walls.get(i).getWidth();
				int wallsNorthY = walls.get(i).getLocy();
				int wallsSouthY = walls.get(i).getLocy() + walls.get(i).getHeight();
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
		System.out.println("In frogger game (keyHit): "+s);
		
			
	}



	public void draw(Graphics g) {
		for(GameObject go:gos) {
			go.draw(g);
		}
		for(GameObject ghost: ghosts) {
			ghost.draw(g);
		}
		for(Wall w: walls) {
			w.draw(g);
			
		}
		
	}
	
	public void randomMove() {
		//first make all the ghosts get out otf the box from the exit this will be done in the pacmangame class
		//math.random 4 and determine which way the ghost will go; every time it moves left or right it needs to check if it can turn
		for(int g = 0; g < ghosts.size(); g++) {
			for(int w = 0; w < walls.size(); w++) {
				int direction = (int) ((Math.random())*4);
				
				if(direction == 0) {
					if(!(ghosts.get(g).hit(walls.get(w)))) {
						ghosts.get(g).moveDown();
					}
				}
				if(direction == 1) {
					if(!(ghosts.get(g).hit(walls.get(w)))) {
						ghosts.get(g).moveUp();
					}
				}
				if(direction == 2) {
					if(!(ghosts.get(g).hit(walls.get(w)))) {					
						ghosts.get(g).moveRight();
					}
				}
				if(direction ==3) {
					if(!(ghosts.get(g).hit(walls.get(w)))) {
						ghosts.get(g).moveLeft();
						}
			}
			}
		}
		}

	public boolean checkMoveDown(GameObject go) {
		int goX = go.getLocx();
		int goY = go.getLocy()+50;
		int num = 0; // number of overlapping walls
		for(int i = 0; i < walls.size(); i++) {
			int wallsLeftX = walls.get(i).getLocx();
			int wallsRightX = walls.get(i).getLocx() + walls.get(i).getWidth();
			int wallsNorthY = walls.get(i).getLocy();
			int wallsSouthY = walls.get(i).getLocy() + walls.get(i).getHeight();
			if(goX < wallsSouthY && goY > wallsNorthY) {
				if(goX > wallsLeftX && goX < wallsRightX) {
					num++;
				}
				if(goX+39 > wallsLeftX && goX +39 < wallsRightX) {
					num++;
				}
			}
			
		}
		if(num == 0) {
			return true;
		}
		return false;
		
	}
	
	public boolean checkMoveUp(GameObject go) {
		int goX = go.getLocx();
		int goY = go.getLocy() -10;
		int num = 0; // number of overlapping walls
		for(int i = 0; i < walls.size(); i++) {
			int wallsLeftX = walls.get(i).getLocx();
			int wallsRightX = walls.get(i).getLocx() + walls.get(i).getWidth();
			int wallsNorthY = walls.get(i).getLocy();
			int wallsSouthY = walls.get(i).getLocy() + walls.get(i).getHeight();
			if(goX < wallsSouthY && goY > wallsNorthY) {
				if(goX > wallsLeftX && goX < wallsRightX) {
					num++;
				}
				if(goX+39 > wallsLeftX && goX +39 < wallsRightX) {
					num++;
				}
				
				
			}
		}
		if(num == 0) {
			return true;
		}
		return false;
	}

	public boolean checkMoveRight(GameObject go) {
		int goX = go.getLocx() + 50;
		int goY = go.getLocy();
		int num = 0; // number of overlapping walls
		for(int i = 0; i < walls.size(); i++) {
			int wallsLeftX = walls.get(i).getLocx();
			int wallsRightX = walls.get(i).getLocx() + walls.get(i).getWidth();
			int wallsNorthY = walls.get(i).getLocy();
			int wallsSouthY = walls.get(i).getLocy() + walls.get(i).getHeight();
			if(goX > wallsLeftX && goX < wallsRightX) {
				if(goY < wallsSouthY && goY > wallsNorthY) {
					num++;
				}
				if(goY+40 <wallsSouthY && goY +40 >wallsNorthY) {
					num++;
				}
				
			}
		}
		if(num == 0) {
			return true;
		}
		return false;
	}

	public boolean checkMoveLeft(GameObject go) {
			
			int goX = go.getLocx()-9;
			int goY = go.getLocy();
			int num = 0; // number of overlapping walls
			for(int i = 0; i < walls.size(); i++) {
				int wallsLeftX = walls.get(i).getLocx();
				int wallsRightX = walls.get(i).getLocx() + walls.get(i).getWidth();
				int wallsNorthY = walls.get(i).getLocy();
				int wallsSouthY = walls.get(i).getLocy() + walls.get(i).getHeight();
				if(goX > wallsLeftX && goX < wallsRightX) {
					if(goY < wallsSouthY && goY > wallsNorthY) {
						num++;
					}
					if(goY+39 <wallsSouthY && goY +39 >wallsNorthY) {
						num++;
					}
				}
			}
			
			if(num == 0) {
				return true;
			}
			return false;
		
	}
	
	
}
