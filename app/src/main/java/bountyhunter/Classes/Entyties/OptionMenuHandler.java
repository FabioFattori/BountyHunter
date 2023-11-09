package bountyhunter.Classes.Entyties;

import java.awt.Graphics2D;
import javax.swing.JDialog;
import bountyhunter.GameRender;
import gameEngine.GamePanel;
import gameEngine.interfaces.DrawableEntity;

public class OptionMenuHandler implements DrawableEntity{

    private int maxWaitingTime;
    private int waitingTime;
    private boolean isOpen = false;
    JDialog optionDialog;

    public OptionMenuHandler(GameRender gameRender) {
        
        maxWaitingTime = gameRender.getFPS() / 10;
        waitingTime = maxWaitingTime;
    }

    @Override
    public void update(GamePanel gamePanel) {
        if (isOpen) {
            if (waitingTime <= 0) {
                if(gamePanel.getKeyHandler().isEscapePressed()) {
                    
                    isOpen = true;
        
                }
            }
        }

        if(waitingTime > 0) {
            waitingTime--;
        }
    }

    @Override
    public void draw(Graphics2D graphics2d, GamePanel gamePanel) {
       if (isOpen) {
            optionDialog = new JDialog(gamePanel.getFrame(), "Pause", true);
            optionDialog.setSize(200, 200);
            optionDialog.setLocationRelativeTo(null);
            optionDialog.setVisible(true);
       }
    }
    
}
