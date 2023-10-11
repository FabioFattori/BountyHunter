package bountyhunter.Classes.WeaponsClasses;

public class Bow extends RangedWeapon {
    public Bow() {
        super(5, 50, 600, 20);
    }

    @Override
    public String toString() {
        return "Arco";
    }
}
