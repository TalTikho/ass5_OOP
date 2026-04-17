package logicP;

import gameP.Game;
import spriteP.Ball;
import spriteP.Block;
/**
 * Ball remover.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;
    /**
     * Constructor.
     * @param game game
     * @param remainingBalls the remaining balls
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Hit.
     * @param beingHit block
     * @param hitter ball
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
