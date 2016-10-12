/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arga;
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
public class Arga extends com.golden.gamedev.object.Sprite {

    Arga(BufferedImage image) {
        super(image);
    }

    Arga(BufferedImage image, double x, double y) {
        super(image, x-image.getWidth()/2, y-image.getHeight()/2);
    }
    
}
