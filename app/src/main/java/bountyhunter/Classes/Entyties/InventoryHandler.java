package bountyhunter.Classes.Entyties;

import bountyhunter.Classes.WeaponsClasses.Weapon;
import bountyhunter.Classes.GraphicsClasses.MenuDrawer;
import bountyhunter.GameRender;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.event.KeyEvent;

import gameEngine.interfaces.DrawableEntity;
import gameEngine.GamePanel;

public class InventoryHandler implements DrawableEntity {
    private List<Weapon> inventory = new java.util.ArrayList<Weapon>();
    private boolean isOpen = false;
    private GameRender gameRender;
    private int currentIndex = 0;
    private boolean isKeyPressed = false;
    private final int maxWaitingTime;
    private int waitingTime;

    private int inventoryKey = KeyEvent.VK_I;
    private int inventoryUp = KeyEvent.VK_UP;
    private int inventoryDown = KeyEvent.VK_DOWN;
    private int inventoryLeft = KeyEvent.VK_LEFT;
    private int inventoryRight = KeyEvent.VK_RIGHT;
    private int changeWeaponKey = KeyEvent.VK_E;

    public InventoryHandler(List<Weapon> inventory, GameRender gameRender) {
        this.inventory = inventory;
        this.gameRender = gameRender;
        maxWaitingTime = gameRender.getFPS() / 10;
        waitingTime = maxWaitingTime;
    }

    public void update(GamePanel gamePanel) {
        if (gamePanel.getKeyHandler().isKeyPressed(inventoryKey) && !this.isKeyPressed) {
            this.isKeyPressed = true;
            isOpen = !isOpen;
        } else if (!gamePanel.getKeyHandler().isKeyPressed(inventoryKey)) {
            this.isKeyPressed = false;
        }

        if (isOpen) {
            if (waitingTime <= 0) {
                if (gamePanel.getKeyHandler().isKeyPressed(inventoryUp)) {
                    if (currentIndex - 5 > 0) {
                        currentIndex -= 5;
                        waitingTime = maxWaitingTime;
                    }
                } else if (gamePanel.getKeyHandler().isKeyPressed(inventoryDown)) {
                    if (currentIndex + 5 < inventory.size() - 1) {
                        currentIndex += 5;
                        waitingTime = maxWaitingTime;
                    }
                } else if (gamePanel.getKeyHandler().isKeyPressed(inventoryLeft)) {
                    if (currentIndex > 0) {
                        currentIndex--;
                        waitingTime = maxWaitingTime;
                    }
                } else if (gamePanel.getKeyHandler().isKeyPressed(inventoryRight)) {
                    if (currentIndex < inventory.size() - 1) {
                        currentIndex++;
                        waitingTime = maxWaitingTime;
                    }
                }
            }

            if (gamePanel.getKeyHandler().isKeyPressed(changeWeaponKey)) {
                gameRender.getPlayer().setWeapon(inventory.get(currentIndex));
            }

            waitingTime--;
        }

    }

    public void draw(Graphics2D graphics2d, GamePanel gamePanel) {
        if (isOpen) {
            MenuDrawer.drawInventory(graphics2d, gameRender.getPlayer(), this.currentIndex);
        }
    }

    /* get & set section */

    public List<Weapon> getInventory() {
        return inventory;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setInventory(List<Weapon> inventory) {
        this.inventory = inventory;
    }

    public boolean isIsOpen() {
        return isOpen;
    }

}
