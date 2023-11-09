package gameEngine.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gameEngine.interfaces.TileDrawable;

public class TileManager {

    private Map<Integer, TileDrawable> tiles;
    AssetProvider assetProvider;

    public TileManager(AssetProvider assetProvider) {
        this.assetProvider = assetProvider;
        tiles = new HashMap<>();
    }

    public void setTile(Map<Integer, TileDrawable> tiles) {
        this.tiles = tiles;
    }

    public void addTile(int id, String path, boolean canPassThrough) {
        addTile(id, new Tile(assetProvider, path, canPassThrough));
    }

    public void addTile(int id, TileDrawable tile) {
        tile.loadImage();
        tiles.put(id, tile);
    }

    public Map<Integer, TileDrawable> getTiles() {
        return tiles;
    }

    public TileDrawable getTile(int i) {
        if (tiles.containsKey(i))
            return tiles.get(i);
        else
            return null;
    }
}
