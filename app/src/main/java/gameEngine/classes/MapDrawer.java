package gameEngine.classes;

import java.awt.Graphics2D;

import gameEngine.GamePanel;

import java.util.ArrayList;
import java.util.List;

public class MapDrawer implements gameEngine.interfaces.TileDrawer {

    private List<List<Integer>> map;
    AssetProvider assetProvider;

    public MapDrawer(AssetProvider assetProvider) {
        this.assetProvider = assetProvider;
        this.map = new ArrayList<>();
    }

    @Override
    public void update(GamePanel gamePanel) {

    }

    @Override
    public void draw(Graphics2D graphics2d, GamePanel gamePanel) {
        int i = 0, j = 0;
        int tileSize = gamePanel.getConfiguration().getTileSize();
        TileManager tileManager = gamePanel.getTileManager();

        for (List<Integer> row : map) {
            i = 0;
            for (Integer tile : row) {
                graphics2d.drawImage(tileManager.getTile(tile).getImage(), i * tileSize, j * tileSize,
                        tileSize, tileSize, null);
                i++;
            }
            j++;
        }
    }

    @Override
    public void loadMap(String mapPath) {
        String map = assetProvider.getText(mapPath);

        String[] lines = map.split("\n");
        for (String line : lines) {
            String[] tiles = line.split(" ");
            List<Integer> row = new ArrayList<>();
            for (String tile : tiles) {
                row.add(Integer.parseInt(tile));
            }
            this.map.add(row);
        }
    }

}
