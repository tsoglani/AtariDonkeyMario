/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ataridonkey;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author gaitanesnikos
 */
public class Level {

    static ArrayList levelArray = new ArrayList();

    public void addRectangle(int x, int y, int z, int l) {
        Rectangle rec = new Rectangle(x, y, z, l);
        levelArray.add(rec);


    }

    public Level() {
    }
}
