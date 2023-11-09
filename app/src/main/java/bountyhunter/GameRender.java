package bountyhunter;

import bountyhunter.Classes.Entyties.*;
import gameEngine.classes.MapDrawer;
import gameEngine.classes.OptionMenuHandler;
import bountyhunter.Classes.GraphicsClasses.MenuDrawer;
import bountyhunter.Classes.WeaponsClasses.*;
import gameEngine.GamePanel;
import gameEngine.interfaces.DrawableEntity;
import java.awt.Graphics2D;

public class GameRender implements DrawableEntity {

    private GamePanel gamePanel;

    private Bullet b;
    private Player player;
    private int frameCount = 0;

    private boolean changeControll = true;
    private MapDrawer mapDrawer;
    private InventoryHandler InventoryHandler;
    private final HubRenderer hubRenderer;
    private boolean isHub = false;

    public GameRender(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        initialize();
        this.hubRenderer = new HubRenderer(gamePanel);
    }

    public void initialize() {
        this.player = new Player(gamePanel, new HeavySword());
        InventoryHandler = new InventoryHandler(player.getInventory(), this);
        mapDrawer = new MapDrawer(gamePanel.getAssetProvider());
        mapDrawer.loadMap("maps/map.txt");
        frameCount = 0;
    }

    public void update(GamePanel gamePanel) {
        frameCount++;

        if (!this.InventoryHandler.isIsOpen()) {
            player.update(gamePanel);
        }

        if (frameCount % gamePanel.getFPS() == 1) {
            changeControll = true;
        }

        if (frameCount == gamePanel.getFPS()) {
            frameCount = 0;
        }

        InventoryHandler.update(gamePanel);

        

        if(isHub) {
            hubRenderer.update(gamePanel);
        }

    }

    public void draw(Graphics2D g2d, GamePanel gamePanel) {

        if(isHub) {
            hubRenderer.draw(g2d, gamePanel);
            return;
        }
        mapDrawer.draw(g2d, gamePanel);

        MenuDrawer.drawTopLeftMenu(g2d, player);

        player.draw(g2d, gamePanel);

        InventoryHandler.draw(g2d, gamePanel);

        g2d.dispose();
    }

    /* get & set section */

    public int getFPS() {
        return this.gamePanel.getFPS();
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

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public void setB(Bullet b) {
        this.b = b;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setInventoryHandler(InventoryHandler InventoryHandler) {
        this.InventoryHandler = InventoryHandler;
    }

    public Bullet getB() {
        return b;
    }

    public Player getPlayer() {
        return player;
    }

    public InventoryHandler getInventoryHandler() {
        return InventoryHandler;
    }
    /* end get & set section */

}
