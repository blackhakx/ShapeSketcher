/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// File name : ShapeSketcher.java

package ShapeSketcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author 91ken
 */
public class ShapeSketcher extends JPanel implements ActionListener{
    
    private final JPanel componentPanel, drawingPanel, buttonPanel;
    private final JRadioButton circleButton, lineButton, ovalButton, rectangleButton, squareButton;
    private final JButton colorButton, saveButton, loadButton;
    private final JCheckBox filledBox;
    private Shape shape;
    private List<Shape> shapeList;
    private boolean pressed = false;
    private Color color;
    
    public static void main(String[] args){
        ShapeSketcher myPanel = new ShapeSketcher();
        JFrame frame = new JFrame("Shape Sketcher"); //create frame to hold our JPanel subclass	
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(myPanel);  //add instance of MyGUI to the frame
        frame.pack(); //resize frame to fit our Jpanel
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width / 2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
	//show the frame	
        frame.setVisible(true);
    }
    
    public ShapeSketcher(){
        super(new BorderLayout());
        shapeList = new ArrayList<>();
        this.color = Color.black;
        componentPanel = new JPanel(new BorderLayout());
        
        //buttonPanel = new JPanel(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel = new JPanel();
        circleButton = new JRadioButton("Circle");
        circleButton.setSelected(true);
        lineButton = new JRadioButton("Line");
        ovalButton = new JRadioButton("Oval");
        rectangleButton = new JRadioButton("Rectangle");
        squareButton = new JRadioButton("Square");
        Box buttons = Box.createVerticalBox();
        ButtonGroup group = new ButtonGroup();
        group.add(circleButton);
        group.add(lineButton);
        group.add(ovalButton);
        group.add(rectangleButton);
        group.add(squareButton);
        buttons.add(circleButton);
        buttons.add(lineButton);
        buttons.add(ovalButton);
        buttons.add(rectangleButton);
        buttons.add(squareButton);
        filledBox = new JCheckBox("filled");
        buttons.add(filledBox);
        buttonPanel.add(buttons);
        
        //Color button
        colorButton = new JButton();
        colorButton.setPreferredSize(new Dimension(componentPanel.getWidth(), 50));
        colorButton.setBackground(color);
        colorButton.addActionListener(this);
        
        //save/load buttons
        JPanel saveloadPanel = new JPanel();
        saveButton = new JButton("SAVE");
        saveButton.addActionListener(this);
        saveloadPanel.add(saveButton);
        loadButton = new JButton("LOAD");
        loadButton.addActionListener(this);
        saveloadPanel.add(loadButton);
        
        
        componentPanel.add(saveloadPanel, BorderLayout.SOUTH);
        componentPanel.add(colorButton, BorderLayout.NORTH);
        componentPanel.add(buttonPanel, BorderLayout.CENTER);
        
        
        
        drawingPanel = new DrawingPanel();
        //drawingPanel.setFocusable(true);
        drawingPanel.addMouseListener((MouseListener) drawingPanel);
        drawingPanel.addMouseMotionListener((MouseMotionListener) drawingPanel);
        
        add(drawingPanel, BorderLayout.CENTER);
        add(componentPanel, BorderLayout.WEST);
        
    }
    
    private class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener
    {
        public DrawingPanel(){
            setPreferredSize(new Dimension(400, 600));
            this.setSize(new Dimension(400, 600));
            this.
            setFocusable(true);
            setBackground(Color.WHITE);
            
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(!shapeList.isEmpty()){
                for(Shape sh:shapeList){
                    sh.draw(g);
                }
            }
            if(shape!=null){
                shape.draw(g);
            }
            Toolkit.getDefaultToolkit().sync();
            
        }

        @Override
        public void mouseClicked(MouseEvent me) { }

        @Override
        public void mousePressed(MouseEvent me) {
            pressed = true;
            Point pnt = me.getPoint();
            if(circleButton.isSelected()){
                shape = new Circle(pnt);
            }
            else if(squareButton.isSelected()){
                shape = new Square(pnt);
            }
            else if(ovalButton.isSelected()){
                shape = new Oval(pnt);
            }
            else if(rectangleButton.isSelected()){
                shape = new Rectangle(pnt);
            }
            else if(lineButton.isSelected()){
                shape = new Line(pnt);
            }
            
            if(filledBox.isSelected()){
                shape.setFilled(true);
            }
            
            shape.setColour(color);
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            pressed = false;
            shapeList.add(shape);
        }

        @Override
        public void mouseDragged(MouseEvent me) {
            if(pressed){
                Point pnt = me.getPoint();
                shape.setControlPoint(pnt);
                drawingPanel.repaint();
                System.out.println(shape.toString());
            }
            
        }
        //Unused/Empty methods
        @Override
        public void mouseEntered(MouseEvent me) {}
        @Override
        public void mouseExited(MouseEvent me){} 
        @Override
        public void mouseMoved(MouseEvent me) {  }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object src = ae.getSource();
        if(src == colorButton){
            color = JColorChooser.showDialog(null, "Choose a color", color);
            colorButton.setBackground(color);
        }
        else if(src == saveButton){
            //set files to save to .txt
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "text", "txt");
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(filter);
            fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int rValue = fc.showSaveDialog(null);
            //If file is chosen
            if(rValue == JFileChooser.APPROVE_OPTION){
                try{
                    File selected = fc.getSelectedFile();
                    FileOutputStream fOut = new FileOutputStream(selected); 
                    ObjectOutputStream oStream = new ObjectOutputStream(fOut);
                    oStream.writeObject(shapeList);
                    fOut.flush();
                    fOut.close();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "File Not Found Error "+ex, "File Not Found Exception", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "IO Error "+ex, "IO Exception", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        else if(src == loadButton){
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "text", "txt");
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(filter);
            fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int rValue = fc.showSaveDialog(null);
            if(rValue == JFileChooser.APPROVE_OPTION){
                try{
                    shapeList.clear();
                    File selected = fc.getSelectedFile();
                    FileInputStream fIn = new FileInputStream(selected);
                    ObjectInputStream iStream = new ObjectInputStream(fIn);                    
                    shapeList = (ArrayList<Shape>) iStream.readObject();
                    fIn.close();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "File Not Found Error: "+ex.getMessage(), "File Not Found Exception", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "IO Error: "+ex.getMessage(), "IO Exception", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    //no object class in loaded file
                    JOptionPane.showMessageDialog(this, "Class Cast Error "+ex.getMessage(), "Class Not Found Exception", JOptionPane.ERROR_MESSAGE);
                } catch (ClassCastException ex){
                    //loaded text has wrong class
                    JOptionPane.showMessageDialog(this, "Class Cast Error "+ex.getMessage(), "Class Cast Exception", JOptionPane.ERROR_MESSAGE);
                }
            }
            drawingPanel.repaint();
        }
    }
}
