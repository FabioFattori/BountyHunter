package bountyhunter.Classes.Entyties;

import bountyhunter.Classes.WeaponsClasses.*;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.Color;

public class Player {
    private int x;
    private int y;
    private int tileSize;
    private int speed;
    private int health = 100;
    private List<Weapon> inventory=new java.util.ArrayList<Weapon>();
    private Weapon weapon;
    private Bullet _bullet;
    private int _bulletShotTime = 0;
    private int _attackingTime = 0;
    private boolean _isPressingAttack = false;

    
    public Player(int tileSize){
        this(tileSize,100,100);
        this.speed = 3;
        this.weapon = new Pugni();
    }

    public Player(int tileSize,int x,int y){
        this.tileSize = tileSize;
        this.x = x;
        this.y = y;
        this.speed = 3;
        this.weapon = new Pugni();
        inventory.add(new Pugni());
        inventory.add(new HeavySword());
        inventory.add(new Bow());
    }

    public Player(int tileSize,Weapon w){
        this(tileSize,100,100);
        
        this.speed = 3;
        this.weapon = w;
    }

    /* get & set section */

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }  

    public Weapon getWeapon() {
        return weapon;
    }

    public void setHealth(int health) {
        if(health < 0)
            throw new IllegalArgumentException("Health cannot be negative");
        this.health = health;
    }

    public void setSpeed(int speed) {
        if(speed < 0)
            throw new IllegalArgumentException("Speed cannot be negative");
        this.speed = speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setInventory(List<Weapon> inventory) {
        this.inventory = inventory;
    }

    public List<Weapon> getInventory() {
        return inventory;
    }

    public int getTileSize() {
        return tileSize;
    }

    /* end of get & set section */

    

    public void redraw(Graphics2D g2d, KeyHandler keyHandler){
        g2d.setColor(Color.white);

        g2d.fillRect(x, y, tileSize, tileSize);

        if(!(this.weapon instanceof RangedWeapon))
        {
            if(keyHandler.attack && !_isPressingAttack && _attackingTime <= 0)
            {
                _isPressingAttack = true;
                _attackingTime = weapon.getAttackSpeed();
            }
            else if(!keyHandler.attack)
            {
                _isPressingAttack = false;
            }

            if(_attackingTime > 0){
                _attackingTime--;
                AttackNotRanged(g2d, keyHandler);
            }
        }
        else
        {
            if(keyHandler.attack && _bullet == null)
            {
                _bullet = new Bullet(x,y,weapon.getRange(),weapon.getSpeed(),weapon.getDamage(),keyHandler.direction);
                _bulletShotTime = 0;
            }
            
            if(_bullet != null)
            {
                AttackRanged(g2d);
                _bulletShotTime++;
            }

            if(_bulletShotTime * weapon.getSpeed() > weapon.getRange())
            {
                _bullet = null;
            }
        

        }
    }

    public void AttackRanged(Graphics2D g2d)
    {
        _bullet.draw(g2d,_bulletShotTime);
    }

    public void AttackNotRanged(Graphics2D g2d,KeyHandler keyHandler){
        g2d.setColor(Color.red);
        
        switch (keyHandler.direction) {
                case "up":
                    g2d.fillRect(x, y-this.weapon.getRange(), tileSize, tileSize);
                    break;
                case "down":
                    g2d.fillRect(x, y+this.weapon.getRange(), tileSize, tileSize);
                    break;
                case "left":
                    g2d.fillRect(x-this.weapon.getRange(), y, tileSize, tileSize);
                    break;
                case "right":
                    g2d.fillRect(x+this.weapon.getRange(), y, tileSize, tileSize);
                    break;
            
                default:
                    break;
        }
    }
}
