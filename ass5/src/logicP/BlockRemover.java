package logicP;
/*
 * ass 5
 * 215275512
 * Tal Tikhonov
 */
import gameP.Game;
import spriteP.Ball;
import spriteP.Block;
/**
 * Block Remover.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;
    /**
     * Constructor.
     * @param game game
     * @param remainingBlocks the remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Hit.
     * @param beingHit block
     * @param hitter ball
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.ballColorMatch(hitter)) {
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
            hitter.setColor(beingHit.getColor());
            remainingBlocks.decrease(1);
        }
    }
}
