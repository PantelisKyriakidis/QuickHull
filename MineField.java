/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treasurehunt;

import java.util.ArrayList;
import java.util.Collections;


/**
 * In this class I stores the arraylist with the coordinates and the 2 paths from the splited convexhull.
 * 
 * @author Pantelis Kyriakidis
 * AEM=2551
 * email: pantelisk@csd.auth.gr
 */
public class MineField {
    private ArrayList<Mine> Mines, path1, path2;
    
    /**
     * Constructor
     */
    public MineField(){
        Mines= new ArrayList<>();
        path1= new ArrayList<>();
        path2= new ArrayList<>();
    }
    
    /**
     * Add to Mines, path1 and path 2 a Mine.
     * @param mine
     */
    public void add(Mine mine){Mines.add(mine);}
    public void add1(Mine mine){path1.add(mine);}
    public void add2(Mine mine){path2.add(mine);}
    
    /**
     * Getters
     * @return
     */
    public ArrayList<Mine> getMines(){return Mines;}
    public ArrayList<Mine> getpath1(){return path1;}
    public ArrayList<Mine> getpath2(){return path2;}
    
    /**
     * here i find the paths.
     * The convex hull is a "circle" so i use 3 for loops to find the paths.
     * @param ekk index of starting point in convex hull
     * @param tr  index of tresure point in convex hull
     * @param hull ConvexHull
     */
    public void paths(int ekk ,int tr,ArrayList<Mine> hull){
        for(int i=ekk; i<=tr;i++){
            path1.add(hull.get(i));         //path1
        }
        for(int i=tr; i<hull.size();i++){   //half path2
            path2.add(hull.get(i));
        }
        for(int i=0; i<=ekk;i++){           //other half path2 
            path2.add(hull.get(i));
        }
       
        Collections.reverse(path2);         //reverse path 2,cause it started from the treasure.
    }
    
    /**
     * Here i calculate path distance.
     * i1 and i2 are the spots and sum is the total distance.
     * @param path
     * @return
     */
    public double pathDistance(ArrayList<Mine> path){
        double sum=0;
        int i1=0,i2=1;
        while (i2<path.size()){
            sum+=calcDistance(path.get(i1).getX(),path.get(i1).getY(),path.get(i2).getX(),path.get(i2).getY());
            i1++;
            i2++;
        }
        return sum;
    }
    
    /**
     * Here is just the type for the calculation above.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public double calcDistance(int x1, int y1, int x2 ,int y2) {
        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }
    
    /**
     * And here i print the shortest path.
     * @param path
     */
    public void toStr(ArrayList<Mine> path){
        System.out.print("The shortest path is:");
        for(int i=0;i<path.size();i++){
            Mine m=path.get(i);
            System.out.print("("+(float)m.getX()+","+(float)m.getY()+")");
            if(i!=path.size()-1){System.out.print("-->");}
        }
        System.out.println();
    }
}
