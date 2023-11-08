package bountyhunter.Classes.WeaponsClasses;

import java.awt.Graphics2D;

import gameEngine.GamePanel;
import gameEngine.enums.Direction;
import gameEngine.interfaces.DrawableEntity;

public class Bullet implements DrawableEntity {
    int x;
    int y;

    int speed = 10;
    Direction direction = Direction.DOWN;
    int tileSize = 10;
    int fpsPassed = 0;

    public Bullet(int x, int y, int range, int speed, int damage, Direction direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
    }

    public void update(GamePanel gamePanel) {
        fpsPassed++;
    }

    public void draw(Graphics2D g2d, GamePanel gamePanel) {
        int currentX;
        int currentY;

        switch (direction) {
            case UP:
                currentX = x + 20;
                currentY = y - (speed * fpsPassed);
                break;
            case DOWN:
                currentX = x + 20;
                currentY = y + (speed * fpsPassed);
                break;
            case LEFT:
                currentX = x - speed * fpsPassed;
                currentY = y + 20;
                break;
            case RIGHT:
                currentX = x + speed * fpsPassed;
                currentY = y + 20;
                break;
            default:
                currentX = x;
                currentY = y;
                break;
        }

        g2d.fillRect(currentX, currentY, tileSize, tileSize);

    }

}
