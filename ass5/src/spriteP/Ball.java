package spriteP;
/*
 * ass 5
 * Tal Tikhonov
 * 215275512
 */
import biuoop.DrawSurface;
import collisionP.CollisionInfo;
import gameP.Game;
import gameP.GameEnvironment;
import geometryP.Line;
import geometryP.Point;
import geometryP.Velocity;
import java.awt.Color;
/**
 * Represents a ball in the game.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    /**
     * Constructor.
     * @param center the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }
    /**
     * Returns color.
     * @return color
     */
    public Color getColor() {
        return color;
    }
    /**
     * Set color.
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Set the velocity of the ball.
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * Set the velocity of the ball.
     * @param dx the change in x
     * @param dy the change in y
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**
     * Set the game environment.
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }
    /**
     * Calculate the next position of the ball based on its current velocity.
     * @return the next position of the ball
     */
    private Point nextPosition() {
        double dx = velocity.getDx();
        double dy = velocity.getDy();
        double newX = center.getX() + dx;
        double newY = center.getY() + dy;
        return new Point(newX, newY);
    }
    /**
     * Move the ball one step, checking for collisions.
     */
    public void moveOneStep() {
        Line trajectory = new Line(center, nextPosition());
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            center = nextPosition();
        } else {
            double collisionX = collisionInfo.collisionPoint().getX();
            double collisionY = collisionInfo.collisionPoint().getY();
            double dx = velocity.getDx();
            double dy = velocity.getDy();
            center = new Point(collisionX - dx, collisionY - dy);
            velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity);
        }
    }
    /**
     * Draw.
     * @param surface the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }
    /**
     * Time.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**
     * Adding to the game.
     * @param game adding this
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
    /**
     * Removing the ball.
     * @param game game
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}

