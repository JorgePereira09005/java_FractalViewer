/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal;

//Class that represents complex numbers. We need it to draw the fractal.
public class Complex {
    
    public double re, im;
    
    public Complex() {
        this.re = 0;
        this.im = 0;
    }
    
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }
    
    public Complex multiply(Complex com) {
        double real = (this.re * com.re - this.im * com.im);
        double ima = (this.re*com.im + this.im*com.re);
        
        return new Complex(real,ima);
    }
    
    public Complex sum(Complex com) {
        double real = this.re + com.re;
        double ima = this.im + com.im;
        
        return new Complex(real, ima);
    }
    
    public double abs() {
        return Math.sqrt(((re*re)+(im*im)));
    }
}
