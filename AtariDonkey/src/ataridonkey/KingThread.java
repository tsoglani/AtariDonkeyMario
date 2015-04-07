package ataridonkey;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author gaitanesnikos
 */
public class KingThread extends Thread {

    KongPanel p;
    KongFrame f;
    static int fastKong = 5;
// poso grigora trexei o donkey 

    public void run() {

        while (true) {
            try {
                this.sleep(100);
                if (KongFrame.row > KongPanel.kngrow + 145) {
                    KongPanel.kngrow += fastKong;

                } else if (KongFrame.row < KongPanel.kngrow + 145) {
                    KongPanel.kngrow -= fastKong;

                }
                p.repaint();


                Iterator it = KongPanel.barelList.iterator();
                while (it.hasNext()) {
                    BarrelThread bar = (BarrelThread) it.next();
                    Rectangle r = new Rectangle(bar.row, bar.colum - 5, 51, 57);

                    if (r.contains(KongFrame.row + 35, KongFrame.colum + 30)) {
                        KongPanel.numOfLivesRemaining--;
                        KongFrame.row = 100;
                        KongFrame.colum = 670;
                        this.sleep(100);

                    }


                }
                Rectangle r = p.getExit();
                if (r.contains(KongFrame.row + 35, KongFrame.colum + 50) && p.getRecToGet().isEmpty()) {
f.setStage(f.getStage()+1);
f.setTitle("DonkeyKon Stage : " + f.getStage());
                    p.nextLevel();


                }



            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }


        }

    }

    public KingThread(KongPanel p,KongFrame f) {
        this.p = p;
        this.f=f;
    }
}
