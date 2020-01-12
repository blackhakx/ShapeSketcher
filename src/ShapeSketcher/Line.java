/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// File name : Line.java

package ShapeSketcher;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author 91ken
 */
public class Line extends Shape{

    private boolean filled;
    
    public Line(Point startPoint){
        super(startPoint);
    }
    
    @Override
    public void draw(Graphics g){
        g.setColor(getColour());
        g.drawLine(startPoint.x, startPoint.y, controlPoint.x, controlPoint.y);
        
    }
    
    @Override
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

}
