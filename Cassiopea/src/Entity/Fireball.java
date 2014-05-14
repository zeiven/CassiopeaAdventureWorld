package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import TileMap.mattonciniMap;

public class Fireball extends MapObject {

	
	boolean hit=false;
	Animation animation;
	private BufferedImage[] sprites;
	
	public Fireball(mattonciniMap tm) {
		super(tm);
		height=15;
		width=15;
		cheight=10;
		cwidth=10;
		moveSpeed=3.0;
		
try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Player/fireball.gif"
				)
			);
			
				sprites = new BufferedImage[8];
				for(int i = 0; i < sprites.length; i++) {
					sprites[i] = spritesheet.getSubimage(i * width,0,width,height);
				}
			
			
}
		catch(Exception e) {
			e.printStackTrace();
		}
		animation=new Animation();
		animation.setFrames(sprites);
		animation.setDelay(100);
		
		if(right)
		{
			//muovi a destra
			dx=moveSpeed;
		}
		if(left)
		{
			//muovi a sinistra
			dx=-moveSpeed;
		}
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
		
	}
	
	void update()
	{
		animation.update();
	}
}
