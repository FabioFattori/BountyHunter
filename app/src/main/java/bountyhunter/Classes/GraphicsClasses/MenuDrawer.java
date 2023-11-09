package bountyhunter.Classes.GraphicsClasses;

import bountyhunter.Classes.WeaponsClasses.*;
import bountyhunter.Classes.Entyties.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import org.checkerframework.checker.units.qual.C;

public abstract class MenuDrawer {
    
    public static void drawTopLeftMenu(Graphics2D g,Player p){
        
        g.setColor(Color.white);
        g.fillRect(0, 5, 200, 70);
        g.setColor(Color.black);
        g.draw(new RoundRectangle2D.Double(0, 5, 200, 70, 10, 10));
        g.drawString("Health: " + p.getHealth(), 10, 20);
        g.drawString("Damage: " + p.getWeapon().getDamage(), 10, 40);
        g.drawString("Weapon type:"+p.getWeapon().toString(), 10, 60);
    }

    public static void drawGameOver(Graphics2D g){
        g.setColor(Color.RED);
        g.drawString("Game Over", 100, 100);
    }

    public static void drawTextPrompt(String text,Graphics2D g){
        g.setColor(Color.white);
        
        g.drawString(text, 100, 100);
    }

    public static void drawInventory(Graphics2D g,Player p,int currentIndex){
        int x=500;
        int y=10;
        int maxWidth=400;
        int maxHeight=300;
        g.setColor(Color.black);
        //draw a rectangole with the inventory in the right side of the screen
        g.drawRect(x, y, maxWidth, maxHeight);
        g.draw(new RoundRectangle2D.Double(x, y, maxWidth, maxHeight, 10, 10));
        g.drawString("Inventory", x+50, y+15);
        
        int i = 0;
        for (Weapon w : p.getInventory()) {
            if(i==5){
                i=0;
            }
                if(p.getInventory().indexOf(w)==currentIndex){
                    g.drawRoundRect(x+10+i*56, y+35, 48, 48,10,10);
                    
                }

                //check if the weapon is equipped
                if(p.getWeapon().equals(w)){
                    g.setColor(Color.orange);
                    g.fillRoundRect(x+10+i*56, y+35, 48, 48,10,10);
                    g.setColor(Color.black);
                }

                if(w instanceof HeavySword){
                    ((HeavySword)w).DrawIcon(g, x+i*56+10, y+35);
                }
            i++;
        }
    }

    public static void drawPauseMenu(Graphics2D g,int index){
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.black);
        g.drawString("Game Paused", 100, 100);
        if(index == 0){
            g.setColor(Color.red);
        }
        g.drawString("Resume", 120, 120);
        g.setColor(Color.black);
        if(index == 1){
            g.setColor(Color.red);
        }
        g.drawString("Hub", 120, 170);
        g.setColor(Color.black);
        if(index == 2){
            g.setColor(Color.red);
        }

        g.drawString("Exit", 120, 210);

        g.setColor(Color.black);



    }
}
