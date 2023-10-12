package bountyhunter;

import org.checkerframework.checker.guieffect.qual.SafeEffect;

public class EnviromentVariables {
    private final int tileSize = 48;
    private int maxTileX = 20;
    private int maxTileY = 15;
    private final String pathToAsset = "../../../../Assets/";

    public EnviromentVariables() {
        this(20, 15);
    }

    public EnviromentVariables(final int maxTileX,final int maxTileY) {
        this.maxTileX = maxTileX;
        this.maxTileY = maxTileY;
    }



    public int getScreenSizeX() {
        return tileSize * maxTileX;
    }

    public int getScreenSizeY() {
        return tileSize * maxTileY;
    }

    public int getTileSize() {
        return tileSize;
    }

    public String getPathToAsset() {
        return pathToAsset;
    }

    public int getMaxTileX() {
        return maxTileX;
    }

    public int getMaxTileY() {
        return maxTileY;
    }

    public void setMaxTileX(int maxTileX) {
        this.maxTileX = maxTileX;
    }

    public void setMaxTileY(int maxTileY) {
        this.maxTileY = maxTileY;
    }


}
