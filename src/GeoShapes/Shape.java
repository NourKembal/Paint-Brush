package GeoShapes;


import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nour
 */
public abstract class Shape {

    protected Color color;
    protected Boolean filled;
    protected Boolean dotted;

    protected Shape() {
        color = Color.BLACK;
        filled = false;
        dotted = false;
    }
}
