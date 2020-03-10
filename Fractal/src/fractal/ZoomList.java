/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal;

import java.util.ArrayList;
import javafx.util.Pair;

public class ZoomList {
    
    private double m_xCenter, m_yCenter, m_scale;
    int m_width, m_height ;
    ArrayList<Zoom> zooms;
    
    public ZoomList (int width, int height) {
        this.m_width = width;
        this.m_height = height;
        this.m_scale = 1.0;
        this.zooms = new ArrayList<>();
    }
    
//    Converts Bitmap coordinates to fractal-adjusted coordinates, which will be contained in [-1, 1], and finds the new center point. 
//    zoom.x and zoom.y are the coordinates of the new center point in Bitmap coordinates
    public void addZoom(Zoom zoom) {
        this.zooms.add(zoom);
        
        m_xCenter += (zoom.x - m_width/2) * m_scale;
        m_yCenter += -(zoom.y - m_height/2) * m_scale;
        m_scale *= zoom.scale;
        
    }
    
    // Shifts fractal-adjusted coordinates towards the new center point.
    public Pair<Double, Double> doZoom (int x, int y) {
        
        double xFractal = (x - m_width/2) * m_scale + m_xCenter;
        double yFractal = (y - m_height/2) * m_scale + m_yCenter;
        
        return new Pair<>(xFractal, yFractal);
    }
}