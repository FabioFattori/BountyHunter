package gameEngine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import gameEngine.classes.AssetProvider;
import gameEngine.classes.GameEntity;
import gameEngine.classes.GamePresentation;
import gameEngine.classes.TileManager;
import gameEngine.interfaces.DrawableEntity;
import gameEngine.classes.OptionMenuHandler;

public class GamePanel
        extends JPanel
        implements Runnable {

    private KeyHandler keyHandler = new KeyHandler();
    private List<DrawableEntity> gameEntities = new ArrayList<DrawableEntity>();
    private GameConfiguration gameConfiguration;
    private GamePresentation gamePresentation;
    private AssetProvider assetProvider;
    private TileManager tileManager;
    private final JFrame owner;
    private boolean pause;
    private OptionMenuHandler OptionMenuHandler;

    Thread gameThread;

    public GamePanel(GameConfiguration gameConfiguration, ArrayList<DrawableEntity> drawableEntities,
            Color background,JFrame frame) {
        this.owner = frame;
        this.gameConfiguration = gameConfiguration;
        this.gameEntities = drawableEntities;
        this.assetProvider = new AssetProvider(gameConfiguration.getAssetPath());
        this.tileManager = new TileManager(assetProvider);
        setPreferredSize(new Dimension(gameConfiguration.getScreenSizeX(), gameConfiguration.getScreenSizeY()));
        setBackground(background);
        setDoubleBuffered(true);
        addKeyListener(this.keyHandler);
        setFocusable(true);
        
        
    }

    public void setOptionMenuHandler(OptionMenuHandler OptionMenuHandler) {
        this.OptionMenuHandler = OptionMenuHandler;
    }

    public OptionMenuHandler getOptionMenuHandler() {
        return this.OptionMenuHandler;
    }

    public TileManager getTileManager() {
        return this.tileManager;
    }

    public void setGamePresentation(GamePresentation gamePresentation) {
        this.gamePresentation = gamePresentation;
    }

    public void setupGame() {
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    public void run() {

        double drawInterval = (1000000000 / getConfiguration().getFPS());
        double delta = 0;
        long lastTime = System.nanoTime();

        long timer = 0;
        int drawCount = 0;

        while (this.gameThread != null) {

            long currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {

                System.out.println(drawCount + " FPS");
                // if (drawCount < getConfiguration().getFPS()) {
                // System.out.println("FPS dropped below " + getConfiguration().getFPS() +
                // "FPS=" + drawCount);
                // } else {
                // System.out.println(drawCount + " FPS");
                // }

                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (pause) {
            OptionMenuHandler.update(this);
            return;
        }
            
        OptionMenuHandler.update(this);
        
        if (!gamePresentation.getShow()) {
            for (DrawableEntity gameEntity : getGameEntities()) {
                gameEntity.update(this);
            }
        } else {
            gamePresentation.update(this);
        }
    }

    public void paintComponent(Graphics g) {
        if(pause){
            OptionMenuHandler.draw((Graphics2D)g, this);
            return;
        }

        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;

        if (!gamePresentation.getShow()) {
            for (DrawableEntity gameEntity : getGameEntities()) {
                gameEntity.draw(g2, this);
            }
        } else {
            gamePresentation.draw(g2, this);
        }

        g2.dispose();
    }

    public GameConfiguration getConfiguration() {
        return this.gameConfiguration;
    }

    public KeyHandler getKeyHandler() {
        return this.keyHandler;
    }

    public List<DrawableEntity> getGameEntities() {
        return this.gameEntities;
    }

    public void addGameEntity(GameEntity drawableEntity) {
        this.gameEntities.add(drawableEntity);
    }

    public void addGameEntities(DrawableEntity drawableEntities) {
        this.gameEntities.add(drawableEntities);
    }

    public AssetProvider getAssetProvider() {
        return this.assetProvider;
    }

    public void pause() {
        this.pause = true;
    }

    public void resume() {
        this.pause = false;
    }

    // UTIL method
    public int getFPS() {
        return this.getConfiguration().getFPS();
    }

    public JFrame getFrame() {
        return this.owner;
    }
}