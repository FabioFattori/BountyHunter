package gameEngine.classes;

import java.awt.Graphics2D;

import gameEngine.GamePanel;
import gameEngine.interfaces.DrawableEntity;
import java.awt.Color;
import java.awt.Font;

public class GamePresentation implements DrawableEntity {

    private String title;
    private String subtitle;
    private boolean show;

    public GamePresentation(String title) {
        this.title = title;
        initialize();
    }

    private void initialize() {
        show = true;
    }

    public boolean getShow() {
        return show;
    }

    public boolean setShow(boolean show) {
        return this.show = show;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Override
    public void update(GamePanel gamePanel) {
        if (gamePanel.getKeyHandler().isEnterPressed()) {
            show = false;
        }
    }

    @Override
    public void draw(Graphics2D graphics2d, GamePanel gamePanel) {
        graphics2d.setColor(Color.white);
        graphics2d.drawString("Press Enter to start", 220, 150);
        graphics2d.setFont(new Font("Comic Sans", Font.ITALIC, 40));
        graphics2d.drawString(title, 100, 70);
    }
}
