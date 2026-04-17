package logicP;

import spriteP.Ball;
import spriteP.Block;
/**
 * Print hits.
 */
public class PrintHitListener implements HitListener {
    /**
     * Hit.
     * @param beingHit block
     * @param hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
