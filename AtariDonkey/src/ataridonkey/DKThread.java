/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ataridonkey;

import java.awt.*;
import java.util.*;
import java.util.logging.Logger;

/**
 *
 * @author gaitanesnikos
 */
public class DKThread extends Thread {

    KongPanel p;

    public DKThread(KongPanel p) {
        this.p = p;
    }

    public void run() {
        try {


            while (true) {
                this.sleep(10);
                boolean isOnFlour = false;

                Iterator it = Level.levelArray.iterator();
                while (it.hasNext()) {

                    Rectangle rec = (Rectangle) it.next();
                    if (rec.contains(KongFrame.row + 25, KongFrame.colum + 70)) {
                        isOnFlour = true;



                    }


                }


                



                if (!isOnFlour) {
                    KongFrame.colum += 1;
                } else {
                    KongFrame.colum--;
                }





                p.repaint();

            }
        } catch (Exception ex) {
            run();
        }


    }
}
