package GeoShapes;


import java.awt.Color;
import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nour
 */
public class Line extends Shape {

    private Point start;
    private Point end;

    public Line() {
        start = new Point(0, 0);
        end = new Point(0, 0);
    }

    public Line(Point p) {
        start = p;
        end = p;
    }

    public Line(Point s, Point e) {
        start = s;
        end = e;
    }

    public void setStart(Point p) {
        start = p;
    }

    public void setEnd(Point p) {
        end = p;
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

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
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
