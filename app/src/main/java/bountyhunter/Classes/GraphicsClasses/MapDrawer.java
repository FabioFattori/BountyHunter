package bountyhunter.Classes.GraphicsClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;

import bountyhunter.EnviromentVariables;
import gameEngine.GamePanel;
import gameEngine.classes.AssetProvider;

public class MapDrawer {
    private int map[][];
    private int tileSize;
    private HashMap<Integer, BufferedImage> tileImages = new HashMap<Integer, BufferedImage>();
    AssetProvider assetProvider;

    public MapDrawer(GamePanel gamePanel, String mapPath) {
        this.tileSize = gamePanel.getConfiguration().getTileSize();
        this.assetProvider = gamePanel.getAssetProvider();
        String path = assetProvider.fullPath(mapPath);

        try {

            int currentRow = 0;

            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            readTheFileToGetLenghtOfMatrix(br);
            // devo ricreare il bufferedReader per far ripartire il puntatore dall'inizio
            // del file
            is = getClass().getResourceAsStream(path);
            br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            while (line != null) {

                String[] tileNums = line.split(" ");
                int[] tileNumsInt = new int[tileNums.length];
                int i = 0;
                for (String tielString : tileNums) {
                    tileNumsInt[i] = Integer.parseInt(tielString);
                    i++;
                }

                map[currentRow] = tileNumsInt;
                currentRow++;

                line = br.readLine();

            }

            br.close();

            // load images once for all
            loadMapAssets();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load map: " + path + " error Message => " + e);
        }
    }

    private void loadMapAssets() {

        String groundPath = "ground.png";
        String borderPath = "border.png";
        String waterPath = "water.png";

        // assetProvider.loadImage(groundPath);
        // assetProvider.loadImage(borderPath);
        // assetProvider.loadImage(waterPath);
        this.tileImages.put(0, assetProvider.getImage(groundPath));
        this.tileImages.put(1, assetProvider.getImage(borderPath));
        this.tileImages.put(2, assetProvider.getImage(waterPath));
    }

    private void readTheFileToGetLenghtOfMatrix(BufferedReader br) {
        try {
            int nRow = 0;
            int nCol = 0;
            String line = br.readLine();

            while (line != null) {
                nRow++;
                String[] tileNums = line.split(" ");
                if (nCol <= tileNums.length) {
                    nCol = tileNums.length;
                }
                line = br.readLine();
            }

            map = new int[nRow][nCol];

            br.close();

        } catch (Exception e) {
            throw new RuntimeException("Failed to load map, error Message => " + e);
        }

    }

    public void drawMap(Graphics2D g2d) {
        int i = 0;
        int currentRow = 0;

        try {

            for (int[] row : map) {
                for (int tile : row) {
                    if (tile == 0) {
                        g2d.drawImage(this.tileImages.get(0), i * tileSize, currentRow * tileSize, tileSize, tileSize,
                                null);
                    } else if (tile == 1) {
                        g2d.drawImage(this.tileImages.get(1), i * tileSize, currentRow * tileSize, tileSize, tileSize,
                                null);
                    } else if (tile == 2) {
                        g2d.drawImage(this.tileImages.get(2), i * tileSize, currentRow * tileSize, tileSize, tileSize,
                                null);
                    } else if (tile == -1) {
                        g2d.setColor(java.awt.Color.black);
                    }

                    i++;
                }
                i = 0;
                currentRow++;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load map, error Message => " + e);
        }

    }

    public int[][] getMap() {
        return map;
    }
}
