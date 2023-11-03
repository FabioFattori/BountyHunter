package bountyhunter.Classes.GraphicsClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;

import bountyhunter.EnviromentVariables;

public class MapDrawer {
    private int map[][];
    private int tileSize;
    private HashMap<Integer, BufferedImage> tileImages = new HashMap<Integer, BufferedImage>();

    public MapDrawer(String path, final int tileSize) {
        this.tileSize = tileSize;
        try {

            int currentRow = 0;

            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            readTheFileToGetLenghtOfMatrix(br);
            // devo ricreare il bufferedReader per far ripartire il puntatore dall'inizio
            // del file
            br = new BufferedReader(new FileReader(file));
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
        } catch (Exception e) {
            throw new RuntimeException("Failed to load map: " + path + " error Message => " + e);
        }
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

            for (int[] row : map) {
                for (int i : row) {
                    i = -1;
                }
            }

            br.close();

        } catch (Exception e) {
            throw new RuntimeException("Failed to load map, error Message => " + e);
        }

    }

    public void drawMap(Graphics2D g2d) {
        int i = 0;
        int currentRow = 0;

        try {
            if (this.tileImages.isEmpty()) {
                this.tileImages.put(0, ImageIO.read(new File(EnviromentVariables.getAssetPath() + "ground.png")));
                this.tileImages.put(1, ImageIO.read(new File(EnviromentVariables.getAssetPath() + "border.png")));
                this.tileImages.put(2, ImageIO.read(new File(EnviromentVariables.getAssetPath() + "water.png")));
            }

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
