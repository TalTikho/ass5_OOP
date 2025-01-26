package collisionP;
/*
 * ass 5
 * Tal Tikhonov
 * 215275512
 */
import geometryP.Point;
import geometryP.Rectangle;
import geometryP.Velocity;
import spriteP.Ball;
/**
 * The collisionP.Collidable interface represents objects that can be collided with.
 */
public interface Collidable {
    /**
     * Get the collision rectangle.
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that it has been hit.
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity after the hit
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
    /**
     * Notify the object that it has been hit.
     * @param hitter the ball
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

