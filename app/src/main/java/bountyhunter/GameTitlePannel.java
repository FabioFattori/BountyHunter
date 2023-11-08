package bountyhunter;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import bountyhunter.Classes.Entyties.TitleMenuHandler;

public class GameTitlePannel extends JPanel implements Runnable {
    private final JFrame frame;
    Thread titleThread;
    private EnviromentVariables ev = new EnviromentVariables();
    private TitleMenuHandler keyHandler = new TitleMenuHandler(this);

    public GameTitlePannel(final JFrame frame) {
        this.frame = frame;
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(ev.getScreenSizeX(), ev.getScreenSizeY()));
        this.setBackground(Color.black);
        this.addKeyListener(keyHandler);

    }

    public void startTitleThread() {
        titleThread = new Thread(this);
        titleThread.start();
    }

    public void startGame() {
        System.out.println("Game Started");

        /* delete old title window and then create a new window to host the game */
        this.frame.dispose();
        JFrame gameFrame = new JFrame(ev.getTitleOfWindow());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);

        /* create the game pannel then run it to make the game start */
        PannelControll panel = new PannelControll(gameFrame);
        gameFrame.setPreferredSize(new Dimension(ev.getScreenSizeX(), ev.getScreenSizeY()));
        gameFrame.setSize(new Dimension(ev.getScreenSizeX(), ev.getScreenSizeY()));
        gameFrame.add(panel);
        gameFrame.setResizable(true);
        gameFrame.setVisible(true);

        panel.startGameThread();
    }

    @Override
    public void run() {
        while (this.titleThread != null) {

            repaint();

            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.drawString("Press Enter to start", 220, 150);
        g2d.setFont(new Font("Comic Sans", Font.ITALIC, 40));
        g2d.drawString(ev.getTitleOfWindow(), 100, 70);
    }

}