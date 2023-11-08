package gameEngine.interfaces;

import gameEngine.GamePanel;
import java.awt.Graphics2D;

public interface DrawableEntity {

    public void update(GamePanel gamePanel);

    public void draw(Graphics2D graphics2d, GamePanel gamePanel);

}
