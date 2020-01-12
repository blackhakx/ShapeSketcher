/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// File name : Square.java

package ShapeSketcher;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author 91ken
 */
public class Square extends Rectangle{

    public Square(Point startPoint) {
        super(startPoint);
    }
    
    @Override
    public void draw(Graphics g){
        //, startY, sizeY
        double startX, sizeX, startY;
        //width and height of square is the difference of startPoint and controlPoint x values
        if(startPoint.x <= controlPoint.x){
            startX = startPoint.x;
            sizeX = controlPoint.x - startPoint.x;
        }
        else{
            startX = controlPoint.x;
            sizeX = startPoint.x - controlPoint.x;
        }
        
        if(startPoint.y <= controlPoint.y){
            startY = startPoint.y;
        }
        else{
            startY = startPoint.y - sizeX;
        }
        
        g.setColor(getColour());
        if(!filled){
            g.drawRect((int)startX, (int)startY, (int)sizeX, (int)sizeX);
        }
        else{
            g.fillRect((int)startX, (int)startY, (int)sizeX, (int)sizeX);
        }
    }

}
