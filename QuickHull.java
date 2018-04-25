/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treasurehunt;

import java.util.ArrayList;

/**
 * This is the class for the quickHull algorithm. 
 * I found it here: http://www.sanfoundry.com/java-program-implement-quick-hull-algorithm-find-convex-hull/
 * Its average case complexity is considered to be O(n * log(n)), whereas in the worst case it takes O(n2).
 * T(n) = O(n) + 2T(n/2) = O(nlogn)
 * T(n) = O(n) + T(n-1) =O(n2)
 * 
 * @author Pantelis Kyriakidis
 * AEM=2551
 * email: pantelisk@csd.auth.gr
 */
public class QuickHull {

    /**
     * Here is the starting method.
     * @param points
     * @return
     */
    public ArrayList<Mine> quickHull(ArrayList<Mine> points)
    {
        ArrayList<Mine> convexHull = new ArrayList<Mine>();
        if (points.size() < 3)
            return (ArrayList) points.clone();  
 
        int minPoint = -1, maxPoint = -1;
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (int i = 0; i < points.size(); i++)
        {
            if (points.get(i).getX() < minX)
            {                                                           //finding min and max X.
                minX = points.get(i).getX();
                minPoint = i;
            }
            if (points.get(i).getX() > maxX)
            {
                maxX = points.get(i).getX();
                maxPoint = i;
            }
        }
        Mine A = points.get(minPoint);                  
        Mine B = points.get(maxPoint);
        convexHull.add(A);                                      //adding to the hull and removing them from points
        convexHull.add(B);
        points.remove(A);
        points.remove(B);
 
        ArrayList<Mine> leftSet = new ArrayList<Mine>();
        ArrayList<Mine> rightSet = new ArrayList<Mine>();
 
        for (int i = 0; i < points.size(); i++)
        {
            Mine p = points.get(i);
            if (pointLocation(A, B, p) == -1)                           //left , right sets
                leftSet.add(p);
            else if (pointLocation(A, B, p) == 1)
                rightSet.add(p);
        }
        hullSet(A, B, rightSet, convexHull);        
        hullSet(B, A, leftSet, convexHull);
 
        return convexHull;
    }
 
    /**
     * Calculating the distance.
     * @param A
     * @param B
     * @param C
     * @return
     */
    public int distance(Mine A, Mine B, Mine C)
    {
        int ABx = B.getX() - A.getX();
        int ABy = B.getY() - A.getY();
        int num = ABx * (A.getY() - C.getY()) - ABy * (A.getX() - C.getX());
        if (num < 0)
            num = -num;
        return num;
    }
            
    /**
     *
     * @param A
     * @param B
     * @param set
     * @param hull
     */
    public void hullSet(Mine A, Mine B, ArrayList<Mine> set,
            ArrayList<Mine> hull)
    {
        int insertPosition = hull.indexOf(B);
        if (set.size() == 0)
            return;
        if (set.size() == 1)
        {
            Mine p = set.get(0);
            set.remove(p);
            hull.add(insertPosition, p);
            return;
        }
        int dist = Integer.MIN_VALUE;
        int furthestPoint = -1;                             
        for (int i = 0; i < set.size(); i++)
        {
            Mine p = set.get(i);
            int distance = distance(A, B, p);               //finding the furthest point
            if (distance > dist)
            {
                dist = distance;
                furthestPoint = i;
            }
        }
        Mine P = set.get(furthestPoint);
        set.remove(furthestPoint);
        hull.add(insertPosition, P);
 
                                                                // Determine who's to the left of AP
        ArrayList<Mine> leftSetAP = new ArrayList<Mine>();
        for (int i = 0; i < set.size(); i++)
        {
            Mine M = set.get(i);
            if (pointLocation(A, P, M) == 1)
            {
                leftSetAP.add(M);
            }
        }
 
                                                                // Determine who's to the left of PB
        ArrayList<Mine> leftSetPB = new ArrayList<Mine>();
        for (int i = 0; i < set.size(); i++)
        {
            Mine M = set.get(i);
            if (pointLocation(P, B, M) == 1)
            {
                leftSetPB.add(M);
            }
        }
        hullSet(A, P, leftSetAP, hull);
        hullSet(P, B, leftSetPB, hull);
 
    }
 
    /**
     * Finding if a point belongs to left or right set 
     * @param A
     * @param B
     * @param P
     * @return
     */
    public int pointLocation(Mine A, Mine B, Mine P)
    {
        int cp1 = (B.getX() - A.getX()) * (P.getY() - A.getY()) - (B.getY() - A.getY()) * (P.getX() - A.getX());
        if (cp1 > 0)
            return 1;
        else if (cp1 == 0)
            return 0;
        else
            return -1;
    }
}
