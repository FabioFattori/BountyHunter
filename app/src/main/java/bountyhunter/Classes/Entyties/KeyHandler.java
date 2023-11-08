package bountyhunter.Classes.Entyties;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean up, down, left, right, attack, changeWeapon, inventory, upInventory, downInventory, leftInventory,
            rightInventory;

    public String direction = "down";

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_W:
                direction = "up";
                up = true;
                break;
            case KeyEvent.VK_A:
                direction = "left";
                left = true;
                break;
            case KeyEvent.VK_S:
                direction = "down";
                down = true;
                break;
            case KeyEvent.VK_D:
                direction = "right";
                right = true;
                break;
            case KeyEvent.VK_SPACE:
                attack = true;
                break;
            case KeyEvent.VK_E:
                changeWeapon = true;
                break;
            case KeyEvent.VK_I:
                inventory = true;
                break;
            case KeyEvent.VK_UP:
                upInventory = true;
                break;
            case KeyEvent.VK_LEFT:
                leftInventory = true;
                break;
            case KeyEvent.VK_DOWN:
                downInventory = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightInventory = true;
                break;

            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W:

                up = false;
                break;
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_SPACE:
                attack = false;
                break;
            case KeyEvent.VK_E:
                changeWeapon = false;
                break;
            case KeyEvent.VK_I:
                inventory = false;
                break;
            case KeyEvent.VK_UP:

                upInventory = false;
                break;
            case KeyEvent.VK_LEFT:
                leftInventory = false;
                break;
            case KeyEvent.VK_DOWN:
                downInventory = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightInventory = false;
                break;

            default:
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
