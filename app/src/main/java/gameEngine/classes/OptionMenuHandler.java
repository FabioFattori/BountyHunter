package gameEngine.classes;

import java.awt.Graphics2D;

import javax.swing.JDialog;
import bountyhunter.GameRender;
import bountyhunter.Classes.GraphicsClasses.MenuDrawer;
import gameEngine.GamePanel;
import gameEngine.interfaces.DrawableEntity;

public class OptionMenuHandler implements DrawableEntity {

    private int maxWaitingTime;
    private int waitingTimeToOpenMenu;
    private int waitingTimeToMoveSelection;
    private boolean isOpen = false;
    JDialog optionDialog;
    boolean escapePressed = false;
    private int indexSelection = 0;

    public OptionMenuHandler(GameRender gameRender) {

        maxWaitingTime = gameRender.getFPS() / 10;
        waitingTimeToOpenMenu = maxWaitingTime;
        waitingTimeToMoveSelection = maxWaitingTime;
    }

    @Override
    public void update(GamePanel gamePanel) {
        // isOpen = false;

        if (waitingTimeToOpenMenu <= 0 && gamePanel.getKeyHandler().isEscapePressed()) {

            if (escapePressed) {
                isOpen = false;
                gamePanel.resume();
            } else {
                isOpen = true;
            }

            escapePressed = !escapePressed;

        } else if (waitingTimeToOpenMenu <= 0 && escapePressed) {
            isOpen = true;
            waitingTimeToOpenMenu = maxWaitingTime;
            escapePressed = false;
            gamePanel.pause();
        }

        if (isOpen && waitingTimeToMoveSelection <= 0) {
            if (gamePanel.getKeyHandler().isUpPressed() && indexSelection > 0) {
                indexSelection--;
                waitingTimeToMoveSelection = maxWaitingTime;
            } else if (gamePanel.getKeyHandler().isDownPressed() && indexSelection < 2) {
                indexSelection++;
                waitingTimeToMoveSelection = maxWaitingTime;
            }

            if (gamePanel.getKeyHandler().isEnterPressed()) {
                if (indexSelection == 0) {
                    isOpen = false;
                    gamePanel.resume();
                } else if (indexSelection == 1) {
                    System.out.println("go to HUB");
                } else if (indexSelection == 2) {
                    System.exit(0);
                }
            }
            
        }

        if (waitingTimeToMoveSelection > 0 && isOpen) {
                waitingTimeToMoveSelection--;
            }

        if (waitingTimeToOpenMenu > 0) {
            waitingTimeToOpenMenu--;
        }

    }

    @Override
    public void draw(Graphics2D graphics2d, GamePanel gamePanel) {
        if (isOpen) {
            MenuDrawer.drawPauseMenu(graphics2d, indexSelection);

        }
    }

}
