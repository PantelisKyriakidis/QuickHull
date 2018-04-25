package treasurehunt;

import java.util.Random;

/**
 * This is the class i used for the second part.
 * I used the some theory that is reffered in the book on page 227-228 (Anany Levitin's book).
 * I wrote the code myself.
 * efficiency analyze:
 * At fist I divided diamond with 2 but later i found out that if i divided them with 3 it has an algorithmic efficiency of:(log3n)
 * So that's the reason i choose to divide with 3. 
 *
 * 
 * @author Pantelis Kyriakidis
 * AEM=2551
 * email: pantelisk@csd.auth.gr
 */
public class Treasure {
    private int dnum;   //diamond number
    private int w;      //number of weightings
    
    /**
     *Constructor
     */
    public Treasure(){w=0;}
    
    /**
     *seter for diamond number 
     * @param number 
     */
    public void setDiaNum(int number){dnum=number;}
    
    /**
     * this is the method from the exercise copied. I use it for weighing.
     * @return
     */
    public int zygos(){               
        Random randomGenerator = new Random();
        int x = randomGenerator.nextInt(100);         
        if (x<34)             
            return 1;          
        else if (x < 67)             
            return 0;          
        else             
            return -1;  
    }             

    /**
     * The algorithm to find the weighings.
     * I tried to make it as much austere i could..i did not use many viriables neither too many lines of code.
     * It is a recursive algorithm.
     * I took the dnum only. 
     * I divide it by 3, add one to w and call the method again IF the number is even , because we take the same 3 numbers.
     * On the other hand if the number is uneven, then the third number must take the division remainder.For ex. 52/3=17 , 52%3 =1 . So we have: 17,17,18.
     * After that I weighed the "first 2".if they are even (z==0), then the third is the one with the fake diamond.so I add the remaindes on the dnum and call weightings(). 
     * Finally,   case: dnum=1 (return the diamond) , case: dnum=2 (cant be divided to 3 numbers (0,0,2)), so its only a weighing an we find the fake.
     * 
     * @return w: number o weighings.
     */
    public int weightings(){
        if (dnum==1)
            return w;
        else if (dnum==2)
            return w+1;
        else{
            w+=1;
            dnum=dnum/3;
            if (dnum%3!=0){ 
                int mod=dnum%3;
                int z=zygos();
                if (z==0)
                    dnum+= mod;
            }
            weightings();
        }
        return w;
    }
}
