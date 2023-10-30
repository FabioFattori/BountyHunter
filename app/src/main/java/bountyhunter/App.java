package bountyhunter;

import java.awt.Dimension;

import javax.swing.JFrame;

public class App {

    public static void main(String[] args) {
        EnviromentVariables ev = new EnviromentVariables();
        JFrame frame = new JFrame(ev.getTitleOfWindow());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        
          
         Dimension screenDimension = new Dimension(600,400);

        GameTitlePannel gtp = new GameTitlePannel(frame);
        frame.setPreferredSize(screenDimension);
        frame.setSize(screenDimension);
        frame.add(gtp);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
        gtp.startTitleThread();
    }
}
