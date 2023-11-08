package bountyhunter.Classes.Entyties;

import bountyhunter.EnviromentVariables;
import bountyhunter.Classes.WeaponsClasses.*;
import gameEngine.GamePanel;
import gameEngine.interfaces.DrawableEntity;
import gameEngine.enums.Direction;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.awt.Color;

public class Player implements DrawableEntity {
    private BufferedImage icon;
    private int x;
    private int y;
    private int tileSize;
    private int speed;
    private int health = 100;
    private List<Weapon> inventory = new java.util.ArrayList<Weapon>();
    private Weapon weapon;
    private Bullet _bullet;
    private int _bulletShotTime = 0;
    private int _attackingTime = 0;
    private boolean _isPressingAttack = false;

    private int upKey = KeyEvent.VK_W;
    private int downKey = KeyEvent.VK_S;
    private int leftKey = KeyEvent.VK_A;
    private int rightKey = KeyEvent.VK_D;
    private int attackKey = KeyEvent.VK_SPACE;

    public Player(int tileSize) {
        this(tileSize, 100, 100);
        this.speed = 3;
        this.weapon = new Pugni();
    }

    public Player(int tileSize, int x, int y) {
        this.tileSize = tileSize;
        this.x = x;
        this.y = y;
        this.speed = 3;
        this.weapon = new Pugni();
        inventory.add(new Pugni());
        HeavySword hs = new HeavySword();
        try {
            hs.setIcon(ImageIO
                    .read(getClass().getResourceAsStream("/Assets/HeavySword.png")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        inventory.add(hs);
        inventory.add(new Bow());
    }

    public Player(int tileSize, Weapon w) {
        this(tileSize, 100, 100);

        this.speed = 3;
        this.weapon = w;
    }

    Direction direction = Direction.NONE;

    public void update(GamePanel gamePanel) {
        boolean upPressed = gamePanel.getKeyHandler().isKeyPressed(upKey);
        boolean downPressed = gamePanel.getKeyHandler().isKeyPressed(downKey);
        boolean leftPressed = gamePanel.getKeyHandler().isKeyPressed(leftKey);
        boolean rightPressed = gamePanel.getKeyHandler().isKeyPressed(rightKey);
        boolean attackPressed = gamePanel.getKeyHandler().isKeyPressed(attackKey);

        int screenSizeY = gamePanel.getConfiguration().getScreenSizeY();
        int screenSizeX = gamePanel.getConfiguration().getScreenSizeX();

        direction = gamePanel.getKeyHandler().getDirectionWASD();

        if (upPressed && this.getY() - this.getSpeed() > 0) {
            this.setY(this.getY() - this.getSpeed());
        }
        if (downPressed && this.getY() + this.getSpeed() < screenSizeY - this.getTileSize()) {
            this.setY(this.getY() + this.getSpeed());
        }
        if (leftPressed && this.getX() - this.getSpeed() > 0) {
            this.setX(this.getX() - this.getSpeed());
        }
        if (rightPressed && this.getX() + this.getSpeed() < screenSizeX - this.getTileSize()) {
            this.setX(this.getX() + this.getSpeed());
        }

        if (!(this.weapon instanceof RangedWeapon)) {
            if (attackPressed && !_isPressingAttack && _attackingTime <= 0) {
                _isPressingAttack = true;
                _attackingTime = weapon.getAttackSpeed();
            } else if (!attackPressed) {
                _isPressingAttack = false;
            }
        } else {
            if (attackPressed && _bullet == null) {

                _bullet = new Bullet(x, y, weapon.getRange(), weapon.getSpeed(), weapon.getDamage(), direction);
                _bulletShotTime = 0;
            }

            if (_bulletShotTime * weapon.getSpeed() > weapon.getRange()) {
                _bullet = null;
            }

        }
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
        if (health < 0)
            throw new IllegalArgumentException("Health cannot be negative");
        this.health = health;
    }

    public void setSpeed(int speed) {
        if (speed < 0)
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

    public void draw(Graphics2D g2d, GamePanel gamePanel) {

        try {
            if (this.icon == null) {
                this.icon = ImageIO.read(getClass().getResourceAsStream("/Assets/player.png"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        g2d.drawImage(this.icon, x, y, tileSize, tileSize, null);

        if (!(this.weapon instanceof RangedWeapon)) {
            if (_attackingTime > 0) {
                _attackingTime--;
                AttackNotRanged(g2d, gamePanel);
            }
        } else {
            if (_bullet != null) {
                AttackRanged(g2d, gamePanel);
            }
        }
    }

    public void AttackRanged(Graphics2D g2d, GamePanel gamePanel) {
        _bullet.draw(g2d, gamePanel);
    }

    public void AttackNotRanged(Graphics2D g2d, GamePanel gamePanel) {
        g2d.setColor(Color.red);

        switch (direction) {
            case UP:
                g2d.fillRect(x, y - this.weapon.getRange(), tileSize, tileSize);
                break;
            case DOWN:
                g2d.fillRect(x, y + this.weapon.getRange(), tileSize, tileSize);
                break;
            case LEFT:
                g2d.fillRect(x - this.weapon.getRange(), y, tileSize, tileSize);
                break;
            case RIGHT:
                g2d.fillRect(x + this.weapon.getRange(), y, tileSize, tileSize);
                break;

            default:
                break;
        }
    }
}
