package Entity;



import TileMap.mattonciniMap;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class Fitoplancton extends Nemico {
	
	private BufferedImage[] sprites;
	static int contaCibo;
	
	public Fitoplancton(mattonciniMap tm) {
		
		super(tm);

		contaCibo=0;
		moveSpeed = 0.3;
		maxSpeed = 0.3;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		health = maxHealth = 2;
		damage = 1;
		
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Punti/fitopl.gif"
				)
			);
			
			sprites = new BufferedImage[3];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
					i * width,
					0,
					width,
					height
				);
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(300);
		
		right = true;
		facingRight = true;
		
	}
	
	private void getNextPosition() {
		
	}
	
	public void update() {
		
		// update position
		getNextPosition();
		checkmattonciniMapCollision();
		setPosition(xtemp, ytemp);
		
		
		animation.update();
		
	}
	
	public void draw(Graphics2D g) {
		
		//if(notOnScreen()) return;
		
		setMapPosition();
		
		super.draw(g);
		
	}
	
	public void setContaCibo(){
		contaCibo++;
	}
	
	public void ResetContaCibo(){
		contaCibo=0;
	}
	
	public int getContaCibo(){
		return contaCibo;
	}
}











