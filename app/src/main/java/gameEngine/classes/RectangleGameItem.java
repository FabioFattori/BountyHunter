package gameEngine.classes;

import gameEngine.collision.Rectangle;

public class RectangleGameItem extends GameEntity {

    private Rectangle bound;

    public RectangleGameItem(Rectangle bound) {
        this.bound = bound;
    }

    public Rectangle getBound() {
        return bound;
    }

    public void setBound(Rectangle bound) {
        this.bound = bound;
    }
}
