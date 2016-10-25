/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arga;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author godric
 */
public class Agar extends com.golden.gamedev.object.Sprite {

    double zoom;
    
    Agar(BufferedImage image) {
        super(image);
    }

    Agar(BufferedImage image, double x, double y) {
        super(image, x-image.getWidth()/2, y-image.getHeight()/2);
    }
    
    public void setzoom(double value) {
        this.zoom = value;
    }
    
    public double getzoom() {
        return this.zoom;
    }
}
