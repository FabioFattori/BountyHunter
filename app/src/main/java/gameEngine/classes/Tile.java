package gameEngine.classes;

import gameEngine.interfaces.TileDrawable;
import java.awt.image.BufferedImage;

public class Tile implements TileDrawable {
    private String path;
    private boolean canPassThrough;
    AssetProvider assetProvider;

    public Tile(AssetProvider assetProvider, String path, boolean canPassThrough) {
        this.path = path;
        this.canPassThrough = canPassThrough;
        this.assetProvider = assetProvider;
    }

    @Override
    public void loadImage() {
        assetProvider.loadImage(path);
    }

    @Override
    public boolean canPassThrough() {
        return canPassThrough;
    }

    @Override
    public BufferedImage getImage() {
        return assetProvider.getImage(path);
    }
}
