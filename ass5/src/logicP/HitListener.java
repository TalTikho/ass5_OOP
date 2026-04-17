package logicP;

import spriteP.Ball;
import spriteP.Block;
/**
 * HitListener.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
// The hitter parameter is the Ball that's doing the hitting.
    /**
     * hitEvent.
     * @param beingHit block
     * @param hitter ball
     */
     void hitEvent(Block beingHit, Ball hitter);

}
