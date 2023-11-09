package bountyhunter;

import java.awt.Graphics2D;
import gameEngine.classes.MapDrawer;
import gameEngine.GamePanel;
import gameEngine.interfaces.DrawableEntity;

public class HubRenderer implements DrawableEntity{

    private final GamePanel gamePanel;

    public HubRenderer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    @Override
    public void update(GamePanel gamePanel) {
       
    }

    @Override
    public void draw(Graphics2D graphics2d, GamePanel gamePanel) {
        
    }
    
}
