package gameEngine;

public class GameConfiguration {
    private final int tileSize;
    private final int scale;
    private final int columns;
    private final int rows;
    private final int fps;
    private final String assetPath;

    public GameConfiguration(int tileSize, int scale, int columns, int rows, int fps, String assetPath) {
        this.tileSize = tileSize;
        this.scale = scale;
        this.columns = columns;
        this.rows = rows;
        this.fps = fps;
        this.assetPath = assetPath;
    }

    public int getScreenSizeX() {
        return tileSize * scale * columns;
    }

    public int getScreenSizeY() {
        return tileSize * scale * rows;
    }

    public int getTileSize() {
        return tileSize * scale;
    }

    public int getDefaultTileSize() {
        return tileSize;
    }

    public int getScale() {
        return scale;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public int getFPS() {
        return fps;
    }

    public String getAssetPath() {
        return assetPath;
    }

}
