/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arga;
import com.golden.gamedev.util.ImageUtil;
import java.awt.Dimension;
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
    private final int GROWTH = 5;
    private int eatCount;
    
    Agar(BufferedImage image) {
        super(ImageUtil.resize(image, 40, 40));
        
        eatCount = 0;
    }

    Agar(BufferedImage image, double x, double y) {
        super(ImageUtil.resize(image, 40, 40),
                x-image.getWidth()/2, y-image.getHeight()/2);
        
        eatCount = 0;
    }
    
    public void eat() {
        this.eatCount ++;
        this.setImage(ImageUtil.resize(this.getImage(), 
                this.getWidth() + GROWTH, this.getHeight() + GROWTH));
    }

    public String toStringEat() {
        return String.format("Eat %d", this.eatCount);
    }
    
    public void update(long l) {
        super.update(l);
    }
}
