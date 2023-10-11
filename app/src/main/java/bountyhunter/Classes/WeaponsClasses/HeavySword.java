package bountyhunter.Classes.WeaponsClasses;

import java.awt.Graphics2D;

public class HeavySword extends Weapon {
    public HeavySword() {
        super(10, 100, 50, 0);
    }

    @Override
    public String toString() {
        return "Spada Pesante";
    }

    public void DrawIcon(Graphics2D g2d, int x, int y) {
        g2d.setColor(java.awt.Color.white);
        g2d.drawRect(x, y, 10, 20);
        g2d.drawLine(x + 5, y + 20, x + 5, y + 30);
        g2d.drawLine(x, y, x + 5, y - 5);
        g2d.drawLine(x + 5, y - 5, x + 15, y);
    }

}
