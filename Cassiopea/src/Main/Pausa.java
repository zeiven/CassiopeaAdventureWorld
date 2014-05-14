package Main;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;

public class Pausa {

	private Font font;
	private Image image;
	public Pausa()
	{
		try {
			image = ImageIO.read(
				getClass().getResourceAsStream(
					"/Pausa/Pause2.png"
				)
			);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
public void draw(Graphics2D g) {
		
	g.drawImage(image, 0, 0, null);	
}
}
