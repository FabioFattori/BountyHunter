package bountyhunter.Classes.WeaponsClasses;

import java.util.ArrayList;
import java.util.List;

public class RangedWeapon extends Weapon {
    private List<Bullet> bulletsShoted;

    public RangedWeapon(int attackSpeed, int damage, int range, int speed) {
        super(attackSpeed, damage, range, speed);
        bulletsShoted = new ArrayList<Bullet>();
    }

    public List<Bullet> getBulletsShoted() {
        return bulletsShoted;
    }
}
