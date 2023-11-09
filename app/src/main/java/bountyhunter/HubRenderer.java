package bountyhunter;

import java.awt.Graphics2D;

import bountyhunter.Classes.GraphicsClasses.MapDrawer;
import gameEngine.GamePanel;
import gameEngine.interfaces.DrawableEntity;

public class HubRenderer implements DrawableEntity{

    private final GamePanel gamePanel;
    private final MapDrawer mapDrawer;

    public HubRenderer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.mapDrawer = new MapDrawer(gamePanel,"maps/hubMap.txt");
    }


    @Override
    public void update(GamePanel gamePanel) {
       
    }

    @Override
    public void draw(Graphics2D graphics2d, GamePanel gamePanel) {
        this.mapDrawer.drawMap(graphics2d);
    }
    
}
