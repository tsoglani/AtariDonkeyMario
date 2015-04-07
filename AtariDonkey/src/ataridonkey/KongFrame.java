/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ataridonkey;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import javax.swing.*;

/**
 *
 * @author gaitanesnikos
 */
public class KongFrame extends JFrame {

    private int stage = 1;

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }
    KongPanel kingKong;
    static int row = 100;
    static int colum = 600;
    static Image marioWalk;
    static Rectangle marioRectangle;
    boolean goRight = true;
    boolean goLeft = true;
    boolean goUp = true;
    DKThread threds;
    KingThread kgthread;
    boolean isGoingUpOrDown = false;

    public KongFrame() {
        this.setSize(700, 800);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        Music m= new Music();
        kingKong = new KongPanel(this);
        marioRectangle= new Rectangle();
        this.add(kingKong);
        this.setTitle("DonkeyKon Stage : " + stage);
        threds = new DKThread(kingKong);
        threds.start();
        kgthread = new KingThread(kingKong,this);
        kgthread.start();
        marioWalk = getToolkit().getImage("images/walk1.png");
        this.addKeyListener(key);
        this.setVisible(true);
    }
    KeyListener key = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (row - 11 >= -30) {
                    if (isGoingUpOrDown) {
                        isGoingUpOrDown = false;
                        threds.resume();
                    }
                    row -= 11;
                    paintWalkingBack();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (row + 11 < 650) {
                    if (isGoingUpOrDown) {
                        isGoingUpOrDown = false;
                        threds.resume();
                    }
                    row += 11;
                    paintWalking();
                }
            }



            if (e.getKeyCode() == KeyEvent.VK_UP) {
                Iterator iter = KongPanel.stairs.iterator();
                boolean goU = false;
                while (iter.hasNext()) {
                    Rectangle r = (Rectangle) iter.next();

                    if (r.contains(KongFrame.row + 35, KongFrame.colum + 37)) {
                        goU = true;


                    }

                }
                if (goU) {
                    threds.suspend();
                    isGoingUpOrDown = true;
                    colum -= 10;
                    paintClimbing();
                }


            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                boolean goD = false;
                Iterator iter = KongPanel.stairs.iterator();
                while (iter.hasNext()) {
                    Rectangle r = (Rectangle) iter.next();
                    if (r.contains(KongFrame.row + 35, KongFrame.colum + 37)) {
                        goD = true;

                    }

                }



                if (goD) {
                    threds.suspend();
                    isGoingUpOrDown = true;
                    colum += 10;
                    paintClimbing();
                }
            }
            kingKong.repaint();

        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };

    public void paintWalking() {
        if (goRight) {
            marioWalk = getToolkit().getImage("images/walk1.png");

        } else {
            marioWalk = getToolkit().getImage("images/walk2.png");
        }
        goRight = !goRight;
        marioRectangle = new Rectangle(KongFrame.row, KongFrame.colum, marioWalk.getWidth(this), marioWalk.getHeight(this));
        kingKong.repaint();
    }

    public void paintWalkingBack() {
        if (goLeft) {
            marioWalk = getToolkit().getImage("images/walk1back.png");
        } else {
            marioWalk = getToolkit().getImage("images/walk2back.png");
        }
        goLeft = !goLeft;
        marioRectangle = new Rectangle(KongFrame.row, KongFrame.colum, marioWalk.getWidth(this), marioWalk.getHeight(this));
        kingKong.repaint();
    }

    public void paintClimbing() {
        if (goUp) {
            marioWalk = getToolkit().getImage("images/climb1.png");
        } else {
            marioWalk = getToolkit().getImage("images/climb2.png");
        }
        goUp = !goUp;
        marioRectangle = new Rectangle(KongFrame.row, KongFrame.colum, marioWalk.getWidth(this), marioWalk.getHeight(this));
        kingKong.repaint();

    }
}
