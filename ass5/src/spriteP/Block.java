package spriteP;
/*
 * ass 5
 * Tal Tikhonov
 * 215275512
 */
import biuoop.DrawSurface;
import collisionP.Collidable;
import gameP.Game;
import geometryP.Point;
import geometryP.Rectangle;
import geometryP.Velocity;
import logicP.HitListener;
import logicP.HitNotifier;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a block in the game.
 */
public class Block implements Sprite, Collidable, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<>();
    /**
     * Constructor.
     *
     * @param rectangle the rectangle representing the block
     * @param color the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }
    /**
     * Constructor.
     * @param hitListeners hit
     * @param rectangle the rectangle representing the block
     * @param color the color of the block
     */
    public Block(Rectangle rectangle, Color color, List<HitListener> hitListeners) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = hitListeners;
    }
    /**
     * Get Color.
     * @return color
     */
    public Color getColor() {
        return color;
    }
    /**
     * Getting the rectangle.
     * @return the rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * Hit.
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity
     * @return the ew velocity
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if ((rectangle.getTopLine().onLine(collisionPoint)) || (rectangle.getBottomLine().onLine(collisionPoint))) {
            dy *= -1;
        }

        if ((rectangle.getRightLine().onLine(collisionPoint)) || (rectangle.getLeftLine().onLine(collisionPoint))) {
            dx *= -1;
        }

        return new Velocity(dx, dy);
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
        if (!ballColorMatch(hitter)) {
            notifyHit(hitter);
            //hitter.setColor(this.color);
            //return currentVelocity;
        }
        //updating using the hit method above
        return hit(collisionPoint, currentVelocity);
    }
    /**
     * Draw.
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
     * Time.
     */
    @Override
    public void timePassed() {
        // Blocks do not move, so this is empty
    }
    /**
     * add.
     * @param hl the hitlistener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
     /**
     * remove.
     * @param hl the hitlistener
     */
     @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * Notifying.
     * @param hitter ball
     */
    public void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * Color matching.
     * @param ball represents the ball
     * @return T/F if matched color
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }
    /**
     * Removing from the game.
     * @param game game
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * Adding to the game.
     * @param game the game
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
    //changed it here so it will stop being angry
//    @Override
//    public void hitEvent(Block beingHit, Ball hitter) {
//        return;
//    }
}
