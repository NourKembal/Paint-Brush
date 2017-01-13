/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeoShapes;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

/**
 *
 * @author Nour
 */
public class FreeLine extends Shape {
    public Vector<Point> pointsVec;

    public FreeLine() {
        pointsVec=new Vector<>();
    }
    public void addPoint(Point p)
    {
        pointsVec.addElement(p);
    }
    public void setColor(Color c)
    {
        color=c;
    }
     public void setDotted(boolean b) {
        dotted = b;
    }
    public Color getColor()
    {
        return color;
    }
     public boolean getDotted() {
        return dotted;
    }
}
