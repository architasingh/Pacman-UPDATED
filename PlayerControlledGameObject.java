import java.awt.Image;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;

public class PlayerControlledGameObject extends GameObject {

	ActionMap map ;
	
	public PlayerControlledGameObject(int x, int y, int w, int h, String string, ActionMap am) {
		super(x, y, w, h, string);
		map = am;
	}

	public void putAction(Object command, AbstractAction act) {
		map.put(command, act);
	}
}
