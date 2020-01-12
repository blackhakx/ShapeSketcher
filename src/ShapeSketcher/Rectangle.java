

// File name : Rectangle.java

package ShapeSketcher;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author 91ken
 */
public class Rectangle extends Shape{
    
    protected boolean filled;
    
    public Rectangle(Point startPoint){
        super(startPoint);
        this.filled = false;
    }
    
    
    @Override
    public void draw(Graphics g){       
        double sizeX,sizeY,startX, startY;

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
            sizeY = controlPoint.y - startPoint.y;
        }
        else{
            startY = controlPoint.y;
            sizeY = startPoint.y - controlPoint.y;
        }
        g.setColor(getColour());
        if(!filled){
            g.drawRect((int)startX, (int)startY, (int)sizeX, (int)sizeY);
            //g.drawRect((int)x1, (int)y1, (int)x2, (int)y2);
        }
        else{
            
            g.fillRect((int)startX, (int)startY, (int)sizeX, (int)sizeY);
            //g.fillRect((int)x1, (int)y1, (int)x2, (int)y2);
        }
    }
    
    @Override
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
