/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal;

/**
 *
 * @author Jorge
 */
public class Zoom {
    
    public int x, y;
    public float scale = 0;
    
    public Zoom(int x, int y, float scale){
        this.x = x;
        this.y = y;
        this.scale = scale;
    }
}
