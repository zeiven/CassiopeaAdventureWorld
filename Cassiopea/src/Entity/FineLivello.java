package Entity;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;

import TileMap.mattonciniMap;

public class FineLivello extends Nemico{

	Image image;
	
	public FineLivello(mattonciniMap mattonciniMap) {
		super(mattonciniMap);
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		try {
			image = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Enemies/Bandiera1.gif"
				)
			);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

public void draw(Graphics2D g) {
		
		//if(notOnScreen()) return;
		setMapPosition();
		super.draw(g);
		
		
	}
}
