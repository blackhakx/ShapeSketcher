/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// File name : Shape.java

package ShapeSketcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author 91ken
 */
public abstract class Shape implements Serializable, EnclosesRegion{
    protected Point startPoint;
    protected Point controlPoint;
    private Color colour;
    
    public Shape(){
        this(new Point(0,0));
    }
    
    public Shape(Point startPoint){
        this.startPoint = startPoint;
        this.colour = Color.BLACK;
        this.controlPoint = startPoint;
    }
    
    public Color getColour(){
        return colour;
    }
    
    public void setColour(Color colour){
        this.colour = colour;
    }
    
    public void setControlPoint (Point controlPoint){
        this.controlPoint = controlPoint;
    }
    
    public void draw(Graphics g){
        double sizeX = startPoint.x - controlPoint.x;
        double sizeY = startPoint.y - controlPoint.y;
        g.setColor(colour);
        g.drawRect((int)startPoint.x, (int)startPoint.y, (int)sizeX, (int)sizeY);
    }
    
    @Override
    public String toString(){
        return("startPoint (x = "+startPoint.x+", y = "+startPoint.y
                +") controlPoint (x = "+controlPoint.x+", y = "+controlPoint.y+
                ") color(r="+colour.getRed()+",g="+colour.getGreen()+",b="+colour.getBlue()+")");
    }
}
