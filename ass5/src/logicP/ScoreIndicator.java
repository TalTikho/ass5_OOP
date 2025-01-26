package logicP;
/*
* ass 5
* 215275512
* Tal Tikhonov
*/
import spriteP.Sprite;
import biuoop.DrawSurface;
import gameP.Game;
import java.awt.Color;
/**
 * Sore indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    /**
     * Constructor.
     * @param score score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    /**
     * Draw.
     * @param d the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(100, 20, "Score: " + score.getValue(), 15);
    }
    /**
     * Time.
     */
    @Override
    public void timePassed() {
        // No need to implement
    }
    /**
     * Adding the score.
     * @param game game
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
