/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal;

import javafx.util.Pair;

/**
 *
 * @author Jorge
 */
public class Mandelbrot {
    
    private int maxIterations;
    
    public Mandelbrot(int iterations) {
        
        if (iterations == 0) {
            System.out.println("Number of iterations must be non-zero.");
        }
        else {
            maxIterations = iterations;
        }
    }
    
    public double log2(double a){
        return Math.log(a)/Math.log(2);
    }
        
    public int getMaxIterations() {
        return this.maxIterations;
    }
    
    Pair<Integer, Float> getIterations(double x, double y) {
        
        Complex z = new Complex(); //z0
        Complex c = new Complex(x, y);
        float smoothColor = 0;
        float magnitude;
        int iterations = 0;
        
        while (iterations < this.maxIterations) {
            Complex support = z.multiply(z);
            z = support.sum(c);
            
            magnitude = (float) z.abs();
            if (magnitude > 2) {
                
                smoothColor = (float) (iterations + 1 - Math.log(this.log2(magnitude)));
                break;
            }
            iterations++;
        }
        
        return new Pair<>(iterations, smoothColor);
    }
}
