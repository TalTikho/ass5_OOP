package gameP;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;
import collisionP.Collidable;
import logicP.BlockRemover;
import logicP.BallRemover;
import logicP.Counter;
import logicP.ScoreIndicator;
import logicP.ScoreTrackingListener;
import logicP.PrintHitListener;
import spriteP.SpriteCollection;
import spriteP.Sprite;
import spriteP.Ball;
import spriteP.Block;
import spriteP.Paddle;
import geometryP.Point;
import geometryP.Rectangle;
import geometryP.Velocity;
/**
 * The gameP.Game class manages a game instance.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private KeyboardSensor keyboard;
    private PrintHitListener phs = new PrintHitListener();
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    /**
     * Constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("gameP.Game", 800, 600);
        this.sleeper = new Sleeper();
        this.keyboard = gui.getKeyboardSensor();
    }
    /**
     * Add a collidable object to the game.
     * @param c the collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * Add a sprite to the game.
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * Remove collidable from the game.
     * @param c represents the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * Remove Sprite.
     * @param s represents the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * Initialize the game: create the Blocks, spriteP.Ball, spriteP.Paddle and add them to the game.
     */
    public void initialize() {
        // Create and add paddle
        Paddle paddle = new Paddle(new Rectangle(new Point(350, 592), 100, 8), Color.black, keyboard, 15);
        paddle.addToGame(this);
        //this.paddle = paddle; // Assign to the field
        //addle.addToGame(this);
        //setting counters
        remainingBlocks = new Counter();
        remainingBalls = new Counter();
        score = new Counter();
        // setting removers
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);
        // Create and add balls
        Ball ball1 = new Ball(new Point(400, 300), 5, Color.RED);
        ball1.setVelocity(Velocity.fromAngleAndSpeed(45, 5));
        ball1.setGameEnvironment(this.environment);
        ball1.addToGame(this);
        remainingBalls.increase(1);
        //
        Ball ball2 = new Ball(new Point(450, 350), 5, Color.BLUE);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(-45, 5));
        ball2.setGameEnvironment(this.environment);
        ball2.addToGame(this);
        remainingBalls.increase(1);
        //
        Ball ball3 = new Ball(new Point(500, 350), 5, Color.BLACK);
        ball3.setVelocity(Velocity.fromAngleAndSpeed(-45, 5));
        ball3.setGameEnvironment(this.environment);
        ball3.addToGame(this);
        remainingBalls.increase(1);
        //
        // Create and add blocks
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK, Color.CYAN};
        //
        int rowY = 100;
        int maxBlocks = 12; // amount of blocks in row with the largest amount
        for (int i = 0; i < 6; i++, rowY += 20) {
            Color randColor = colors[i];
            for (int j = 0; j < maxBlocks + 1 - i; j++) {
                Block block = new Block(
                        new Rectangle(new Point(800 - j * 50, rowY), 50, 20), randColor);
                block.addToGame(this);
                block.addHitListener(phs);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                remainingBlocks.increase(1);
            }
        }
        //Add border blocks
        Block block1 = new Block(new Rectangle(new Point(0, 0), 800, 0), Color.WHITE);
        Block block2 = new Block(new Rectangle(new Point(0, 0), 0, 600), Color.WHITE);
        Block block3 = new Block(new Rectangle(new Point(0, 600), 800, 0), Color.WHITE);
        Block block4 = new Block(new Rectangle(new Point(800, 0), 0, 600), Color.WHITE);
        block1.addToGame(this);
        block2.addToGame(this);
        block3.addToGame(this);
        block4.addToGame(this);
        block3.addHitListener(ballRemover);
        /*
        * space
        */
        // Add the death-region block and register ballRemover
        //Block deathRegion = new Block(new Rectangle(new Point(0, 600), 800, 0), Color.WHITE);
        //deathRegion.addHitListener(ballRemover);
        //addCollidable(deathRegion);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (remainingBlocks.getValue() > 0 && remainingBalls.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            //that sets the color
//            d.setColor(Color.CYAN);
//            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        score.increase(100);
        gui.close();
    }
}

