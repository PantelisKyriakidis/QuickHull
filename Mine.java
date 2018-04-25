/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treasurehunt;

/**
 * Base class of the arraylists. It contains the coordinates of the mines , player and treasure.
 * @author Pantelis Kyriakidis
 * AEM=2551
 * email: pantelisk@csd.auth.gr
 */
public class Mine {
    private int x;
    private int y;
    
    /**
     * Constructor.
     * @param x
     * @param y
     */
    public Mine(int x,int y){
        this.x=x;
        this.y=y;
    }
    
    /**
     * getters
     * @return
     */
    public int getX(){return x;}
    public int getY(){return y;}
}
