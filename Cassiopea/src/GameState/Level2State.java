package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Entity.BarraVita;
import Entity.Cassiopea;
import Entity.Fireball;
import Entity.Nemico;
import Main.GamePanel;
import TileMap.Background;
import TileMap.mattonciniMap;

public class Level2State extends GameState{

	mattonciniMap mattonciniMap; // mattoncini map
	Background bg;
	Fireball fireball;
	
	ArrayList<Nemico> nemici;
	BarraVita barraVita;
	
	
	public Level2State(GameStateManager gsm)
	{
		this.gsm=gsm;
		init();
	}
	@Override
	public void init() {
		
		mattonciniMap = new mattonciniMap(30);
		mattonciniMap.loadmattoncinis("/Tilesets/provaMondo.gif"); // è l'immagine intera di quello che creerà il mondo, mattoncini ecc ecc
		mattonciniMap.loadMap("/Maps/level1-1.map"); //primo livello..fa na pocu schifu, però CAMINA !!
		mattonciniMap.setPosition(0, 0);
		
		
		bg = new Background("/Backgrounds/acquario2.bmp", 0.1);
		
		cassiopea = new Cassiopea(mattonciniMap);
		cassiopea.setPosition(100, 100);
		fireball=new Fireball(mattonciniMap);
		barraVita= new BarraVita(cassiopea);
		
	}

	@Override
	public void update() {
		// update cassy<3
		cassiopea.update();
		
		//**********SETPOSITION SERVE A FARE "LA MATRICE A SCORRIMENTO" 

		mattonciniMap.setPosition(
			GamePanel.WIDTH / 2 - cassiopea.getx(),
			GamePanel.HEIGHT / 2 - cassiopea.gety()
		);
		
		// set background
		bg.setPosition(mattonciniMap.getx(), mattonciniMap.gety());
		
		for(int i = 0; i < nemici.size(); i++) {
			Nemico e = nemici.get(i);
			e.update();}
		
	}

public void draw(Graphics2D g) {
		
		// draw background
		bg.draw(g);
		
		// draw 
		mattonciniMap.draw(g);
		
		// draw cassy
		cassiopea.draw(g);
		
		for(int i = 0; i < nemici.size(); i++) {
			nemici.get(i).draw(g);
		}
		barraVita.draw(g);
		fireball.draw(g);
		
	}
	
	
	
}