public class Enemy{
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
        
    }
}