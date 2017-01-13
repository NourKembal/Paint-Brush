/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeoShapes;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Nour
 */
public class Circle extends Shape {
    private Point p1;
    private Point p2;
   // private int diameter;
    private int width;
    private int height;

    public Circle() {
        p1 = new Point(0, 0);
        p2 =new Point(0, 0);
        //diameter = 0;
        width = 0;
        height = 0;
    }

//    public UCircle(Point p,Point pp, int r) {
//        p1 = p;
//        p2=pp;
//        diameter = r;
//    }

    public void setP1(Point p) {
        p1 = p;
    }
    
    public void setP2(Point p) {
        p2 = p;
    }

//    public void calcDiameter() {
//        diameter=(int)Math.hypot(p1.x-p2.x, p1.y-p2.y);
//    }
    
     public void calcWidth() {
        width=p1.x-p2.x;
        if(width<0)
            width=-width;
    }
     
      public void calcHeight() {
          height=p1.y-p2.y;
          if(height<0)
              height=-height;
    }

    public void setColor(Color c) {
        color = c;
    }

    public void setFilled(boolean b) {
        filled = b;
    }

    public void setDotted(boolean b) {
        dotted = b;
    }

    public Point getP1() {
        return p1;
    }
    
    public Point getP2() {
        return p2;
    }
    
    public Point getULP() {
        if(p1.x<=p2.x && p1.y <=p2.y)
            return p1;
        else if(p1.x <=p2.x)
            return new Point(p1.x,p2.y);
        else if(p1.y <=p2.y)
            return new Point(p2.x, p1.y);
        else
            return p2;
    }

//    public int getDiameter() {
//        return diameter;
//    }
    public int getHeight() {
       return height;
    }
    public int getWidth() {
       return width;
    }

    public Color getColor() {
        return color;
    }

    public boolean getFilled() {
        return filled;
    }

    public boolean getDotted() {
        return dotted;
    }
}
