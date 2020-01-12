/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// File name : Oval.java

package ShapeSketcher;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author ken
 */
public class Oval extends Shape{
    protected boolean filled;
    
    public Oval(Point startPoint){
        super(startPoint);
        this.filled = false;
    }
    
    @Override
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    
    
    @Override
    public void draw(Graphics g){

        double startX, startY, sizeX, sizeY;
        
        if(startPoint.x <= controlPoint.x &&(startPoint.y <= controlPoint.y)){
            
            sizeX = controlPoint.x - startPoint.x;
            startX = startPoint.x - sizeX;
            sizeX *= 2;
            
            sizeY = controlPoint.y - startPoint.y;
            startY = startPoint.y - sizeY;
            sizeY *= 2;
        }
        else if(startPoint.x > controlPoint.x &&(startPoint.y <= controlPoint.y)){
            sizeX = startPoint.x - controlPoint.x;
            startX = controlPoint.x;
            //startX = controlPoint.x - sizeX;
            sizeX *= 2;
            
            sizeY = controlPoint.y - startPoint.y;
            
            startY = startPoint.y - sizeY;
            sizeY *= 2;
        }
        else if((startPoint.x > controlPoint.x) && (startPoint.y > controlPoint.y)){
            
            sizeX = startPoint.x - controlPoint.x;
            sizeX *= 2;
            startX = controlPoint.x;
            
            
            sizeY = startPoint.y - controlPoint.y;
            sizeY *= 2;
            startY = controlPoint.y;
            
        }
        else{
            sizeX = controlPoint.x - startPoint.x;
            sizeX *= 2;
            startX = controlPoint.x - sizeX;
            
            
            sizeY = startPoint.y - controlPoint.y;
            sizeY *= 2;
            //startY = startPoint.y - sizeY;
            startY = controlPoint.y;
        }
        g.setColor(getColour());
        
        if(filled){
            g.fillOval((int)startX, (int)startY, (int)sizeX, (int)sizeY);
        }
        else{
            g.drawOval((int)startX, (int)startY, (int)sizeX, (int)sizeY);
        }
    }
    
    
}
