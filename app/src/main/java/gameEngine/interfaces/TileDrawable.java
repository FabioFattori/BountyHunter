package gameEngine.interfaces;

import java.awt.image.BufferedImage;

public interface TileDrawable {
    public void loadImage();

    public boolean canPassThrough();

    public BufferedImage getImage();
}
