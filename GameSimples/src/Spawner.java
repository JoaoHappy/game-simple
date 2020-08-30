import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawner {

	public int timer = 0;
	
	public List<RectObjs> rectangles = new ArrayList<RectObjs>();
	
	public List<Particula> particulas = new ArrayList<Particula>();
	
	public  void update() {
		timer++;
		if(timer % 60 == 0) {
			rectangles.add(new RectObjs(0, new Random().nextInt(480-40), 40, 40));
		}
		
		for(int i = 0; i < rectangles.size(); i++) {
			
			RectObjs current = rectangles.get(i);
			
			rectangles.get(i).update();
			
			if(current.x > Game.width) {
				rectangles.remove(current);
				Game.contador-=20;
			}
			
			if(Game.clicado) {
				if(Game.mx >= current.x && Game.mx < current.x + current.width) {
					if(Game.my >= current.y && Game.my < current.y + current.height) {
						rectangles.remove(current);
						Game.score++;
						Game.clicado = false;
						
						for(int n = 0; n < 50; n++) {
							particulas.add(new Particula(current.x,current.y,8,8,current.color));
						}
					}
					
				}
			}
		}
		
		for(int i = 0; i < particulas.size(); i++){
			particulas.get(i).update();
			
			Particula part = particulas.get(i);
			if(part.timer >= 60) {
				particulas.remove(part);
			}
		}
	}
	
	public void render(Graphics g) {
		
		
		
		for(int i = 0; i < rectangles.size(); i++ ) {
			
			RectObjs current = rectangles.get(i);
			Graphics2D g2 = (Graphics2D) g;
			g2.rotate(Math.toRadians(current.rotation), current.x+current.width/2, current.y+current.height/2);
			g2.setColor(current.color);
			g2.fillRect(current.x, current.y, current.width, current.height);
			g2.rotate(Math.toRadians(-current.rotation), current.x+current.width/2, current.y+current.height/2);
		}
		for(int i = 0; i < particulas.size(); i++){
			particulas.get(i).render(g);
		}
	}
	
}
