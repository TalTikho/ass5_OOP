package logicP;
/*
* ass 5
* 215275512
* Tal Tikhonov
*/
/**
 * HitNotifier.
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.
    /**
     * Add.
     * @param hl hit listener
     */
     void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.
    /**
     * Remove.
     * @param hl hit listener
     */
     void removeHitListener(HitListener hl);
}
