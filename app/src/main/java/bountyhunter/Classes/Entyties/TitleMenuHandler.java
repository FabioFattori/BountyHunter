package bountyhunter.Classes.Entyties;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import bountyhunter.GameTitlePannel;

public class TitleMenuHandler implements KeyListener{
    private final GameTitlePannel gtp;
    private boolean isEnterPressed = false;
    private boolean gameIsStarted = false;

    public TitleMenuHandler(GameTitlePannel gtp) {
        this.gtp = gtp;
    }

    

    @Override
    public void keyTyped(KeyEvent e) {
        //nothing to do here
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_ENTER:
                isEnterPressed = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(isEnterPressed && !gameIsStarted) {
            gtp.startGame();
            gameIsStarted = true;
        }
    }
}
