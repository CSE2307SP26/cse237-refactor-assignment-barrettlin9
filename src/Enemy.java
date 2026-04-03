public class Enemy{
    //issue with the double list created from game, don't have time to figure out
    //maybe needs to still be in game class but I don't have time
    private double x;
    private double y;
    private double xVelocity;
    private double yVelocity;
    private double upperVelocity;
    private double lowerVelocity;

    public Enemy(double x, double y, double xVelocity, double yVelocity) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public void spawnEnemy(){
        x = Math.random();
        y = Math.random();
        xVelocity = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
        yVelocity = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
    }

    public void updateEnemyPosition(){
        x = x + xVelocity;
        y = y + yVelocity;

        if(x > 1 || x < 0) {
            xVelocity = -xVelocity;
        }
        if(y > 1 || y < 0) {
            yVelocity = -yVelocity;
        }
    }

    public void resetEnemyPosition(){
        //didn't have time to fill out
    }
}