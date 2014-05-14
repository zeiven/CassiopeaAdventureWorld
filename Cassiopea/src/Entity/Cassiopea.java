package Entity;

import Entity.Enemies.Onde;
import TileMap.*;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Cassiopea extends MapObject {
	
	//CASSY
	public int lives=5;
	public int health;
	private int maxHealth;
	int highscore;
	// VEDIAMO SE CASSYMINCHIA VOLA
	private boolean gliding;
	
	
	private boolean scratching;
	private int scratchDamage;
	private int scratchRange;
	private boolean flinching;
	private long flinchTimer;
	private boolean fire=false;
	
//	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
		2, 8, 1, 2, 4, 2, 5
	};
//	
//	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int GLIDING = 4;
	private static final int FIREBALL = 5;
	private static final int SCRATCHING = 6;
//	
	
	
	public Cassiopea(mattonciniMap tm) {
		
		super(tm);
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		facingRight = true;
		
		health = maxHealth = 5;
		
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Player/cassiopea2.gif"
				)
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			
			for(int i = 0; i < 5; i++) {
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i != SCRATCHING) {
						bi[j] = spritesheet.getSubimage(
								j * width,
								i * height,
								width,
								height
						);
					}
					else {
						bi[j] = spritesheet.getSubimage(
								j * width * 2,
								i * height,
								width * 2,
								height
						);
					}
					
				}
				
				sprites.add(bi);
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		
		
	}
	
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
	public void setGliding(boolean b) { 
		gliding = b;
	}
	
	public void checkAttack(ArrayList<Nemico> nemici) {
		
		// loop through enemies
		for(int i = 0; i < nemici.size(); i++) {
			
			Nemico e = nemici.get(i);
			
			// scratch attack
			if(scratching) {
				if(facingRight) {
					if(
						e.getx() > x &&
						e.getx() < x + scratchRange && 
						e.gety() > y - height / 2 &&
						e.gety() < y + height / 2
					) {
						e.hit(scratchDamage);
					}
				}
				else {
					if(
						e.getx() < x &&
						e.getx() > x - scratchRange &&
						e.gety() > y - height / 2 &&
						e.gety() < y + height / 2
					) {
						e.hit(scratchDamage);
					}
				}
			}
	
			

			
			// check enemy collision
			if(!(e instanceof Fitoplancton)){
			if(intersects(e)) {
				hit(e.getDamage());}
			}
			if(e instanceof Fitoplancton){
				if(intersects(e)) {
					e.setPosition(-1, -1);
					e.setContaCibo();
				}
				if(e.getContaCibo()==10 && health<maxHealth){
					health++;
					((Fitoplancton) e).ResetContaCibo();
					}
			}
//			
			if(e instanceof Onde){
				if(intersects(e)) {
					health=0;
					System.out.println("intersezioni onde ");
					
				}
			}	
		}
		
	}
	
	
	public void hit(int damage) {

		if(flinching) return;
		health -= damage;
		if(health < 0) health = 0;
//		if(health == 0) dead = true;
		flinching = true;
		flinchTimer = System.nanoTime();
	}
	
	private void getNextPosition() {
		
		// movement
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		
		// jumping
		if(jumping && !falling) {
	
			dy = jumpStart;
			falling = true;
		}
		
		// falling
		if(falling) {
			
			if(dy > 0 && gliding) dy += fallSpeed * 0.1;
			else dy += fallSpeed;
			
			if(dy > 0) jumping = false;
			if(dy < 0 && !jumping) dy += stopJumpSpeed;
			
			if(dy > maxFallSpeed) dy = maxFallSpeed;
			
		}
		
	}
	
	public void update() {
		
		// update position
		getNextPosition();
		checkmattonciniMapCollision();
		setPosition(xtemp, ytemp);
		
	
		/*else*/ if(dy > 0) {
			if(fire){
				//spara
				currentAction=FIREBALL;
				animation.setFrames(sprites.get(FIREBALL));
				animation.setDelay(100);
				width=30;
			}
			if(gliding) {
				if(currentAction != GLIDING) {
					currentAction = GLIDING;
					animation.setFrames(sprites.get(GLIDING));
					animation.setDelay(100);
					width = 30;
				}
			}
			else if(currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width = 30;
			}
		}
		else if(dy < 0) {
			if(fire){
				//spara
				currentAction=FIREBALL;
				animation.setFrames(sprites.get(FIREBALL));
				animation.setDelay(100);
				width=30;
			}
			if(currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 30;
			}
		}
		else if(left || right) {
			if(fire){
				//spara
				currentAction=FIREBALL;
				animation.setFrames(sprites.get(FIREBALL));
				animation.setDelay(100);
				width=30;
			}
			if(currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
				width = 30;
			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
			if(fire){
				//spara
				currentAction=FIREBALL;
				animation.setFrames(sprites.get(FIREBALL));
				animation.setDelay(100);
				width=30;
			}
		}
		
		animation.update();
		
		// set direction
		if(currentAction != SCRATCHING && currentAction != FIREBALL) {
			if(right) facingRight = true;
			if(left) facingRight = false;
		}
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 1000) {
				flinching = false;
			}
		}
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed / 100 % 2 == 0) {
				return;
			}
		}
		
		super.draw(g);
		
	}


}

















