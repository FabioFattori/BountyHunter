package bountyhunter;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import gameEngine.GameConfiguration;
import gameEngine.GamePanel;
import gameEngine.classes.GameEntity;
import gameEngine.classes.GamePresentation;
import gameEngine.interfaces.DrawableEntity;

public class App {

    private static String APPLICATION_TITLE = "Bounty Hunter V0.1";

    public static void main(String[] args) {
        JFrame frame = new JFrame(APPLICATION_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Dimension screenDimension = new Dimension(600, 400);

        GamePanel gp = new GamePanel(getDefaultConfiguration(), new ArrayList<DrawableEntity>(),
                Color.black);
        gp.setGamePresentation(getDefaultPresentation());

        frame.setPreferredSize(screenDimension);
        frame.setSize(screenDimension);
        frame.add(gp);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);

        GameRender gameRender = new GameRender(gp);
        gp.addGameEntities(gameRender);

        gp.run();
    }

    private static GameConfiguration getDefaultConfiguration() {
        return new GameConfiguration(16, 3, 20, 15, 60, "/Assets/");
    }

    private static GamePresentation getDefaultPresentation() {
        return new GamePresentation(APPLICATION_TITLE);
    }
}
