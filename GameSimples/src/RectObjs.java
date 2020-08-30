import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public class RectObjs extends Rectangle {

	public int speed = 0;
	
	public Color color;
	
	public int rotation = 0;
	
	public RectObjs(int x, int y, int width, int height) {
		super(x,y,width,height);
		
		color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
	
		speed = new Random().nextInt(12-2)+8;
		
	}
	
	public void update() {
		x+=speed;
		rotation+=5;
		if(rotation >= 360) {
			rotation = 0;
		}
	}
}
