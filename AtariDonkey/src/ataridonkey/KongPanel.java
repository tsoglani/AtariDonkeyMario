/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ataridonkey;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author gaitanesnikos
 */
public class KongPanel extends JPanel {

    KongFrame frame;

    ArrayList recToGet = new ArrayList();
    private Rectangle barrelRec;
    int kngcolum = -67;
    Image stair;
    static int numOfLivesRemaining = 4;
    static int kngrow = 0;
    static ArrayList stairs = new ArrayList();
    static ArrayList barelList = new ArrayList();
    Image kngKong = getToolkit().getImage("images/ddrop.png");
    Image barrel = getToolkit().getImage("images/barrel.png");
    Image hearts = getToolkit().getImage("images/lives.png");
    Level level = new Level();
    Rectangle r;
    Random rand = new Random();
    Image lifesImage = getToolkit().getImage("images/lives.png");
    int xx, yy, kk, ll;

    public void randomStairs() {
        xx = rand.nextInt(500);
        yy = rand.nextInt(500);
        kk = rand.nextInt(500);
        ll = rand.nextInt(500);


    }
    Rectangle exit = new Rectangle(rand.nextInt(300) + 200, 65, 120, 90);

    public Rectangle getExit() {
        return exit;
    }

    public void setExit(Rectangle exit) {
        this.exit = exit;
    }

    public void createStairs(int x, int y) {


        stair = getToolkit().getImage("images/1.png");
        r = new Rectangle(x + 100, y - 25, stair.getWidth(this) - 5, stair.getHeight(this));

        stairs.add(r);
    }

    public void fulTheRecWithItemsMarioToGet() {

        recToGet.add(new Rectangle(rand.nextInt(600) + 50, 260, 20, 20));
        recToGet.add(new Rectangle(rand.nextInt(600) + 50, 410, 20, 20));
        recToGet.add(new Rectangle(rand.nextInt(600) + 50, 560, 20, 20));
        recToGet.add(new Rectangle(rand.nextInt(600) + 50, 700, 20, 20));


    }

    public ArrayList getRecToGet() {
        return recToGet;
    }

    public void setRecToGet(ArrayList recToGet) {
        this.recToGet = recToGet;
    }

    public KongPanel(KongFrame frame) {
        this.frame = frame;
        randomStairs();
        fulTheRecWithItemsMarioToGet();
        MarrelThread m = new MarrelThread(this);
        m.start();


    }

    public void nextLevel() {
////        Iterator iter= stairs.iterator();
////        while (iter.hasNext()){
////        Rectangle rec= (Rectangle) iter.next();
////        stairs.remove(rec);
////        
////        }

        if (numOfLivesRemaining < 4) {
            numOfLivesRemaining = 4;
        }
        stairs.removeAll(stairs);


        KongFrame.marioWalk = getToolkit().getImage("images/walk1.png");
        randomStairs();
        exit = null;
        exit = new Rectangle(rand.nextInt(300) + 200, 65, 120, 90);
        KongFrame.row = 100;
        KongFrame.colum = 670;
        if (KingThread.fastKong < 9) {
            KingThread.fastKong += 0.4;
        }
        if (MarrelThread.timer > 1000) {
            MarrelThread.timer -= 500;
        }
        Iterator it = recToGet.iterator();

        fulTheRecWithItemsMarioToGet();

    }
//
//    public void checkLoseLife(int x, int y, int z, int a, boolean loose) {
//        barrelRec = new Rectangle(x, y, z, a);
//        
//        if (barrelRec.contains(KongFrame.row + 35, KongFrame.colum + 30)) {
//            //System.out.println(" headshot");
//           loose=true;
//            if (loose) {
//                System.out.println(numOfLivesRemaining);
//                numOfLivesRemaining--;
//                
//            }
//            KongFrame.row = 100;
//            KongFrame.colum = 670;
//
//        }
//    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
            BufferedImage buffImage = new BufferedImage(700, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D gg = (Graphics2D) gr;
        Graphics2D g = buffImage.createGraphics();
       
        g.setColor(Color.gray);
        g.fillRect(0, 0, 1100, 1000);



        if (numOfLivesRemaining >= 0) {
            g.setColor(Color.white);
            g.fill(exit);
            g.setColor(Color.black);
            level.addRectangle(0, 750, 700, 50);
            level.addRectangle(0, 600, 700, 50);
            level.addRectangle(0, 450, 700, 50);
            level.addRectangle(0, 300, 700, 50);
            level.addRectangle(0, 150, 700, 50);
            Iterator it = Level.levelArray.iterator();
            while (it.hasNext()) {
                Rectangle rec = (Rectangle) it.next();

                g.fill(rec);

            }









            for (int i = 0; i < numOfLivesRemaining; i++) {
                g.drawImage(lifesImage, i * 50, 10, this);
            }

            createStairs(xx, 590);
            createStairs(yy, 440);
            createStairs(kk, 290);
            createStairs(ll, 140);
            g.setColor(Color.red);
//g.draw(r);
            g.drawImage(stair, xx + 100, 590, this);
            g.drawImage(stair, yy + 100, 440, this);
            g.drawImage(stair, kk + 100, 290, this);
            g.drawImage(stair, ll + 100, 140, this);
            g.drawImage(kngKong, kngrow, kngcolum, this);
            g.drawImage(KongFrame.marioWalk, KongFrame.row, KongFrame.colum, this);
            g.drawString("H", KongFrame.row + 35, KongFrame.colum + 50);
            g.setColor(Color.yellow);
            Iterator tit = recToGet.iterator();

            try {
                while (tit.hasNext()) {
                    Rectangle rec = (Rectangle) tit.next();
                    g.fill(rec);

                    if (KongFrame.marioRectangle.contains(rec)) {
                        recToGet.remove(rec);
                    }
                }



            } catch (Exception e) {
            }

            Iterator iter = barelList.iterator();
            while (iter.hasNext()) {

                BarrelThread bar = (BarrelThread) iter.next();
                g.drawImage(barrel, bar.row, bar.colum, this);

                // g.setColor(Color.red);
                // g.drawRect(bar.row, bar.colum-5, barrel.getHeight(this)+10, barrel.getWidth(this));
                //checkLoseLife(bar.row, bar.colum - 5, barrel.getHeight(this) + 10, barrel.getWidth(this), isHiten);


            }





            g.setColor(Color.white);
            frame.setSize(700, 800);

        } else {
            Font f = new Font("", Font.BOLD, 30);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString("GameOver", 300, 300);

        }

        //   gg.setPaint(new TexturePaint(buffImage, new Rectangle(700, 700)));
        gg.drawImage(buffImage, 0, 0, this);
        
        //    this.repaint();

    }
}
