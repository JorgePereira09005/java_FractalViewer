/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javafx.util.Pair;
import javax.imageio.ImageIO;

/**
 *
 * @author Jorge
 */
public class FractalCreator {

    public Mandelbrot mandel;
    public int resWidth, resHeight;
    public ZoomList zoomList;
    public BufferedImage Bitmap;
    public String filePath;
    public float hueOffset, hueMultiplier, saturation, brightness;

    public FractalCreator(int maxIterations, int width, int height, float hueMultiplier, float hueOffset, float saturation, float brightness, String filePath) {

        if (width == 0 || height == 0) {
            System.out.println("Resolution must be non-zero.");
            return;
        }
        
        this.filePath = filePath;
        this.mandel = new Mandelbrot(maxIterations);
        this.resWidth = width;
        this.resHeight = height;
        this.hueOffset = hueOffset;
        this.hueMultiplier = hueMultiplier;
        this.saturation = saturation;
        this.brightness = brightness;
        this.zoomList = new ZoomList(resWidth, resHeight);
        this.zoomList.addZoom(new Zoom(resWidth/2, resHeight/2, 4.0f/resWidth));
        this.Bitmap = new BufferedImage( resWidth, resHeight, BufferedImage.TYPE_INT_RGB );
    }
    
    void addZoom (Zoom zoom){
        this.zoomList.addZoom(zoom);
    }
    
    public void drawFractalBuffer() {
        
        for (int y = 0; y < resHeight; y++) {
            for (int x = 0; x < resWidth; x++) {
                
                Pair<Double, Double> coords = this.zoomList.doZoom(x,y);
                Pair<Integer, Float> values = this.mandel.getIterations(coords.getKey(), coords.getValue());
                
                if (values.getKey() < this.mandel.getMaxIterations()) {
                    Bitmap.setRGB(x, y, Color.HSBtoRGB(this.hueMultiplier*values.getValue() + this.hueOffset, this.saturation, this.brightness));
                }      
            }
        }
    }    
    
    public void drawBitmap() {
        try {
            RenderedImage rendImage = this.Bitmap;
            ImageIO.write(rendImage, "bmp", new File(this.filePath));
        } 
        catch ( IOException e) {
        }
    }
    
    public void saveBmp(String filepath){
        try {
            RenderedImage rendImage = this.Bitmap;
            ImageIO.write(rendImage, "bmp", new File(filepath));
        } 
        catch ( IOException e) {
        }
    }
    
    public void run() {
        this.drawFractalBuffer();
        this.drawBitmap();
    }
    
}
