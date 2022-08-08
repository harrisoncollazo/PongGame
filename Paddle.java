import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Paddle extends Rectangle{

	int id;
	int yVelocity;
	int speed = 10;
	
	

	//
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id=id;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(speed);
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(speed);
			}
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(0);
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(0);
			}
			break;
		}
	}
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}

	public void move() {
		y = y + yVelocity;
	}
	

	
	// added by Gisela
	Color playerOneFillColor = Color.blue;
	Color playerTwoFillColor = Color.red;
	Color playerOneFontColor = Color.white;
	Color playerTwoFontColor = Color.white;
	static String clashSoundPath = new File("").getAbsolutePath() + "/clash_sound.wav";

	// modified by Gisela
	public void draw(Graphics g) {
			if (id == 1) {
				g.setColor(playerOneFillColor);
				g.fillOval(x, y, width, height);
				g.setColor(playerOneFontColor);
				g.drawString("O", x + 10, y + 40);
				g.drawString("N", x + 10, y + 55);
				g.drawString("E", x + 10, y + 70);

			} else {
				g.setColor(playerTwoFillColor);
				g.fillOval(x, y, width, height);
				g.setColor(playerTwoFontColor);
				g.drawString("T", x + 10, y + 40);
				g.drawString("W", x + 10, y + 55);
				g.drawString("O", x + 10, y + 70);
			}
		
		
	}

	// Added By Gisela
	public static synchronized void playCrashSound() {
		new Thread(new Runnable() { 
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream =
					 AudioSystem.getAudioInputStream( new File(clashSoundPath));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}
}