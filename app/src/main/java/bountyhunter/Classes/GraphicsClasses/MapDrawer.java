package bountyhunter.Classes.GraphicsClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.Graphics2D;

public class MapDrawer {
    private int map[][];
    private int tileSize;

    public MapDrawer(String path, final int  tileSize) {
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

        for (int[] row : map) {
            for (int tile : row) {
                if (tile == 0) {
                    g2d.setColor(java.awt.Color.yellow);
                } else if (tile == 1) {
                    g2d.setColor(java.awt.Color.white);
                } else if (tile == 2) {
                    g2d.setColor(java.awt.Color.red);
                } else if (tile == -1) {
                    g2d.setColor(java.awt.Color.black);
                }

                g2d.fillRect(i * tileSize, currentRow * tileSize, tileSize, tileSize);
                i++;
            }
            i = 0;
            currentRow++;
        }
        
    }

    public int[][] getMap() {
        return map;
    }
}
