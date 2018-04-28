package application.model;

import application.Main;

/**
 * EnemyHandler class controls and updates the Enemy Ship thread
 * 
 * @author wolfyCSA, caseycannon423, IceKold736, Mpoznecki, indomichael
 */


public class EnemyHandler implements Runnable {
	public Thread enemyHandler;
	public boolean running = false;
	
	public EnemyHandler() {}
	
	//THREAD STARTS IN MAINMENUCONTROLLER
	public synchronized void start(){
		if (running)
			return;
		running = true;
		enemyHandler = new Thread(this);
		enemyHandler.setDaemon(true);
		enemyHandler.start();
	}
	
	public synchronized void stop(){
		if (!running)
			return;
		running = false;
		try {
			enemyHandler.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * runs the Enemy ship Thread
	 */
	public void run() {
		
		while (running)
		{
			for (EnemyShip e : Main.enemies.toArray(new EnemyShip[Main.playerBullets.size()])) {
				e.update1();
				//System.out.println("move");
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (EnemyShip e : Main.enemies.toArray(new EnemyShip[Main.playerBullets.size()])) {
				e.update();
				//System.out.println("move");
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		stop();
	}
}
