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

    public void DrawIcon(Graphics2D g2d,int x, int y) {
        g2d.drawImage(this.getIcon(), x, y, 48, 48, null);
    }

}
