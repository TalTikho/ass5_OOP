package logicP;
/*
 * ass 5
 * 215275512
 * Tal Tikhonov
 */
/**
 * Counter.
 */
public class Counter {
    private int count;
    /**
     * Increase.
     * @param number number
     */
    public void increase(int number) {
        this.count += number;
    }
    /**
     * Decrease.
     * @param number number
     */
    public void decrease(int number) {
        this.count -= number;
    }
    /**
     * Get the value.
     * @return value
     */
    public int getValue() {
        return this.count;
    }
}
