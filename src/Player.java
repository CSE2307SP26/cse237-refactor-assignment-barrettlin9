public class Player {
    double playerX;
	double playerY;
	double playerSpeed;

    public Player (double playerX, double playerY, double playerSpeed) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.playerSpeed = playerSpeed;
    }

    public void updatePlayerPosition() {
        if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
            playerX = playerX - playerSpeed;
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
            playerX = playerX + playerSpeed;
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
            playerY = playerY - playerSpeed;
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
            playerY = playerY + playerSpeed;
        }
    }

    public void resetPlayerPosition() {
        playerX = 0.5;
        playerY = 0.5;
    }

}
