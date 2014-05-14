package GameState;

import java.awt.event.KeyEvent;

import Entity.Cassiopea;
import Main.GamePanel;

public abstract class GameState {
	
	
	Cassiopea cassiopea;
	protected GameStateManager gsm;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) cassiopea.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) cassiopea.setRight(true);
		if(k == KeyEvent.VK_UP) cassiopea.setUp(true);
		if(k == KeyEvent.VK_DOWN) cassiopea.setDown(true);
		if(k == KeyEvent.VK_W) cassiopea.setJumping(true);
		if(k == KeyEvent.VK_E) cassiopea.setGliding(true);
		if(k == KeyEvent.VK_D) cassiopea.setFire(true);
		if(k == KeyEvent.VK_P){
			GamePanel.pausa=!GamePanel.pausa;
//			GameStateManager.pause=!GameStateManager.pause;
//			GameStateManager.pauseLock.lock();
//			GameStateManager.pauseCondition.signalAll();
//			GameStateManager.pauseLock.unlock();
		}
		
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) cassiopea.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) cassiopea.setRight(false);
		if(k == KeyEvent.VK_UP) cassiopea.setUp(false);
		if(k == KeyEvent.VK_DOWN) cassiopea.setDown(false);
		if(k == KeyEvent.VK_W) cassiopea.setJumping(false);
		if(k == KeyEvent.VK_E) cassiopea.setGliding(false);
		if(k == KeyEvent.VK_D) cassiopea.setFire(false);
	}
	
}
