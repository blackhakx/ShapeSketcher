/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// File name : Circle.java

package ShapeSketcher;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author 91ken
 */
public class Circle extends Oval{
   
    Circle(Point pnt) {
        super(pnt);
    }

    @Override
    public void draw(Graphics g){
        
        double startX, startY, sizeX;
        //width and height of circle is the difference of startPoint and controlPoint x values
        if(startPoint.x > controlPoint.x){
            sizeX = startPoint.x - controlPoint.x;
        }
        else{
            sizeX = controlPoint.x - startPoint.x;
        }
        
        startX = startPoint.x - sizeX;
        startY = startPoint.y - sizeX;
        sizeX *=2;
        
        g.setColor(getColour());
        if(filled){
            g.fillOval((int)startX, (int)startY, (int)sizeX, (int)sizeX);
        }
        else{
            g.drawOval((int)startX, (int)startY, (int)sizeX, (int)sizeX);
        }

    }
    
}
