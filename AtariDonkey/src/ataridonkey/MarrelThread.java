/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ataridonkey;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaitanesnikos
 */
public class MarrelThread extends Thread {

    static int timer = 7000;
    KongPanel p;
  BarrelThread b;
    public MarrelThread(KongPanel p) {
        this.p = p;
    }

    public void run() {
        while (true) {
            try {
                this.sleep(timer);
//                if(b!=null){
//                    System.out.println(b.isAlive());
//                  
//                }
                 b = new BarrelThread(KongPanel.kngrow, 0, p);
                b.start();
                KongPanel.barelList.add(b);
                
                
                
            } catch (InterruptedException ex) {
            }
        }


    }
}
