package spriteP;
/*
 * ass 3
 * Tal Tikhonov
 * 215275512
 */
import biuoop.DrawSurface;
/**
 * The spriteP.Sprite interface represents game objects that can be drawn to the screen
 * and can change over time.
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     * @param d the DrawSurface
     */
    void drawOn(DrawSurface d);
    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}

