package gameEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import gameEngine.enums.Direction;
import java.util.HashMap;
import java.util.Map;

public class KeyHandler implements KeyListener {

    private ArrayList<Integer> keyPressed;

    private Direction direction = Direction.NONE;
    private Direction directionWASD = Direction.NONE;

    private int up = KeyEvent.VK_UP;
    private int down = KeyEvent.VK_DOWN;
    private int left = KeyEvent.VK_LEFT;
    private int right = KeyEvent.VK_RIGHT;

    private int upWASD = KeyEvent.VK_W;
    private int downWASD = KeyEvent.VK_S;
    private int leftWASD = KeyEvent.VK_A;
    private int rightWASD = KeyEvent.VK_D;

    public KeyHandler() {
        keyPressed = new ArrayList<>();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        Integer key = Integer.valueOf(keyCode);

        if (!keyPressed.contains(key)) {
            keyPressed.add(key);
        }

        bindDirection(keyCode);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int keyCode = e.getKeyCode();
        Integer key = Integer.valueOf(keyCode);

        if (keyPressed.contains(key)) {
            keyPressed.remove(key);
        }
    }

    private void bindDirection(int keyCode) {
        if (keyCode == up) {
            direction = Direction.UP;
        } else if (keyCode == down) {
            direction = Direction.DOWN;
        } else if (keyCode == left) {
            direction = Direction.LEFT;
        } else if (keyCode == right) {
            direction = Direction.RIGHT;
        } else if (keyCode == upWASD) {
            directionWASD = Direction.UP;
        } else if (keyCode == downWASD) {
            directionWASD = Direction.DOWN;
        } else if (keyCode == leftWASD) {
            directionWASD = Direction.LEFT;
        } else if (keyCode == rightWASD) {
            directionWASD = Direction.RIGHT;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public boolean isKeyPressed(int keyCode) {
        return keyPressed.contains(keyCode);
    }

    public Direction getDirection() {
        return direction;
    }

    public Direction getDirectionWASD() {
        return directionWASD;
    }

    // UTILS function
    public boolean isEnterPressed() {
        return isKeyPressed(KeyEvent.VK_ENTER);
    }

    public boolean isLeftPressed() {
        return isKeyPressed(KeyEvent.VK_LEFT);
    }

    public boolean isRightPressed() {
        return isKeyPressed(KeyEvent.VK_RIGHT);
    }

    public boolean isUpPressed() {
        return isKeyPressed(KeyEvent.VK_UP);
    }

    public boolean isDownPressed() {
        return isKeyPressed(KeyEvent.VK_DOWN);
    }

    public boolean isSpacePressed() {
        return isKeyPressed(KeyEvent.VK_SPACE);
    }

    public boolean isEscapePressed() {
        return isKeyPressed(KeyEvent.VK_ESCAPE);
    }

    public boolean isUpWASDPressed() {
        return isKeyPressed(KeyEvent.VK_W);
    }

    public boolean isDownWASDPressed() {
        return isKeyPressed(KeyEvent.VK_S);
    }

    public boolean isLeftWASDPressed() {
        return isKeyPressed(KeyEvent.VK_A);
    }

    public boolean isRightWASDPressed() {
        return isKeyPressed(KeyEvent.VK_D);
    }
}
