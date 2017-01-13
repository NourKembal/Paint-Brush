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
public class Rectangle extends Shape{
 

    private Point p1;
    private Point p2;
    private int width;
    private int height;
    
    public Rectangle() {
        p1 = new Point(0, 0);
        p2 = new Point(0, 0);
        width = 0;
        height=0;
    }

    public Rectangle(Point p1,Point p2) {
        this.p1 = p1;
        this.p2=p2;
        width = 0;
        height=0;
    }

    public void setP1(Point p) {
        p1 = p;
    }
    public void setP2(Point p) {
        p2 = p;
    }

    public void calcWidth() {
        if(p1.x>=p2.x)
            width=p1.x-p2.x;
        else
            width=p2.x-p1.x;
    }

    public void calcHeight() {
        if(p1.y>=p2.y)
            height=p1.y-p2.y;
        else
            height=p2.y-p1.y;
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
    
    public Point getULP()
    {
        if(p1.x<=p2.x && p1.y <=p2.y)
            return p1;
        else if(p1.x <=p2.x)
            return new Point(p1.x,p2.y);
        else if(p1.y <=p2.y)
            return new Point(p2.x, p1.y);
        else
            return p2;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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