package bountyhunter.Classes.WeaponsClasses;
import java.awt.image.BufferedImage;

public abstract class Weapon {
    private int damage;
    private int attackSpeed; // the higher the attack speed, the slower the attack
    private int range;
    private int speed;
    protected BufferedImage icon;

    public Weapon(int damage, int attackSpeed, int range, int speed) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.range = range;
        this.speed = speed;
        this.icon = null;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Weapon && ((Weapon) o).damage == this.damage && ((Weapon) o).speed == this.speed
                && ((Weapon) o).range == this.range && ((Weapon) o).attackSpeed == this.attackSpeed) {
            return true;
        }
        return false;
    }

    /* get & set section */

    public int getDamage() {
        return damage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        if (icon == null)
            throw new IllegalArgumentException("Icon cannot be null");
        this.icon = icon;
    }

    public int getRange() {
        return range;
    }

    public int getSpeed() {
        return speed;
    }

    public void setDamage(int damage) {
        if(damage <= 0)
            throw new IllegalArgumentException("Damage cannot be negative");
        this.damage = damage;
    }

    public void setAttackSpeed(int attackSpeed) {
        if(attackSpeed <= 0)
            throw new IllegalArgumentException("Attack speed cannot be negative");
        this.attackSpeed = attackSpeed;
    }

    public void setRange(int range) {
        if(range <= 0)
            throw new IllegalArgumentException("Range cannot be negative");
        this.range = range;
    }

    public void setSpeed(int speed) {
        if(speed < 0)
            throw new IllegalArgumentException("Speed cannot be negative");
        this.speed = speed;
    }

    /* end of get & set section */

}
