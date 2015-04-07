/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ataridonkey;

import java.awt.Rectangle;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author gaitanesnikos
 */
public class BarrelThread extends Thread {

    static int timeToFallTheBarrel = 15;

    public int getColum() {
        return colum;
    }

    public void setColum(int colum) {
        this.colum = colum;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    int row;
    int colum;
    KongPanel p;

    public BarrelThread(int row, int colum, KongPanel p) {
        this.row = KongPanel.kngrow + 150;
        this.colum = colum + 30;
        this.p = p;
    }

  
    @SuppressWarnings("deprecation")
    public void run() {
        while (true) {
            try {
                this.sleep(timeToFallTheBarrel);
                colum += 4;
                p.repaint();
                if (colum > 800) { 
                    KongPanel.barelList.remove(this);
this.stop();
                  
                   // break;
                   

                }

            } catch (Exception ex) {
           
            }
        }



    }
}
