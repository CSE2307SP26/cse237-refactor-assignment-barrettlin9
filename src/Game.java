import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {

	public static void main(String[] args) {
		double lowerVelocity = 0.005;
		double upperVelocity = 0.01;
		int ballCount = 3;
		double radius = 0.025;
		int score = 0;
		int highScore = 0;
		double playerX = 0.5;
		double playerY = 0.5;
		double playerSpeed = 0.01;
		double[] enemyX = new double[ballCount];
		double[] enemyY = new double[ballCount];
		double[] enemyXVelocity = new double[ballCount];
		double[] enemyYVelocity = new double[ballCount];
		

		// Initialize ball positions and velocities
		for(int i = 0; i < ballCount; i++) {
			enemyX[i] = Math.random();
			enemyY[i] = Math.random();
			enemyXVelocity[i] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
			enemyYVelocity[i] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
		}
		
		StdDraw.enableDoubleBuffering();
		
		long st = System.currentTimeMillis();
		long dt = System.currentTimeMillis();
		
		// Game loop MASSIVE
		while (true) {
			StdDraw.clear();
			boolean collision = false;
			for(int i = 0; i < ballCount; i++) {
				
				// Update enemy position
				enemyX[i] = enemyX[i] + enemyXVelocity[i];
				enemyY[i] = enemyY[i] + enemyYVelocity[i];
				if(enemyX[i] + radius > 1 || enemyX[i] - radius < 0) { 
					enemyXVelocity[i] = -enemyXVelocity[i];
				}
				if(enemyY[i] + radius > 1 || enemyY[i] - radius < 0) { 
					enemyYVelocity[i] = -enemyYVelocity[i];
				}
				for(int j = 0; j < ballCount; j++) {
					if(i != j) {
						double d = Math.sqrt(Math.pow(enemyX[i] - enemyX[j], 2) + Math.pow(enemyY[i] - enemyY[j], 2));
						if(d < 2 * radius) {
							enemyXVelocity[i] = -enemyXVelocity[i];
							enemyYVelocity[i] = -enemyYVelocity[i];
						}
					}
				}

				// Check for collision with player
				double d = Math.sqrt(Math.pow(enemyX[i] - playerX, 2) + Math.pow(enemyY[i] - playerY, 2));
				if(d < 2 * radius) {
					collision = true;
				}
			}
			
			// If collision with player, reset game
			if(collision) {
				enemyX = new double[3];
				enemyY = new double[3];
				enemyXVelocity = new double[3];
				enemyYVelocity = new double[3];
				for(int i = 0; i < ballCount; i++) {
					enemyX[i] = Math.random();
					enemyY[i] = Math.random();
					enemyXVelocity[i] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
					enemyYVelocity[i] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
					score = 0;
					st = System.currentTimeMillis();
					dt = System.currentTimeMillis();
					playerX = 0.5;
					playerY = 0.5;
				}
			}
			
			// Handle player movement
			if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
				playerY = playerY + playerSpeed;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
				playerY = playerY - playerSpeed;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
				playerX = playerX - playerSpeed;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
				playerX = playerX + playerSpeed;
			}
			
			// Keep player within bounds
			if(playerX > 1) {
				playerX = 1;
			}
			if(playerX < 0) {
				playerX = 0;
			}
			if(playerY > 1) {
				playerY = 1;
			}
			if(playerY < 0) {
				playerY = 0;
			}
			
			// Update score 
			long now = System.currentTimeMillis();
			if(now > st + 1000) {
				score++;
				if(score > highScore) {
					highScore = score;
				}
				st = now;
			}
			
			// Add new ball every 10 seconds
			if(now > dt + 10000) {
				ballCount++;
				double[] enemyXnew = new double[ballCount];
				double[] enemyYnew = new double[ballCount];
				double[] enemyXVnew = new double[ballCount];
				double[] enemyYVnew = new double[ballCount];
				for(int i = 0; i < ballCount - 1; i++) {
					enemyXnew[i] = enemyX[i];
					enemyYnew[i] = enemyY[i];
					enemyXVnew[i] = enemyXVelocity[i];
					enemyYVnew[i] = enemyYVelocity[i];
				}
				enemyXnew[ballCount-1] = Math.random();
				enemyYnew[ballCount-1] = Math.random();
				enemyXVnew[ballCount-1] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
				enemyYVnew[ballCount-1] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
				enemyX = enemyXnew;
				enemyY = enemyYnew;
				enemyXVelocity = enemyXVnew;
				enemyYVelocity = enemyYVnew;
				dt = now;
			}

			// Draw balls
			StdDraw.setPenColor(Color.red);
			for(int i = 0; i < ballCount; i++) {
				StdDraw.filledCircle(enemyX[i], enemyY[i], radius);
			}
			
			// Draw player and score
			StdDraw.setPenColor(Color.black);
			StdDraw.filledCircle(playerX, playerY, radius);
			StdDraw.text(0.5, 0.1, "Score: " + score + " High Score: " + highScore);
			
			StdDraw.show();
			StdDraw.pause(10);
			
		}
	}


//I do not have time to refactor the code and I left it above so I know but these are
//the methods I would have created to avoid everything being in main
//A fourth class could be score as that is sometimes separate as well but 
//obviously I barely was able to do anything. I also don't know if draw 
//should be in the enemy/player class or in the game class for each object

// the main method would look something like
//public static void main(String[] args) {
//      start();
//	  	while(true) {
//			update();
//		}
// 		restartGame();

	public static void start(){
		Player player = new Player(0.5, 0.5, 0.01);
		Enemy enemy = new Enemy(0.5, 0.5, 0.01, 0.01);
		for (int i = 0; i < ballCount; i++) {
			enemy.spawnEnemy();
		}
	}

	public static void update(){
		checkCollision(player, enemy);
		updateScore();
		draw();
	}

	public static void checkWallCollision(Player player, Enemy enemy){
		if(player.playerX > 1) {
			player.playerX = 1;
		}
		if(player.playerX < 0) {
			player.playerX = 0;
		}
		if(player.playerY > 1) {
			player.playerY = 1;
		}
		if(player.playerY < 0) {
			player.playerY = 0;
		}
	}

	public static boolean checkEnemyCollision(Player player, Enemy enemy){
		//issue with private need getter 
		double d = Math.sqrt(Math.pow(enemy.x - player.playerX, 2) + Math.pow(enemy.y - player.playerY, 2));
		if(d < 2 * radius) {
			return true;
		}
		return false;
	}

	public static void restartGame(){
		enemy.resetEnemyPosition();
		player.resetPlayerPosition();
	}

	public static void updateScore(){
		long now = System.currentTimeMillis();
			if(now > st + 1000) {
				score++;
				if(score > highScore) {
					highScore = score;
				}
				st = now;
			}
	}

	public static void draw(){

	}
}
