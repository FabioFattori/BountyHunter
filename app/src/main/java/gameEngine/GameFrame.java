package gameEngine;

import javax.swing.JFrame;

import gameEngine.classes.FrameConfiguration;

public class GameFrame {

    final private GamePanel gamePanel;
    final private String title;
    private FrameConfiguration frameConfiguration = new FrameConfiguration(true);

    public GameFrame(GamePanel gamePanel, String title) {
        this.gamePanel = gamePanel;
        this.title = title;
    }

    public GameFrame(GamePanel gamePanel, String title, FrameConfiguration frameConfiguration) {
        this.gamePanel = gamePanel;
        this.title = title;
        this.frameConfiguration = frameConfiguration;
    }

    public void run() {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(frameConfiguration.getDefaultCloseOperation());
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(gamePanel.getPreferredSize());
        frame.setSize(gamePanel.getPreferredSize());
        frame.add(gamePanel);
        frame.setResizable(frameConfiguration.getIsResizable());
        frame.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
