package gameEngine.classes;

import javax.swing.JFrame;

public class FrameConfiguration {
    private boolean isResizable;
    private int defaultCloseOperation = JFrame.EXIT_ON_CLOSE;

    public FrameConfiguration(boolean isResizable) {
        this.isResizable = isResizable;
    }

    public FrameConfiguration(boolean isResizable, int defaultCloseOperation) {
        this.isResizable = isResizable;
        this.defaultCloseOperation = defaultCloseOperation;
    }

    public boolean getIsResizable() {
        return isResizable;
    }

    public int getDefaultCloseOperation() {
        return defaultCloseOperation;
    }

    public void setIsResizable(boolean isResizable) {
        this.isResizable = isResizable;
    }

    public void setDefaultCloseOperation(int defaultCloseOperation) {
        this.defaultCloseOperation = defaultCloseOperation;
    }

}
