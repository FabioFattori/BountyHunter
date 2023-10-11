package bountyhunter.Classes.Entyties;

import bountyhunter.Classes.WeaponsClasses.Weapon;
import bountyhunter.Classes.GraphicsClasses.MenuDrawer;
import bountyhunter.PannelControll;
import java.awt.Graphics2D;
import java.util.List;

public class InventoryHandler {
    private List<Weapon> inventory = new java.util.ArrayList<Weapon>();
    private boolean isOpen = false;
    private PannelControll pc;
    private int currentIndex = 0;
    private boolean isKeyPressed = false;
    private final int maxWaitingTime;
    private int waitingTime;

    public InventoryHandler(List<Weapon> inventory, PannelControll pc) {
        this.inventory = inventory;
        this.pc = pc;
        maxWaitingTime = pc.getFps() / 10;
        waitingTime = maxWaitingTime;
    }

    public void update() {
        if (pc.keyHandler.inventory && !this.isKeyPressed) {
            this.isKeyPressed = true;
            isOpen = !isOpen;
        } else if (!pc.keyHandler.inventory) {
            this.isKeyPressed = false;
        }

        if (isOpen) {
            if (waitingTime <= 0) {
                if (pc.keyHandler.upInventory) {
                    if (currentIndex - 5 > 0) {
                        currentIndex -= 5;
                        waitingTime = maxWaitingTime;
                    }
                } else if (pc.keyHandler.downInventory) {
                    if (currentIndex + 5 < inventory.size() - 1) {
                        currentIndex += 5;
                        waitingTime = maxWaitingTime;
                    }
                } else if (pc.keyHandler.leftInventory) {
                    if (currentIndex > 0) {
                        currentIndex--;
                        waitingTime = maxWaitingTime;
                    }
                } else if (pc.keyHandler.rightInventory) {
                    if (currentIndex < inventory.size() - 1) {
                        currentIndex++;
                        waitingTime = maxWaitingTime;
                    }
                }
            }

            if (pc.keyHandler.changeWeapon) {
                pc.getPlayer().setWeapon(inventory.get(currentIndex));
            }

            waitingTime--;
        }

    }

    public void draw(Graphics2D g2d) {
        if (isOpen) {
            MenuDrawer.drawInventory(g2d, pc.getPlayer(), this.currentIndex);
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

}
