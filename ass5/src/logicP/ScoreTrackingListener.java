package logicP;

import spriteP.Ball;
import spriteP.Block;
/**
 * Score tracking.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * Constructor.
     * @param scoreCounter counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Increasing the score.
     * @param beingHit block
     * @param hitter ball
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
