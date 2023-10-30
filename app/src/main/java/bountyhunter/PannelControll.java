package bountyhunter;

import bountyhunter.Classes.Entyties.*;
import bountyhunter.Classes.GraphicsClasses.MapDrawer;
import bountyhunter.Classes.GraphicsClasses.MenuDrawer;
import bountyhunter.Classes.WeaponsClasses.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class PannelControll extends JPanel implements Runnable {
    /* window of The Game */
    private final JFrame frame;
    private EnviromentVariables ev = new EnviromentVariables();
    private Bullet b;
    private Player player = new Player(ev.getTileSize(), new HeavySword());
    private Thread gameThread;
    private Graphics2D g2d;
    private int fps = 60;
    private int frameCount = 0;
    private boolean changeControll = true;
    private MapDrawer mapDrawer;
    private InventoryHandler InventoryHandler = new InventoryHandler(player.getInventory(), this);

    // ChangeWeapongArea changeWeaponArea = new ChangeWeapongArea(100,100,100,100);

    public KeyHandler keyHandler = new KeyHandler();

    public PannelControll(final JFrame frame) {
        this.frame = frame;

        this.setFocusable(true);
        /* load the map */ 
        try {
            mapDrawer = new MapDrawer(ev.getPathToAsset()+"maps/map.txt",ev.getTileSize());
        } catch (Exception e) {
            System.out.println(e);
        }
        
        ev.setMaxTileX(mapDrawer.getMap()[0].length);
        ev.setMaxTileY(mapDrawer.getMap().length);
        
        this.setPreferredSize(new Dimension(ev.getScreenSizeX(), ev.getScreenSizeY()));
        this.setSize(ev.getScreenSizeX(), ev.getScreenSizeY());
        this.setBackground(Color.black);
        this.addKeyListener(keyHandler);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        

        while (this.gameThread != null) {

            if (player.getHealth() <= 0) {
                this.gameThread = null;
            }

            update();
            repaint();

            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MenuDrawer.drawGameOver(this.g2d);
    }

    public void update() {
        frameCount++;

        if (!this.InventoryHandler.isIsOpen()) {
            player.update(keyHandler, ev.getScreenSizeX(), ev.getScreenSizeY());
        }

        if (frameCount % fps == 1) {
            changeControll = true;

        }

        InventoryHandler.update();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.g2d = (Graphics2D) g;

        mapDrawer.drawMap(g2d);

        MenuDrawer.drawTopLeftMenu(g2d, player);

        player.redraw(g2d, keyHandler.direction);

        InventoryHandler.draw(g2d);

        g2d.dispose();
    }

    /* get & set section */

    public int getFps() {
        return fps;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public boolean isChangeControll() {
        return changeControll;
    }

    public void setChangeControll(boolean changeControll) {
        this.changeControll = changeControll;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public void setB(Bullet b) {
        this.b = b;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    public void setInventoryHandler(InventoryHandler InventoryHandler) {
        this.InventoryHandler = InventoryHandler;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    public Bullet getB() {
        return b;
    }

    public Player getPlayer() {
        return player;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public InventoryHandler getInventoryHandler() {
        return InventoryHandler;
    }

    /* end get & set section */

}
