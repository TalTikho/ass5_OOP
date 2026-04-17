package logicP;

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
