package bountyhunter.Classes.GraphicsClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MapDrawer {
    public void drawMap(String path) {
        try {
            int[][] map = new int[10][10];
            int tileSize = 48;

            File file = new File(path);
            System.out.println(file);
            BufferedReader br
            = new BufferedReader(new FileReader(file));
            

            for (int[] row : map) {
                String line = br.readLine();
                String[] tileNums = line.split(" ");
                int[] tileNumsInt = new int[tileNums.length];
                int i = 0;
                for (String tielString : tileNums) {
                    tileNumsInt[i] = Integer.parseInt(tielString);
                    i++;
                }

                row = tileNumsInt;

            }

            System.out.println(map);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load map: " + path + " " + e);
        }
    }
}
