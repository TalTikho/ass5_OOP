package spriteP;
/*
 * ass 5
 * Tal Tikhonov
 * 215275512
 */
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * The spriteP.SpriteCollection class manages a collection of sprites.
 */
public class SpriteCollection {
    private List<Sprite> sprites;
    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * Add a sprite to the collection.
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * Remove sprite.
     * @param s represents the sprite
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
    /**
     * Call drawOn for all sprites in the collection.
     * @param d the DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
    /**
     * Notify all sprites that time has passed.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : new ArrayList<>(sprites)) {
            sprite.timePassed();
        }
    }
}

