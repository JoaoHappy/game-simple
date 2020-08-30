import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,MouseListener,KeyListener{

	public static final int width = 640, height = 480;
	
	public static byte contador = 100;
	
	public static int score = 0;
	
	byte perdeVida = 100;
	
	public static int mx, my;
	
	public static boolean clicado = false;
	
	public Spawner spawner;
	
	public boolean gameOver = false;
	
	static boolean space = false;
	
	public Game() {
		Dimension dimension = new Dimension(width, height);
		this.setPreferredSize(dimension);
		this.addMouseListener(this);
		this.addKeyListener(this);
		
		
		spawner = new Spawner();
	}
	
	
	
	public void update() {
		
		if(gameOver == false) {
			spawner.update();
			
			if(contador <= 0) {
				//game over
				contador = 100;
				gameOver = true;
			}
		}
		
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		if(gameOver == false) {		
		/*
		g.setColor(Color.white);
		
		g.setFont(new Font("Arial", Font.BOLD,23));
		
		g.drawString("Pontos: " + contador, width/2 -60 , 30);
		
		*/
		
		g.setColor(Color.green);
		g.fillRect(Game.width/2 - perdeVida - 160, 20, contador*3, 20);
		g.setColor(Color.white);
		g.drawRect(Game.width/2 - perdeVida - 160, 20, 300, 20);
		
		g.setFont(new Font("Arial", Font.BOLD,23));
		
		g.drawString("Pontos: " + Game.score, width/2 +60 , 30);
		
		spawner.render(g);
	}else {
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD,30));
		g.drawString("Game Over :(", width/2 - 100 , height/2 - 100);
		
		g.drawString("Press Enter to Play Again!", width/2 - 200 , height/2 + 80);
		
	}
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame jframe  = new JFrame("My game");
		jframe.add(game);
		jframe.setLocationRelativeTo(null);
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jframe.setVisible(true);
		
		new Thread(game).start();
	}
	
	@Override
	public void run() {
		
		while(true) {
			update();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		clicado = true;
		mx = e.getX();
		my = e.getY();
		
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		clicado = true;
		mx = e.getX();
		my = e.getY();
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			space = true;
			System.out.print("espaço");
		}
		
	}



	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
