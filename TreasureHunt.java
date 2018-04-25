/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treasurehunt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;

/**
 *
 * @author Pantelis Kyriakidis
 * AEM=2551
 * email: pantelisk@csd.auth.gr
 */
public class TreasureHunt {

    /**
     * This is the main class of my program. Totally I used 5 classes.
     * Here i read the input from the txt and stored the points to the objects I Made.
     * I have one class for the minefield which except of mines has the starting point and the treasure.
     * The class named QuickHull is the only part of the programm that is not mine(i'll write the url there).
     * One class for the second part of the project named treasure.
     * Finally the class named Mine is the base class of my array lists.
     * 
     * After storing the variables to my objects, main class is doesnt make many things. 
     * 
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        int xekk=0,yekk=0,xtr=0,ytr=0;
        MineField mf=new MineField();  //except of mines it also contains the human and the treaure 
        Treasure treasure=new Treasure();
        
        try (BufferedReader in = new BufferedReader( new FileReader("in.txt"));)
            { 
                String l;
                
                int line=1;
                while ((l = in.readLine()) != null)   //reading from txt  
                    {                                   
                        if (line ==3){
                            int num=Integer.parseInt(l);  //making string to int 
                            treasure.setDiaNum(num);
                            line++;
                            continue;
                        }
                        
                        String[] l2=l.split(" ");           //I spited the lines tha had to numbers to put them in different vriables.
                        int x=Integer.parseInt(l2[0]) , y=Integer.parseInt(l2[1]);
                        
                        Mine mine=new Mine(x,y);  
                        mf.add(mine);       
                        
                        if(line==1){                //here i stored my startig and ending coordinates for later use.
                            xekk=mine.getX();
                            yekk=mine.getY();
                        }
                        if(line==2){
                            xtr=mine.getX();
                            ytr=mine.getY();
                        }
                        
                        line++;
                    }       
            }
        QuickHull qh = new QuickHull();                                //quickhull's convex hull  stored in arraylist named p. 
        ArrayList<Mine> p = qh.quickHull(mf.getMines());
        
        int ekk=0,tr=0;                              //finding the index of start and end in convex hull and..
        for (int i=0;i<p.size();i++){
            if(p.get(i).getX()==xekk && p.get(i).getY()==yekk){ekk=i;}
            if(p.get(i).getX()==xtr && p.get(i).getY()==ytr){tr=i;}    
        }
        
        mf.paths(ekk, tr, p);                           //...and sending them to find the 2 different paths leading to the treasure.
        
        if(mf.pathDistance(mf.getpath1())< mf.pathDistance(mf.getpath2())){
            System.out.println("The shortest distance is "+mf.pathDistance(mf.getpath1()));     //finding the shortest path and printing it.
            mf.toStr(mf.getpath1());
        }
        else {
            System.out.println("The shortest distance is "+mf.pathDistance(mf.getpath2()));
            mf.toStr(mf.getpath2());
        }
        
        //Second Part of the Project
        
        System.out.println("Number of weightings: "+treasure.weightings());          //just a println for the second part.The algorithm i use'd is at tresure class.
    }
}
