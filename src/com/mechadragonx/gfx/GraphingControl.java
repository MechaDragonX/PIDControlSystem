/*
 * I didn't write this code. The code was provided to me by my teacher and I believe was written by a former student.
 */
package com.mechadragonx.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Here I am giving this to everyone to use. 
 * 
 * Set package for your project 
 * 
 * 
 * Don't forget to also bring in the drawing panel for this to work
 * @author overtho17
 */
public class GraphingControl {
    private DrawingPanel display;
    private Graphics g;
    private final double MaxValue;
    private final double MinValue;
    private Queue<Double> valueSet;
    private final int maxValues;
    private final double ScaleValue;
    /**
     * 
     * @param Max max value for display
     * @param Min mid value for display
     * @param size the size of the cache for the graph 
     */
    public GraphingControl(double Max, double Min, int size){
        MaxValue=Max;
        this.MinValue=Min;
        this.maxValues=size;
        display=new DrawingPanel(900,900);
        g=display.getGraphics();
        valueSet=new LinkedList<Double>();
        ScaleValue=900/Math.abs(Max-Min);
        g.setColor(Color.BLACK);
        
    }
    /**
     * draws the value. Set your own timer for this 
     * 
     */
    public void Draw(){
        int iterations = valueSet.size();
        if(iterations<2){
            System.out.println("not enough values returning");
            return;
        }
        g.setColor(Color.white);
        g.fillRect(0, 0, 900, 900);
        double horScale = 900/(iterations-1);
        Point last=new Point(0,0);
        for(int i=0;i<iterations;i++){
            double value = valueSet.poll();
            valueSet.add(value);
            value*=ScaleValue;
            Point current = new Point((int)(i*horScale),(int)(900-value));
            g.setColor(Color.black);
            g.fillOval(current.x-5,current.y-5, 10, 10);
            g.setColor(Color.red);
            g.drawLine(last.x, last.y, current.x,current.y);
            last=current;
        }
    }
    /**
     * adds a value to the cache. If overfilled will remove oldest value
     * does not redraw the screen
     * @param input 
     */
    public void AddValue(double input){
        valueSet.add(input);
        if(valueSet.size()>maxValues){
            valueSet.poll();
        }
    }
    /**
     * draws a static  line of the designated color at the point set 
     * WILL be cleared with a new draw. call after draw on each cycle.
     * @param Val
     * @param c 
     */
    public void drawLine(double Val, Color c){
    g.setColor(c);
    g.drawLine(0,(int)(900-Val*this.ScaleValue), 900,(int)(900-Val*this.ScaleValue));
    }
    private boolean closed =false;
    
}
