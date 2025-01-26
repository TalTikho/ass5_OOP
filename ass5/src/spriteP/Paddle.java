package spriteP;
/*
 * ass 5
 * Tal Tikhonov
 * 215275512
 */
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisionP.Collidable;
import gameP.Game;
import geometryP.Point;
import geometryP.Rectangle;
import geometryP.Velocity;
import java.awt.Color;
/**
 * The spriteP.Paddle class represents the player's paddle in the game.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rectangle;
    private Color color;
    private KeyboardSensor keyboard;
    private double speed;
    /**
     * Constructor for spriteP.Paddle.
     *
     * @param rectangle the rectangle representing the paddle
     * @param color the color of the paddle
     * @param keyboard the keyboard sensor
     * @param speed the speed of the paddle
     */
    public Paddle(Rectangle rectangle, Color color, KeyboardSensor keyboard, double speed) {
        this.rectangle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
        this.speed = speed;
    }
    /**
     * Movement - Left .
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() <= 0) {
            this.rectangle = new Rectangle(new Point(800 - this.rectangle.getWidth(),
                    this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        } else {
            this.rectangle = new Rectangle(new Point(this.rectangle.getUpperLeft().getX()
                    - speed, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }
    /**
     * Movement - Right .
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() >= 800) {
            this.rectangle = new Rectangle(new Point(0, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        } else {
            this.rectangle = new Rectangle(new Point(this.rectangle.getUpperLeft().getX()
                    + speed, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }
    /**
     * Getting collision.
     * @return the rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * Checking hit.
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity
     * @return  the new velocity
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double speed = currentVelocity.getSpeed();

        int region = (int) ((collisionPoint.getX() - this.rectangle.getUpperLeft().getX())
                / (this.rectangle.getWidth() / 5)) + 1;

        switch (region) {
            case 1:
                return Velocity.fromAngleAndSpeed(300, speed);
            case 2:
                return Velocity.fromAngleAndSpeed(330, speed);
            case 4:
                return Velocity.fromAngleAndSpeed(30, speed);
            case 5:
                return Velocity.fromAngleAndSpeed(60, speed);
            default:
                return new Velocity(dx, -dy);
        }
    }
    /**
     * Hit.
     * @param hitter the ball
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        return hit(collisionPoint, currentVelocity);
    }
    /**
     * Drawing.
     * @param surface the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        int x = (int) rectangle.getUpperLeft().getX();
        int y = (int) rectangle.getUpperLeft().getY();
        int width = (int) rectangle.getWidth();
        int height = (int) rectangle.getHeight();

        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, width, height);
        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);
    }
    /**
     * Time passes.
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    /**
     * Adding to game.
     * @param game Game
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
}

