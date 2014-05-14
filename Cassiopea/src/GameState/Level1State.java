package GameState;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Enemies.Onde;
import Entity.Enemies.PesciolinoCattivo;
import Entity.Enemies.Riccio;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;

public class Level1State extends GameState {
	
	private mattonciniMap mattonciniMap; // mattoncini map
	private Background bg;
	private Fireball fireball;
	private ArrayList<Nemico> nemici;
	private BarraVita barraVita;
	
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		
		mattonciniMap = new mattonciniMap(30);
		mattonciniMap.loadmattoncinis("/Tilesets/grasstileset1.gif"); // è l'immagine intera di quello che creerà il mondo, mattoncini ecc ecc
		mattonciniMap.loadMap("/Maps/level1-1.map"); //primo livello..fa na pocu schifu, però CAMINA !!
		mattonciniMap.setPosition(0, 0);
		
		
		bg = new Background("/Backgrounds/acquario2.bmp", 0.1);
		
		cassiopea = new Cassiopea(mattonciniMap);
		cassiopea.setPosition(100, 100);
		
		barraVita= new BarraVita(cassiopea);
		popoliamoNemici();
		
		
	}
	
	
private void popoliamoNemici() {
		
		nemici = new ArrayList<Nemico>();
		
		PesciolinoCattivo s;
		Point[] points = new Point[] {
			new Point(100, 300),
			new Point(860, 200),
			new Point(1525, 300),
			new Point(1300, 300),
			new Point(1800, 300)
		};
		for(int i = 0; i < points.length; i++) {
			s = new PesciolinoCattivo(mattonciniMap);
			s.setPosition(points[i].x, points[i].y);
			nemici.add(s);
		}
		
		Riccio r;
		Point[] points1 = new Point[] {
			new Point(200, 300),
			new Point(760, 200),
			new Point(1325, 300),
			new Point(1400, 300),
			new Point(1600, 300)
		};
		for(int i = 0; i < points.length; i++) {
			r = new Riccio(mattonciniMap);
			r.setPosition(points1[i].x, points1[i].y);
			nemici.add(r);
		}
		
		Fitoplancton f;
		Point[] points2 = new Point[] {
			new Point(300, 200),
			new Point(660, 200),
			new Point(1300, 300),
			new Point(1400, 300),
			new Point(1100, 300)
		};
		for(int i = 0; i < points.length; i++) {
			f = new Fitoplancton(mattonciniMap);
			f.setPosition(points2[i].x, points2[i].y);
			nemici.add(f);
		}
		Onde o;
		Point[] points3 = new Point[] {
			new Point(565, 400),
			new Point(540, 400),
			new Point(525, 400),
			new Point(500, 400),
			new Point(580, 400)
		};
		for(int i = 0; i < points.length; i++) {
			o = new Onde(mattonciniMap);
			o.setPosition(points3[i].x, points3[i].y);
			nemici.add(o);
		}
		FineLivello fin=new FineLivello(mattonciniMap);
		fin.setPosition(510,320 );
		nemici.add(fin);

	}
	
	
	public void update() {
		
		// update cassy<3
		cassiopea.update();
		System.out.println(cassiopea.getx()+" "+cassiopea.gety());
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
cassiopea.checkAttack(nemici);	

if( cassiopea.health==0 && cassiopea.lives>0)
{
	cassiopea.health=5;
	cassiopea.setPosition(100, 100);
	cassiopea.lives--;
}
if(cassiopea.lives==0)
{
	gsm=new GameStateManager();
	gsm.setState(1);
	this.init();
}
	
	}
	
	public void draw(Graphics2D g) {
		
		// draw background
		bg.draw(g);
		
		// draw 
		mattonciniMap.draw(g);
		
		// draw cassy
		cassiopea.draw(g);
		barraVita.draw(g);
		for(int i = 0; i < nemici.size(); i++) {
			nemici.get(i).draw(g);
		}
		
		
	}
	
	
}












